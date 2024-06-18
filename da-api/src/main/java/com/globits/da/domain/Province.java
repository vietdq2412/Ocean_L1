package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Province extends BaseObject {
    private String name;
    private String code;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL , orphanRemoval = true,fetch = FetchType.LAZY)
    private List<District> districts;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
