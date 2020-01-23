package com.befitca.cleanarch.adapter.cognito;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.befitca.cleanarch.usecase.port.out.UserAuthenticator;
import lombok.Setter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class UserAuthenticatorCognitoImpl implements UserAuthenticator {

    private String clientId;
    @Setter
    private String clientSecret;
    private String poolId;
    private AWSCognitoIdentityProvider awsCognitoIdentityProvider;

    public UserAuthenticatorCognitoImpl(final CognitoParams params) {
        AWSCredentials credentials = new BasicAWSCredentials(params.getAwsAccessKey(), params.getAwsSecretKey());
        this.clientId = params.getClientId();
        this.poolId = params.getPoolId();
        this.awsCognitoIdentityProvider = createCognitoClient(credentials,params.getRegion());
    }

    protected AWSCognitoIdentityProvider createCognitoClient(final AWSCredentials credentials, final String region) {
        AWSCognitoIdentityProvider awsCognitoIdentityProvider = AWSCognitoIdentityProviderClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.fromName(region))
                .build();
        return awsCognitoIdentityProvider;
    }

    @Override
    public boolean authenticate(final String username, final String password) {

        AuthenticationResultType authentication = null;
        // TODO:Remove new password challenge
        try {
            AdminInitiateAuthResult adminInitiateAuthResult = authenticateWithCognito(username, password);
            if (ChallengeNameType.NEW_PASSWORD_REQUIRED.toString().equals(adminInitiateAuthResult.getChallengeName())) {
                String session = adminInitiateAuthResult.getSession();
                AdminRespondToAuthChallengeResult adminRespondToAuthChallengeResult = confirmPasswordWithCognito(username,password,session);
                authentication = adminRespondToAuthChallengeResult.getAuthenticationResult();
            } else {
                authentication = adminInitiateAuthResult.getAuthenticationResult();
            }
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    protected AdminRespondToAuthChallengeResult confirmPasswordWithCognito(final String username, final String password, final String session) {

        AdminRespondToAuthChallengeRequest adminRespondToAuthChallengeRequest = new AdminRespondToAuthChallengeRequest();
        adminRespondToAuthChallengeRequest.setUserPoolId(poolId);
        adminRespondToAuthChallengeRequest.setClientId(clientId);
        adminRespondToAuthChallengeRequest.setSession(session);
        adminRespondToAuthChallengeRequest.setChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED);
        adminRespondToAuthChallengeRequest.addChallengeResponsesEntry("USERNAME",username);
        adminRespondToAuthChallengeRequest.addChallengeResponsesEntry("PASSWORD",password);
        adminRespondToAuthChallengeRequest.addChallengeResponsesEntry("NEW_PASSWORD",password);

        AdminRespondToAuthChallengeResult adminRespondToAuthChallengeResult = awsCognitoIdentityProvider.adminRespondToAuthChallenge(adminRespondToAuthChallengeRequest);
        return adminRespondToAuthChallengeResult;
    }

    private AdminInitiateAuthResult authenticateWithCognito(String username, String password) {

        AdminInitiateAuthRequest adminInitiateAuthRequest = new AdminInitiateAuthRequest();
        adminInitiateAuthRequest.setAuthFlow(AuthFlowType.ADMIN_USER_PASSWORD_AUTH);
        adminInitiateAuthRequest.setClientId(this.clientId);
        adminInitiateAuthRequest.setUserPoolId(this.poolId);
        if (clientSecret != null) {
            adminInitiateAuthRequest.addAuthParametersEntry("SECRET_HASH", calculateSecretHash(poolId,clientSecret,clientId));
        }
        adminInitiateAuthRequest.addAuthParametersEntry("USERNAME", username);
        adminInitiateAuthRequest.addAuthParametersEntry("PASSWORD", password);
        AdminInitiateAuthResult adminInitiateAuthResult = awsCognitoIdentityProvider.adminInitiateAuth(adminInitiateAuthRequest);
        return adminInitiateAuthResult;
    }

    public static String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName) {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            mac.update(userName.getBytes(StandardCharsets.UTF_8));
            byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating ");
        }
    }
}
