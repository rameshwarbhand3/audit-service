package com.aws.audit.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class CreateAuditRequest {
    private String appId;
    private String subject;
    private String action;
    private JsonNode data;
}
