package com.befitca.cleanarch.application;

import com.befitca.cleanarch.adapter.cognito.CognitoParams;
import com.befitca.cleanarch.adapter.cognito.UserAuthenticatorCognitoImpl;
import com.befitca.cleanarch.usecase.port.out.UserAuthenticator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.befitca.cleanarch.adapter.web"})
public class AdapterBeanConfiguration {

    @ConfigurationProperties(prefix="demo-app.adapter.cognito")
    @Bean
    public CognitoParams cognitoParams() {
        return new CognitoParams();
    }

    @Bean
    public UserAuthenticator userAuthenticator(CognitoParams params) {
        return new UserAuthenticatorCognitoImpl(params);
    }
}
