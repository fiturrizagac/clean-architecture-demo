package com.befitca.cleanarch.adapter.cognito;

import com.befitca.cleanarch.usecase.port.out.UserAuthenticator;

public class UserAuthenticatorCognitoImpl implements UserAuthenticator {

    @Override
    public boolean authenticate(final String username, final String password) {
        return false;
    }
}
