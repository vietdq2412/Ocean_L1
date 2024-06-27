package com.globits.da.dto;

import com.globits.core.dto.BaseObjectDto;
import com.globits.da.domain.Employee;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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

    @Min(value = 0, message = "Age must be > 0")
    private Integer age;

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
}
