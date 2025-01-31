package com.good.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CodePostalValidator.class) // On associe le validateur personnalisé
public @interface CodePostal {
    String message() default "Le code postal doit respecter le format canadien (ex: G6V 4Z5).";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
