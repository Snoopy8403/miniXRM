package com.minixrm.backend.controller;

import com.minixrm.backend.dto.report.ResponsibleReportDto;
import com.minixrm.backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/responsibles")
    public List<ResponsibleReportDto> getReport() {
        return reportService.getReport();
    }

}
