package com.minixrm.backend.service;

import com.minixrm.backend.dto.activity.ActivityCreateDto;
import com.minixrm.backend.model.entity.Activity;
import com.minixrm.backend.model.entity.Partner;
import com.minixrm.backend.model.enums.PartnerStatus;
import com.minixrm.backend.repository.ActivityRepository;
import com.minixrm.backend.repository.PartnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    ActivityRepository activityRepository;

    @Mock
    PartnerRepository partnerRepository;

    @InjectMocks
    ActivityService activityService;

    @Test
    void createActivity() {

        Partner partner = new Partner();
        partner.setStatus(PartnerStatus.ACTIVE);

        ActivityCreateDto dto = new ActivityCreateDto();
        dto.setPartnerId(1L);
        dto.setDurationMinutes(10);

        when(partnerRepository.findById(1L)).thenReturn(Optional.of(partner));
        when(activityRepository.save(any())).thenReturn(new Activity());

        activityService.create(dto);

        verify(activityRepository).save(any());
    }
}