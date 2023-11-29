package com.aws.audit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SearchAuditRequest {
    private SearchCriteria searchCriteria;
}
