package org.tattour.server.global.config.resolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.tattour.server.global.util.validator.DivisibleByValidator;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DivisibleByValidator.class)
public @interface DivisibleBy {

    String message() default "값은 1000으로 나누어 떨어져야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value() default 1000;
}