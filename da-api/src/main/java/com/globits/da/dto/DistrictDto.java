package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.validator.province.ProvinceExists;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DistrictDto extends BaseObject {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "provinceId is required")
    @ProvinceExists
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

            this.setCreateDate(entity.getCreateDate());
            this.setModifyDate(entity.getModifyDate());
            this.setCreatedBy(entity.getCreatedBy());

            if (entity.getCommunes() != null) {
                this.communeDtos = getCommuneDtos(entity.getCommunes());
            }
        }
    }

    private List<CommuneDto> getCommuneDtos(List<Commune> communes) {
        if (communes != null) {
            List<CommuneDto> dtos = new ArrayList<>();
            for (Commune commune : communes) {
                dtos.add(new CommuneDto(commune));
            }
            return dtos;
        }
        return null;
    }

    public @NotBlank(message = "Name is required") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public @NotNull(message = "provinceId is required") UUID getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(@NotNull(message = "provinceId is required") UUID provinceId) {
        this.provinceId = provinceId;
    }

    public List<CommuneDto> getCommuneDtos() {
        return communeDtos;
    }

    public void setCommuneDtos(List<CommuneDto> communeDtos) {
        this.communeDtos = communeDtos;
    }
}
