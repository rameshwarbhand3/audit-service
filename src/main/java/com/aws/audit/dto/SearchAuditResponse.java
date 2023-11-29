package com.aws.audit.dto;

import com.aws.audit.entity.Audit;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class SearchAuditResponse {
    private List<AuditResponse> audits;

}
