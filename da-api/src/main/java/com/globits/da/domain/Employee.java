package com.globits.da.domain;

import com.globits.core.domain.BaseObject;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Entity
public class Employee extends BaseObject {
    private static final long serialVersionUID = 1L;
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
