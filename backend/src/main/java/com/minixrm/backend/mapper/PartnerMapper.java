package com.minixrm.backend.mapper;

import com.minixrm.backend.dto.partner.PartnerCreateDto;
import com.minixrm.backend.dto.partner.PartnerResponseDto;
import com.minixrm.backend.model.entity.Partner;
import com.minixrm.backend.model.entity.Qualification;
import com.minixrm.backend.repository.QualificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PartnerMapper {

    private final QualificationRepository qualificationRepository;

    public Partner toEntity(PartnerCreateDto dto) {

        Partner partner = new Partner();

        partner.setName(dto.getName());
        partner.setTaxNumber(dto.getTaxNumber());
        partner.setAddress(dto.getAddress());
        partner.setStatus(dto.getStatus());

        if(dto.getQualifications() != null) {

            Set<Qualification> qualifications =
                    dto.getQualifications()
                            .stream()
                            .map(name -> qualificationRepository
                                    .findByName(name)
                                    .orElseThrow())
                            .collect(Collectors.toSet());

            partner.setQualifications(qualifications);
        }

        return partner;
    }

    public PartnerResponseDto toDto(Partner partner) {

        PartnerResponseDto dto = new PartnerResponseDto();

        dto.setId(partner.getId());
        dto.setName(partner.getName());
        dto.setTaxNumber(partner.getTaxNumber());
        dto.setAddress(partner.getAddress());
        dto.setStatus(partner.getStatus());

        dto.setQualifications(
                partner.getQualifications()
                        .stream()
                        .map(Qualification::getName)
                        .collect(Collectors.toSet())
        );

        return dto;
    }

}
