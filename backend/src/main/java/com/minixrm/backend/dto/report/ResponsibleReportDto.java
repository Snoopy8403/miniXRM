package com.minixrm.backend.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponsibleReportDto {

    private String responsibleName;

    private Long totalMinutes;

    private Long partnerCount;

}