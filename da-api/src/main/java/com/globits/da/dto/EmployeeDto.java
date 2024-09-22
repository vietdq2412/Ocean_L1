package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Employee;
import com.globits.da.validator.district.DistrictExists;
import com.globits.da.validator.province.ProvinceExists;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

public class EmployeeDto extends BaseObjectDto {
    @NotBlank(message = "Code is required")
    @Pattern(regexp = "\\S{6,10}", message = "Code must be between 6 and 10 characters and contain no spaces")
    private String code;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Invalid email format (e.g. email@email.com")
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "\\d{1,11}", message = "Phone number should be numeric and not longer than 11 digits")
    private String phone;

    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age must be > 0")
    private Integer age;

    @NotNull(message = "communeId is required")
    private UUID communeId;

    @NotNull(message = "districtId is required")
    @DistrictExists
    private UUID districtId;

    @NotNull(message = "provinceId is required")
    @ProvinceExists
    private UUID provinceId;

    public EmployeeDto() {
        super();
    }

    public EmployeeDto(Employee entity) {
        if (entity != null) {
            this.setId(entity.getId());
            this.code = entity.getCode();
            this.name = entity.getName();
            this.email = entity.getEmail();
            this.phone = entity.getPhone();
            this.age = entity.getAge();

            this.communeId = entity.getCommune().getId();
            this.districtId = entity.getDistrict().getId();
            this.provinceId = entity.getProvince().getId();

            this.setCreateDate(entity.getCreateDate());
            this.setModifyDate(entity.getModifyDate());
            this.setCreatedBy(entity.getCreatedBy());
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UUID getCommuneId() {
        return communeId;
    }

    public void setCommuneId(UUID communeId) {
        this.communeId = communeId;
    }

    public UUID getDistrictId() {
        return districtId;
    }

    public void setDistrictId(UUID districtId) {
        this.districtId = districtId;
    }

    public UUID getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(UUID provinceId) {
        this.provinceId = provinceId;
    }
}
