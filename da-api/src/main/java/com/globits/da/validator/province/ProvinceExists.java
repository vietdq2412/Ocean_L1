package com.globits.da.validator.province;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProvinceExistValidator.class)
public @interface ProvinceExists {
    String message() default "Province not found with provided id";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
