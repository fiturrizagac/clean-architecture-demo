package com.befitca.cleanarch.application;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.befitca.cleanarch.adapter.cognito.UserAuthenticatorCognitoImpl;
import com.befitca.cleanarch.usecase.port.out.UserAuthenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.befitca.cleanarch.adapter.web"})
public class AdapterBeanConfiguration {

    @Bean
    public UserAuthenticator userAuthenticator(CognitoProperties properties) {
        AWSCredentials credentials = new BasicAWSCredentials(properties.getAwsAccessKey(),properties.getAwsSecretKey());
        return new UserAuthenticatorCognitoImpl(properties.getClientId(),properties.getPoolId(),properties.getRegion(),credentials);
    }
}
