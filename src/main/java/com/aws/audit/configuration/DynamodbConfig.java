package com.aws.audit.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix="aws.dynamodb")
public class DynamodbConfig {
    private String tableName;
    private String region;
    private  String endpoint;
}
