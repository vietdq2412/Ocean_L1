package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Category;
import com.globits.da.domain.Commune;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

public class CommuneDto extends BaseObjectDto {
    private String name;

    private DistrictDto districtDto;

    public CommuneDto() {
        super();
    }
    public CommuneDto(Commune entity) {
        if(entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
            this.districtDto = new DistrictDto(entity.getDistrict());
        }
    }

    public DistrictDto getDistrict() {
        return districtDto;
    }

    public void setDistrict(DistrictDto districtDto) {
        this.districtDto = districtDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
