package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Certificate extends BaseObject {
    @NotNull(message = "title is required!")
    private String title;

    @NotNull(message = "dateOfIssuance is required!")
    private Date dateOfIssuance;

    @NotNull(message = "expirationDate is required!")
    private Date expirationDate;

    @NotNull(message = "employee is required!")
    @ManyToOne
    private Employee employee;

    @NotNull(message = "province is required!")
    @ManyToOne
    private Province province;

    @NotNull(message = "certificateType is required!")
    @ManyToOne
    private CertificateType certificateType;

    public @NotNull(message = "title is required!") String getTitle() {
        return title;
    }

    public void setTitle(@NotNull(message = "title is required!") String title) {
        this.title = title;
    }

    public @NotNull(message = "dateOfIssuance is required!") Date getDateOfIssuance() {
        return dateOfIssuance;
    }

    public void setDateOfIssuance(@NotNull(message = "dateOfIssuance is required!") Date dateOfIssuance) {
        this.dateOfIssuance = dateOfIssuance;
    }

    public @NotNull(message = "expirationDate is required!") Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(@NotNull(message = "expirationDate is required!") Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public @NotNull(message = "employee is required!") Employee getEmployee() {
        return employee;
    }

    public void setEmployee(@NotNull(message = "employee is required!") Employee employee) {
        this.employee = employee;
    }

    public @NotNull(message = "province is required!") Province getProvince() {
        return province;
    }

    public void setProvince(@NotNull(message = "province is required!") Province province) {
        this.province = province;
    }

    public @NotNull(message = "certificateType is required!") CertificateType getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(@NotNull(message = "certificateType is required!") CertificateType certificateType) {
        this.certificateType = certificateType;
    }
}
