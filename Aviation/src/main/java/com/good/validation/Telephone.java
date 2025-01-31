package com.good.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Annotation pour valider le format du numéro de téléphone
@Constraint(validatedBy = TelephoneValidator.class)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Telephone {

    String message() default "Le numéro de téléphone doit être au format 1231231234 ou 123-123-1234.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
