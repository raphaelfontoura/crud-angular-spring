package com.rdeveloper.crudspring.validation;

import java.util.List;
import java.util.stream.Stream;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, String> {

    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
        this.acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(e -> {
                    try {
                        return (String) e.getClass().getMethod("getValue").invoke(e);
                    } catch (Exception ex) {
                        return e.name();
                    }
                })
                .toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value);
    }

}
