package com.minixrm.backend.dto.activity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityResponseDto {

    private Long id;
    private String subject;
    private String type;
    private String description;
    private int durationMinutes;
    private String responsibleName;

}