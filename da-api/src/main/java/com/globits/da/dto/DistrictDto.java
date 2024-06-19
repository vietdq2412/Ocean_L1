package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.domain.District;

import java.util.List;
import java.util.UUID;


public class DistrictDto extends BaseObject {
    private String name;
    private UUID provinceId;
    private List<CommuneDto> communeDtos;

    public DistrictDto() {
        super();
    }
    public DistrictDto(District entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.name = entity.getName();
            this.provinceId = entity.getProvince().getId();
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

    public List<CommuneDto> getCommuneDtos() {
        return communeDtos;
    }

    public void setCommuneDtos(List<CommuneDto> communeDtos) {
        this.communeDtos = communeDtos;
    }

    public UUID getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(UUID provinceId) {
        this.provinceId = provinceId;
    }
}
