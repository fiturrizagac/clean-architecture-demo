package com.befitca.cleanarch.usecase.port.out;

public interface UserAuthenticator {

    boolean authenticate(String username, String password);

}
