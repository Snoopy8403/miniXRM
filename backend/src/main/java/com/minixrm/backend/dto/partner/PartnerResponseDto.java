package com.minixrm.backend.dto.partner;

import com.minixrm.backend.model.enums.PartnerStatus;
import lombok.Data;

import java.util.Set;

@Data
public class PartnerResponseDto {
    private Long id;
    private String name;
    private String taxNumber;
    private String address;
    private PartnerStatus status;
    private Set<String> qualifications;
}
