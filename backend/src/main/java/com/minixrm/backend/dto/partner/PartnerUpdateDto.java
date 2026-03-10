package com.minixrm.backend.dto.partner;

import com.minixrm.backend.model.enums.PartnerStatus;
import lombok.Data;

@Data
public class PartnerUpdateDto {

    private String name;

    private String address;

    private PartnerStatus status;

}
