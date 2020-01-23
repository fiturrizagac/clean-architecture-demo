package com.befitca.cleanarch.application;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="demo.adapter.cognito")
@Data
public class CognitoProperties {
    private String region;
    private String clientId;
    private String poolId;
    private String awsAccessKey;
    private String awsSecretKey;
}
