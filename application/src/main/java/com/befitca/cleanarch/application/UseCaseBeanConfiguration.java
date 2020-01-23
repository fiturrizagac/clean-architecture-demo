package com.befitca.cleanarch.application;

import com.befitca.cleanarch.usecase.port.in.LoginUseCase;
import com.befitca.cleanarch.usecase.port.out.UserAuthenticator;
import com.befitca.cleanarch.usecase.service.LoginServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfiguration {

    @Bean
    public LoginUseCase loginUseCase(final UserAuthenticator userAuthenticator) {
        return new LoginServiceImpl(userAuthenticator);
    }
}
