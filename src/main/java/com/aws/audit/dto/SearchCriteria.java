package com.aws.audit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class SearchCriteria {
    private String appId;
    private String action;
    private String subject;
    private DateTime dateTime;
}
