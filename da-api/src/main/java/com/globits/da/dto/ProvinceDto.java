package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.domain.District;
import com.globits.da.domain.Province;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


public class ProvinceDto extends BaseObject {
    @NotBlank(message = "Name is required!")
    private String name;
    private List<DistrictDto> districtDtos;

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

            if (entity.getDistricts() != null) {
                this.districtDtos = getDistrictDtos(entity.getDistricts());
            }
        }
    }

    private List<DistrictDto> getDistrictDtos(List<District> districts) {
        if (districts != null) {
            List<DistrictDto> dtos = new ArrayList<>();
            for (District district : districts) {
                dtos.add(new DistrictDto(district));
            }
            return dtos;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DistrictDto> getDistrictDtos() {
        return districtDtos;
    }

    public void setDistrictDtos(List<DistrictDto> districtDtos) {
        this.districtDtos = districtDtos;
    }
}
