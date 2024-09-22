package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
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

    @Override
    public ApiResponse<ProvinceDto> save(ProvinceDto provinceDto) {
        ApiResponse<ProvinceDto> apiResponse = new ApiResponse<>();

        Province province = new Province();
        province.setName(provinceDto.getName());

        if (provinceDto.getDistrictDtos() != null) {
            List<District> districts = new LinkedList<>();
            for (DistrictDto districtDto : provinceDto.getDistrictDtos()) {
                District district = getDistrict(districtDto);
                district.setProvince(province);
                districts.add(district);
            }
            province.setDistricts(districts);
        }

        apiResponse.setData(new ProvinceDto(provinceRepository.save(province)));
        apiResponse.setHttpStatus(HttpStatus.OK);
        return apiResponse;
    }

    /**
     * Convert DistrictDto to District
     * @param districtDto
     * @return district entity
     */
    private District getDistrict(DistrictDto districtDto) {
        District district = new District();
        if (districtDto.getCommuneDtos() != null){
            List<Commune> communes = new LinkedList<>();
            for (CommuneDto communeDto : districtDto.getCommuneDtos()) {
                Commune commune = new Commune();
                commune.setName(communeDto.getName());
                commune.setDistrict(district);
                communes.add(commune);
            }
            district.setCommunes(communes);
        }

        district.setName(districtDto.getName());
        return district;
    }

    public List<DistrictDto> getDistrictsByProvinceId(UUID provinceId) {
        Province province = provinceRepository.findById(provinceId).orElse(null);
        if (province != null && province.getDistricts() != null) {
            List<DistrictDto> districtDtos = new ArrayList<>();
            for (District district : province.getDistricts()) {
                districtDtos.add(new DistrictDto(district));
            }
            return districtDtos;
        }
        return new ArrayList<>();
    }

}
