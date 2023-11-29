package com.aws.audit.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class AuditResponse {

    private String auditId;
    private String appId;
    private String subject;
    private String action;
    private JsonNode data;
    private Instant timestamp;
}
