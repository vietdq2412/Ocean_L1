package com.globits.da.dto;

import com.globits.core.domain.BaseObject;
import com.globits.da.domain.District;
import com.globits.da.validator.DistrictExists;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class DistrictDto extends BaseObject {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "provinceId is required")
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

    public List<CommuneDto> getCommuneDtos() {
        return communeDtos;
    }

    public void setCommuneDtos(List<CommuneDto> communeDtos) {
        this.communeDtos = communeDtos;
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
}
