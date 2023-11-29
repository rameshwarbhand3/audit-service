package com.aws.audit.mapper;

import com.aws.audit.dto.AuditResponse;
import com.aws.audit.dto.SearchCriteria;
import com.aws.audit.entity.Audit;
import com.aws.audit.dto.CreateAuditRequest;

import java.time.Instant;
import java.util.UUID;


public class AuditMapper {
    public static Audit mapToAudit(CreateAuditRequest createAuditRequest) {
        Audit audit = new Audit();
        audit.setAuditId(UUID.randomUUID().toString());
        audit.setAppId(createAuditRequest.getAppId());
        audit.setSubject(createAuditRequest.getSubject());
        audit.setData(createAuditRequest.getData());
        audit.setAction(createAuditRequest.getAction());
        audit.setTimestamp(Instant.now());
        return  audit;
    }
    public static AuditResponse mapToAuditResponse(Audit audit) {
       return  AuditResponse.builder()
                .auditId(audit.getAuditId())
                .appId(audit.getAppId())
                .action(audit.getAction())
                .data(audit.getData())
                .subject(audit.getSubject())
                .timestamp(audit.getTimestamp())
                .build();
    }
}
