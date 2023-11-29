package com.aws.audit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CreateAuditResponse {
    private String auditId;
}

