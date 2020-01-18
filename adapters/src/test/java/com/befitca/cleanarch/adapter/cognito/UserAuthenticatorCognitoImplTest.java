package com.befitca.cleanarch.adapter.cognito;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import org.junit.Before;
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

    @Before
    public void setUp() {
        AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESSKEY,AWS_SECRETKEY);
        target = new UserAuthenticatorCognitoImpl(CLIENT_ID,POOL_ID,REGION,credentials);
        //target.setClientSecret(CLIENT_SECRET);
    }

    @Test
    public void name() {
        target.authenticate(USERNAME,PASSWORD);
    }
}
