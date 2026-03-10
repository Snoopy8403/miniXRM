package com.minixrm.backend.mapper;

import com.minixrm.backend.dto.activity.ActivityResponseDto;
import com.minixrm.backend.model.entity.Activity;
import org.springframework.stereotype.Component;

@Component
public class ActivityMapper {

    public ActivityResponseDto toDto(Activity activity) {

        ActivityResponseDto dto = new ActivityResponseDto();

        dto.setId(activity.getId());
        dto.setSubject(activity.getSubject());
        dto.setType(activity.getType());
        dto.setDescription(activity.getDescription());
        dto.setDurationMinutes(activity.getDurationMinutes());
        dto.setResponsibleName(activity.getResponsibleName());

        return dto;
    }

}
