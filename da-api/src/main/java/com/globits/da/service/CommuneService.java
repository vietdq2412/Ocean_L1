package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Commune;
import com.globits.da.dto.CommuneDto;
import com.globits.da.rest.response.ApiResponse;

import java.util.UUID;

public interface CommuneService extends GenericService<Commune, UUID> {
    Commune findOneById(UUID id);

    Commune update(UUID id, CommuneDto communeDto);

    ApiResponse<CommuneDto> save(CommuneDto communeDto);
}
