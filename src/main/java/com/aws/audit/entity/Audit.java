package com.aws.audit.entity;

import com.aws.audit.entity.converter.JsonNodeConverter;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbConvertedBy;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@DynamoDbBean
public class Audit {

    private String auditId;
    private String appId;
    private String subject;
    private String action;
    private JsonNode data;
    private Instant timestamp;

    public Audit() {

    }

    @DynamoDbPartitionKey
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @DynamoDbConvertedBy(value = JsonNodeConverter.class)
    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }


    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

}
