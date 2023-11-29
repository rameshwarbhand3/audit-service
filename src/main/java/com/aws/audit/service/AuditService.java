package com.aws.audit.service;

import com.aws.audit.dto.*;
import com.aws.audit.entity.Audit;
import com.aws.audit.mapper.AuditMapper;
import com.aws.audit.repository.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AuditService {
    @Autowired
    private AuditRepository auditRepository;

    public CreateAuditResponse create(CreateAuditRequest createAuditRequest) {
        Audit audit = AuditMapper.mapToAudit(createAuditRequest);
        auditRepository.save(audit);
        return CreateAuditResponse.builder()
                .auditId(audit.getAuditId())
                .build();
    }

    public SearchAuditResponse search(SearchAuditRequest searchAuditRequest) {
        List<Audit> auditList = auditRepository.search(searchAuditRequest.getSearchCriteria());
//        List<AuditResponse> auditResponseList = new ArrayList<>();
//        for (Audit audit : auditList) {
//            auditResponseList.add(AuditMapper.mapToAuditResponse(audit));
//        }
        return SearchAuditResponse.builder()
                .audits(auditList.stream()
                        .map(audit -> AuditMapper.mapToAuditResponse(audit))
                        .toList())
                .build();
    }
}
