package com.globits.da.validator.province;

import com.globits.da.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class ProvinceExistValidator implements ConstraintValidator<ProvinceExists, UUID> {
    private final ProvinceRepository provinceRepository;

    @Autowired
    public ProvinceExistValidator(ProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public boolean isValid(UUID districtId, ConstraintValidatorContext context) {
        return districtId != null && provinceRepository.existsById(districtId);
    }
}
