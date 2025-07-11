package com.rdeveloper.crudspring.validation;

import org.springframework.data.domain.Pageable;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PageableSizeValidator implements ConstraintValidator<ValidPageableSize, Pageable> {

    private static final int MAX_PAGE_SIZE = 50;

    @Override
    public void initialize(ValidPageableSize constraintAnnotation) {
    }

    @Override
    public boolean isValid(Pageable page, ConstraintValidatorContext context) {
        if (page == null) {
            return true;
        }
        return page.getPageSize() <= MAX_PAGE_SIZE;
    }

}
