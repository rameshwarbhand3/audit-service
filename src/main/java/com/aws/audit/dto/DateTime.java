package com.aws.audit.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class DateTime {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate startDate;

   @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;
}
