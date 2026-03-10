package com.minixrm.backend.controller;

import com.minixrm.backend.dto.activity.ActivityCreateDto;
import com.minixrm.backend.dto.activity.ActivityResponseDto;
import com.minixrm.backend.model.entity.Activity;
import com.minixrm.backend.service.ActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping("/activities")
    public ActivityResponseDto create(@RequestBody ActivityCreateDto dto) {
        return activityService.create(dto);
    }

    @GetMapping("/partners/{partnerId}/activities")
    public List<ActivityResponseDto> getPartnerActivities(@PathVariable Long partnerId) {
        return activityService.getByPartnerId(partnerId);
    }

}