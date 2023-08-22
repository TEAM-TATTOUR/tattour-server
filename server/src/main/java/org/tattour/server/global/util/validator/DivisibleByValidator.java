package org.tattour.server.global.util.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.tattour.server.global.config.annotations.DivisibleBy;

public class DivisibleByValidator implements ConstraintValidator<DivisibleBy, Integer> {

    private int divisor;

    @Override
    public void initialize(DivisibleBy divisibleBy) {
        this.divisor = divisibleBy.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value % divisor == 0;
    }
}
