package com.minixrm.backend.service;

import com.minixrm.backend.repository.ActivityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Mock
    ActivityRepository activityRepository;

    @InjectMocks
    ReportService reportService;

    @Test
    void getReport() {

        reportService.getReport();

        verify(activityRepository).getResponsibleReport();
    }
}