package com.aws.audit.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;


@Configuration
public class AuditConfig {
    @Autowired
    private DynamodbConfig dynamodbConfig;

    @Bean
    public DynamoDbClient dynamoDbClient(DynamodbConfig dynamodbConfig) {
        return DynamoDbClient.builder()
                .region(Region.of(dynamodbConfig.getRegion()))
                .build();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

}
