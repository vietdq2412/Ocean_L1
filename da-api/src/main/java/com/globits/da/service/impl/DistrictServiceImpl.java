package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.CommuneDto;
import com.globits.da.dto.DistrictDto;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DistrictServiceImpl extends GenericServiceImpl<District, UUID> implements DistrictService {
    private final DistrictRepository districtRepository;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public District findDistrictByProvinceId(UUID provinceId) {
        return null;
    }

    @Override
    public Page<DistrictDto> getAllDistrict(int pageIndex, int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        return districtRepository.findAllDistrict(pageable);
    }

    @Override
    public District findOneById(UUID id) {
        return districtRepository.findById(id).orElse(null);
    }

    @Override
    public DistrictDto update(UUID id, DistrictDto districtDto) {
        if (districtDto != null && id != null) {
            District district = districtRepository.findById(id).orElse(null);
            if (district != null) {
                district.setName(districtDto.getName());
                return new DistrictDto(districtRepository.save(district));
            }
        }
    }

    @Override
    public ApiResponse<DistrictDto> save(DistrictDto districtDto) {
        ApiResponse<DistrictDto> apiResponse = new ApiResponse<>();

        District district = new District();
        Province province = new Province();
        province.setId(districtDto.getProvinceId());

        district.setName(districtDto.getName());
        district.setProvince(province);

        if (districtDto.getCommuneDtos() != null) {
            List<Commune> communes = new ArrayList<>();
            for (CommuneDto communeDto : districtDto.getCommuneDtos()) {
                Commune commune = new Commune();
                commune.setName(communeDto.getName());
                communes.add(commune);
            }
            district.setCommunes(communes);
        }

        apiResponse.setData(new DistrictDto(districtRepository.save(district)));
        apiResponse.setHttpStatus(HttpStatus.OK);
        return apiResponse;
    }
}
