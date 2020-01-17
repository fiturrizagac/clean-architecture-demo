package com.befitca.cleanarch.adapter.cognito;


import org.junit.Before;
import org.junit.Test;

public class UserAuthenticatorCognitoImplTest {

    private UserAuthenticatorCognitoImpl target;

    @Before
    public void setUp() {
        target = new UserAuthenticatorCognitoImpl();
    }

    @Test
    public void name() {
        target.authenticate("username","password");
    }
}
