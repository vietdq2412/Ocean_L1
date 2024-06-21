package com.globits.da.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DistrictExistsValidator.class)
public @interface DistrictExists {
    String message() default "District not found with provided id";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}