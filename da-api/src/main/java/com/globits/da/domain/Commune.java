package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Commune extends BaseObject {
    private String name;

    @ManyToOne
    private District district;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
