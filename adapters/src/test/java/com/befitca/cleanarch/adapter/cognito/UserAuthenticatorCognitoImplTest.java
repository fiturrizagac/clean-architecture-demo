package com.befitca.cleanarch.adapter.cognito;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class UserAuthenticatorCognitoImplTest {

    private static final String REGION = "xxxxxx";
    private static final String CLIENT_ID = "xxxxxx";
    private static final String CLIENT_SECRET = "xxxxxx";
    private static final String POOL_ID = "xxxxxx";
    private static final String AWS_ACCESSKEY = "xxxxxx";
    private static final String AWS_SECRETKEY = "xxxxxx";
    private static final String USERNAME = "xxxxxx";
    private static final String PASSWORD = "xxxxxx";

    private UserAuthenticatorCognitoImpl target;

    private CognitoParams createCognitoParams() {
        CognitoParams params = new CognitoParams();
        params.setClientId(POOL_ID);
        params.setClientId(CLIENT_ID);
        params.setAwsAccessKey(AWS_ACCESSKEY);
        params.setAwsSecretKey(AWS_SECRETKEY);
        params.setRegion(REGION);
        return params;
    }

    @Before
    public void setUp() {
        target = new UserAuthenticatorCognitoImpl(createCognitoParams());
        //target.setClientSecret(CLIENT_SECRET);
    }

    @Ignore
    @Test
    public void name() {
        target.authenticate(USERNAME,PASSWORD);
    }
}
