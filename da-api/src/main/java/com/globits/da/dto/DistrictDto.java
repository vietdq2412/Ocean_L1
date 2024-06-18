package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.domain.District;

import java.util.List;


public class DistrictDto extends BaseObject {
    private String name;

    private ProvinceDto provinceDto;

    private List<CommuneDto> communeDtos;

    public DistrictDto() {
        super();
    }

    public DistrictDto(District entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
            this.provinceDto = new ProvinceDto(entity.getProvince());
        }
    }

    public List<CommuneDto> getCommunes() {
        return communeDtos;
    }

    public void setCommunes(List<CommuneDto> communeDtos) {
        this.communeDtos = communeDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProvinceDto getProvince() {
        return provinceDto;
    }

    public void setProvince(ProvinceDto provinceDto) {
        this.provinceDto = provinceDto;
    }
}
