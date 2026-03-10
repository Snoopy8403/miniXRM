package com.minixrm.backend.service;

import com.minixrm.backend.dto.partner.PartnerCreateDto;
import com.minixrm.backend.dto.partner.PartnerResponseDto;
import com.minixrm.backend.exception.NotFoundException;
import com.minixrm.backend.mapper.PartnerMapper;
import com.minixrm.backend.model.entity.Partner;
import com.minixrm.backend.model.enums.PartnerStatus;
import com.minixrm.backend.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    public List<PartnerResponseDto> getAll() {

        return partnerRepository.findAll()
                .stream()
                .map(partnerMapper::toDto)
                .toList();
    }

    public PartnerResponseDto create(PartnerCreateDto dto) {

        Partner partner = partnerMapper.toEntity(dto);

        Partner saved = partnerRepository.save(partner);

        return partnerMapper.toDto(saved);
    }

    public PartnerResponseDto getById(Long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Partner not found"));

        return partnerMapper.toDto(partner);
    }

    public PartnerResponseDto update(Long id, PartnerCreateDto dto) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Partner not found"));
        partner.setName(dto.getName());
        partner.setTaxNumber(dto.getTaxNumber());
        partner.setAddress(dto.getAddress());
        partner.setStatus(dto.getStatus());
        Partner updatedPartner = partnerMapper.toEntity(dto);
        partner.setQualifications(updatedPartner.getQualifications());
        Partner saved = partnerRepository.save(partner);

        return partnerMapper.toDto(saved);
    }

    public PartnerResponseDto toggleStatus(Long id) {

        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Partner not found"));

        if (partner.getStatus() == PartnerStatus.ACTIVE) {
            partner.setStatus(PartnerStatus.INACTIVE);
        } else {
            partner.setStatus(PartnerStatus.ACTIVE);
        }

        Partner saved = partnerRepository.save(partner);

        return partnerMapper.toDto(saved);
    }

    public void delete(Long id) {
        if (!partnerRepository.existsById(id)) {
            throw new NotFoundException("Partner not found");
        }
        partnerRepository.deleteById(id);
    }
}