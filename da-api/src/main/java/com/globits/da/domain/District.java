package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.*;
import java.util.List;

@Entity
public class District extends BaseObject {
    private String name;

    @ManyToOne
    private Province province;

    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL , orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Commune> communes;

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        if (communes != null && this.communes != null ) {
            this.communes.clear();
            this.communes.addAll(communes);
        }else {
            this.communes = communes;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }
}
