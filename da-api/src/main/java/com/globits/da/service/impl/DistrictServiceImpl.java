package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDto;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DistrictServiceImpl extends GenericServiceImpl<District, UUID> implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;


    @Override
    public District findDistrictByProvinceId(UUID provinceId) {
        return null;
    }

    @Override
    public District findOneById(UUID id) {
        return districtRepository.findById(id).orElse(null);
    }

    @Override
    public District update(UUID id, DistrictDto districtDto) {
        if (districtDto != null && id != null) {
            District commune = districtRepository.findById(id).orElse(null);
            if (commune != null) {
                commune.setName(districtDto.getName());
                return districtRepository.save(commune);
            }
        }
        return null;
    }
}
