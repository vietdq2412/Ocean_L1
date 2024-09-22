package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Certificate;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

public class CertificateDto extends BaseObjectDto {
    @NotNull(message = "title is required!")
    private String title;

    @NotNull(message = "dateOfIssuance is required!")
    private Date dateOfIssuance;

    @NotNull(message = "expirationDate is required!")
    private Date expirationDate;

    @NotNull(message = "employeeId is required!")
    private UUID employeeId;

    @NotNull(message = "provinceId is required!")
    private UUID provinceId;

    @NotNull(message = "certificateTypeId is required!")
    private UUID certificateTypeId;

    public CertificateDto(Certificate entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.title = entity.getTitle();
            this.dateOfIssuance = entity.getDateOfIssuance();
            this.expirationDate = entity.getExpirationDate();
            this.provinceId = entity.getProvince().getId();
            this.employeeId = entity.getEmployee().getId();

            this.setCreateDate(entity.getCreateDate());
            this.setModifyDate(entity.getModifyDate());
            this.setCreatedBy(entity.getCreatedBy());
        }
    }

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

    public @NotNull(message = "employeeId is required!") UUID getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@NotNull(message = "employeeId is required!") UUID employeeId) {
        this.employeeId = employeeId;
    }

    public @NotNull(message = "provinceId is required!") UUID getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(@NotNull(message = "provinceId is required!") UUID provinceId) {
        this.provinceId = provinceId;
    }

    public @NotNull(message = "certificateTypeId is required!") UUID getCertificateTypeId() {
        return certificateTypeId;
    }

    public void setCertificateTypeId(@NotNull(message = "certificateTypeId is required!") UUID certificateTypeId) {
        this.certificateTypeId = certificateTypeId;
    }
}
