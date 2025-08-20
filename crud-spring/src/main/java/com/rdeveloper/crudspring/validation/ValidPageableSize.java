package com.rdeveloper.crudspring.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PageableSizeValidator.class)
public @interface ValidPageableSize {
    String message() default "Page size must not be greater than 50";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}