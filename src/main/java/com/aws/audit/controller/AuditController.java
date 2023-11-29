package com.aws.audit.controller;

import com.aws.audit.dto.*;
import com.aws.audit.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuditController {
    @Autowired
    private AuditService auditService;

    @PostMapping("/audits")
    public ResponseEntity<CreateAuditResponse>save(@RequestBody CreateAuditRequest createAuditRequest){
        CreateAuditResponse createAuditResponse = auditService.create(createAuditRequest);
        return new ResponseEntity<>(createAuditResponse, HttpStatus.CREATED);
    }
    @PostMapping("/audits/search")
    public ResponseEntity<SearchAuditResponse>search(@RequestBody SearchAuditRequest searchAuditRequest){
        SearchAuditResponse searchAuditResponse = auditService.search(searchAuditRequest);
        return new ResponseEntity<>(searchAuditResponse,HttpStatus.OK);
    }
}
