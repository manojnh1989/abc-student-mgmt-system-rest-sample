package com.example.studentmgmtsystem.validator.annotation;

import com.example.studentmgmtsystem.validator.UniqueConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Documented;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueConstraintValidator.class)
public @interface UniqueConstraint {
    Class<?> entityType();

    String entityFieldName();

    String message() default "";

    Class[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
