package com.minixrm.backend.service;

import com.minixrm.backend.dto.report.ResponsibleReportDto;
import com.minixrm.backend.repository.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ActivityRepository activityRepository;

    public List<ResponsibleReportDto> getReport() {
        return activityRepository.getResponsibleReport();
    }

}
