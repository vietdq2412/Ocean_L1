package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProvinceServiceImpl extends GenericServiceImpl<Province, UUID> implements ProvinceService {
    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceServiceImpl(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

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

    @Override
    public Page<ProvinceDto> findAllProvinceDto(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        return provinceRepository.findAllProvinceDto(pageable);
    }

}
