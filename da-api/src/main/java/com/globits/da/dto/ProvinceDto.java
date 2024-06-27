package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.domain.Province;

import javax.validation.constraints.NotBlank;
import java.util.List;


public class ProvinceDto extends BaseObject {
    @NotBlank(message = "Name is required!")
    private String name;
    private List<DistrictDto> districts;

    public ProvinceDto() {
        super();
    }

    public ProvinceDto(Province entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
            this.setCreateDate(entity.getCreateDate());
            this.setModifyDate(entity.getModifyDate());
            this.setCreatedBy(entity.getCreatedBy());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DistrictDto> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictDto> districts) {
        this.districts = districts;
    }
}
