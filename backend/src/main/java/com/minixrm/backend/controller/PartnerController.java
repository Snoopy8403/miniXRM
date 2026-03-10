package com.minixrm.backend.controller;

import com.minixrm.backend.dto.partner.PartnerCreateDto;
import com.minixrm.backend.dto.partner.PartnerResponseDto;
import com.minixrm.backend.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partners")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @GetMapping
    public List<PartnerResponseDto> getAll() {
        return partnerService.getAll();
    }

    @GetMapping("/{id}")
    public PartnerResponseDto getById(@PathVariable Long id) {
        return partnerService.getById(id);
    }

    @PostMapping
    public PartnerResponseDto create(@RequestBody PartnerCreateDto dto) {
        return partnerService.create(dto);
    }

    @PutMapping("/{id}")
    public PartnerResponseDto update(@PathVariable Long id, @RequestBody PartnerCreateDto dto) {
        return partnerService.update(id, dto);
    }

    @PatchMapping("/{id}/toggle-status")
    public PartnerResponseDto toggleStatus(@PathVariable Long id) {
        return partnerService.toggleStatus(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        partnerService.delete(id);
    }
}