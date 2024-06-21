package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Province;
import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProvinceServiceImpl extends GenericServiceImpl<Province, UUID> implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;


    @Override
    public Province findOneById(UUID id) {
        return provinceRepository.findById(id).orElse(null);
    }

    @Override
    public Province update(UUID id, ProvinceDto provinceDto) {
        if (provinceDto != null && id != null) {
            Province province = provinceRepository.findById(id).orElse(null);
            if (province != null) {
                province.setName(provinceDto.getName());
                return provinceRepository.save(province);
            }
        }
        return null;
    }

}
