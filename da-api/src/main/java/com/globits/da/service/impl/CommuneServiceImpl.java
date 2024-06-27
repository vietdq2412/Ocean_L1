package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.dto.CommuneDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommuneServiceImpl extends GenericServiceImpl<Commune, UUID> implements CommuneService {
    private final CommuneRepository communeRepository;
    private final DistrictRepository districtRepository;

    @Autowired
    public CommuneServiceImpl(CommuneRepository communeRepository, DistrictRepository districtRepository) {
        this.communeRepository = communeRepository;
        this.districtRepository = districtRepository;
    }

    @Override
    public Commune findOneById(UUID id) {
        return communeRepository.findById(id).orElse(null);
    }

    @Override
    public Commune update(UUID id, CommuneDto communeDto) {
        if (communeDto != null && id != null) {
            Commune commune = communeRepository.findById(id).orElse(null);
            if (commune != null) {
                commune.setName(communeDto.getName());
                return communeRepository.save(commune);
            }
        }
        return null;
    }

    @Override
    public ApiResponse<CommuneDto> save(CommuneDto communeDto) {
        ApiResponse<CommuneDto> apiResponse = new ApiResponse<>();
        Commune commune = new Commune();

        District district = districtRepository.findById(communeDto.getDistrictId()).orElse(null);
        if (district == null) {
            apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrorMessage("District not found with id: " + communeDto.getDistrictId());
            return apiResponse;
        }

        commune.setName(communeDto.getName());
        commune.setDistrict(district);

        apiResponse.setData(new CommuneDto(communeRepository.save(commune)));
        apiResponse.setHttpStatus(HttpStatus.CREATED);
        return apiResponse;
    }


}
