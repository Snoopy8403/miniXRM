package com.minixrm.backend.service;

import com.minixrm.backend.dto.activity.ActivityCreateDto;
import com.minixrm.backend.dto.activity.ActivityResponseDto;
import com.minixrm.backend.exception.BusinessException;
import com.minixrm.backend.exception.NotFoundException;
import com.minixrm.backend.model.entity.Activity;
import com.minixrm.backend.model.entity.Partner;
import com.minixrm.backend.model.enums.PartnerStatus;
import com.minixrm.backend.repository.ActivityRepository;
import com.minixrm.backend.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository activityRepository;
    private final PartnerRepository partnerRepository;

    public ActivityResponseDto create(ActivityCreateDto dto) {

        Partner partner = partnerRepository.findById(dto.getPartnerId())
                .orElseThrow(() -> new NotFoundException("Partner not found"));

        if (partner.getStatus() == PartnerStatus.INACTIVE) {
            throw new BusinessException("Cannot add activity to inactive partner");
        }

        if (dto.getDurationMinutes() <= 0) {
            throw new BusinessException("Duration must be positive");
        }

        Activity activity = new Activity();

        activity.setSubject(dto.getSubject());
        activity.setType(dto.getType());
        activity.setDescription(dto.getDescription());
        activity.setDurationMinutes(dto.getDurationMinutes());
        activity.setResponsibleName(dto.getResponsibleName());
        activity.setPartner(partner);

        Activity saved = activityRepository.save(activity);

        return new ActivityResponseDto(
                saved.getId(),
                saved.getSubject(),
                saved.getType(),
                saved.getDescription(),
                saved.getDurationMinutes(),
                saved.getResponsibleName()
        );
    }


    public List<ActivityResponseDto> getByPartnerId(Long partnerId) {

        partnerRepository.findById(partnerId)
                .orElseThrow(() -> new NotFoundException("Partner not found"));

        return activityRepository.findByPartnerId(partnerId)
                .stream()
                .map(a -> new ActivityResponseDto(
                        a.getId(),
                        a.getSubject(),
                        a.getType(),
                        a.getDescription(),
                        a.getDurationMinutes(),
                        a.getResponsibleName()
                ))
                .toList();
    }

}