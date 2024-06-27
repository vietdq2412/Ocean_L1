package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Commune;
import com.globits.da.validator.district.DistrictExists;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class CommuneDto extends BaseObjectDto {
    @NotBlank(message = "Name is required!")
    private String name;

    @NotNull(message = "District Id is required!")
    @DistrictExists
    private UUID districtId;

    public CommuneDto() {
        super();
    }

    public CommuneDto(Commune entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
            this.districtId = entity.getDistrict().getId();

            this.setCreateDate(entity.getCreateDate());
            this.setModifyDate(entity.getModifyDate());
            this.setCreatedBy(entity.getCreatedBy());
        }
    }

    public UUID getDistrictId() {
        return districtId;
    }

    public void setDistrictId(UUID districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
