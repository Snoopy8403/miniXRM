package com.minixrm.backend.dto.activity;

import lombok.Data;

@Data
public class ActivityCreateDto {

    private String subject;

    private String type;

    private String description;

    private int durationMinutes;

    private String responsibleName;

    private Long partnerId;

}
