package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDto;

import java.util.UUID;

public interface DistrictService extends GenericService<District, UUID> {
    District findDistrictByProvinceId(UUID provinceId);

    District findOneById(UUID id);

    District update(UUID id, DistrictDto districtDto);
}
