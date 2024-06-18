package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.domain.Province;

import java.util.List;

public class ProvinceDto extends BaseObject {
    private String name;
    private String code;
    private List<DistrictDto> districtDtos;

    public ProvinceDto() {
        super();
    }

    public ProvinceDto(Province entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
            this.code = entity.getCode();
        }
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DistrictDto> getDistricts() {
        return districtDtos;
    }

    public void setDistricts(List<DistrictDto> districtDtos) {
        this.districtDtos = districtDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
