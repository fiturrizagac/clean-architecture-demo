package com.befitca.cleanarch.adapter.cognito;

import lombok.Data;

@Data
public class CognitoParams {
    private String region;
    private String clientId;
    private String poolId;
    private String awsAccessKey;
    private String awsSecretKey;
}
