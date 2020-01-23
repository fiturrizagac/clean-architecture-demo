package com.befitca.cleanarch.usecase.service;

import com.befitca.cleanarch.usecase.port.in.LoginCredentials;
import com.befitca.cleanarch.usecase.port.in.LoginUseCase;
import com.befitca.cleanarch.usecase.port.out.UserAuthenticator;

public class LoginServiceImpl implements LoginUseCase {

    private UserAuthenticator userAuthenticator;

    public LoginServiceImpl(final UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }

    public boolean login(final LoginCredentials loginCredentials) {

        userAuthenticator.authenticate(loginCredentials.getUsername(), loginCredentials.getPassword());
        // validar credenciales

        return false;
    }

}