package com.globits.da.validator;

import com.globits.da.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class DistrictExistsValidator implements ConstraintValidator<DistrictExists, UUID> {
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public boolean isValid(UUID districtId, ConstraintValidatorContext context) {
        return districtId != null && districtRepository.existsById(districtId);
    }


}
