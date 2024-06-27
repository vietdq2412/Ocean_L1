package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface ProvinceService extends GenericService<Province, UUID> {
    Province findOneById(UUID id);

    Province update(UUID id, ProvinceDto communeDto);

    Page<ProvinceDto> findAllProvinceDto(int pageIndex, int pageSize);
}
