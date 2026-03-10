package com.minixrm.backend.service;

import com.minixrm.backend.dto.partner.PartnerCreateDto;
import com.minixrm.backend.dto.partner.PartnerResponseDto;
import com.minixrm.backend.mapper.PartnerMapper;
import com.minixrm.backend.model.entity.Partner;
import com.minixrm.backend.repository.PartnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PartnerServiceTest {

    @Mock
    PartnerRepository partnerRepository;

    @Mock
    PartnerMapper partnerMapper;

    @InjectMocks
    PartnerService partnerService;

    @Test
    void createPartner() {

        PartnerCreateDto dto = new PartnerCreateDto();
        Partner partner = new Partner();
        PartnerResponseDto response = new PartnerResponseDto();

        when(partnerMapper.toEntity(dto)).thenReturn(partner);
        when(partnerRepository.save(partner)).thenReturn(partner);
        when(partnerMapper.toDto(partner)).thenReturn(response);

        partnerService.create(dto);

        verify(partnerRepository).save(partner);
    }
}