package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDto;
import com.globits.da.rest.response.ApiResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface DistrictService extends GenericService<District, UUID> {
    District findDistrictByProvinceId(UUID provinceId);

    Page<DistrictDto> getAllDistrict(int pageIndex, int pageSize);

    District findOneById(UUID id);

    DistrictDto update(UUID id, DistrictDto districtDto);

    ApiResponse<DistrictDto> save(DistrictDto districtDto);
}
