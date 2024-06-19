package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Category;
import com.globits.da.domain.Commune;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

public class CommuneDto extends BaseObjectDto {
    private String name;
    private UUID districtId;

    public CommuneDto() {
        super();
    }
    public CommuneDto(Commune entity) {
        if(entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
            this.districtId = entity.getDistrict().getId();
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
