package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class CertificateType extends BaseObject {
    @NotNull(message = "Name is required!")
    String name;

    @OneToMany(mappedBy = "certificateType", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    Set<Certificate> certificates;

    public @NotNull(message = "Name is required!") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name is required!") String name) {
        this.name = name;
    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<Certificate> certificates) {
        this.certificates = certificates;
    }
}
