package com.good.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CodePostalValidator implements ConstraintValidator<CodePostal, String> {

    @Override
    public void initialize(CodePostal constraintAnnotation) {
        // Initialisation si nécessaire, généralement vide.
    }

    @Override
    public boolean isValid(String codePostal, ConstraintValidatorContext context) {
        if (codePostal == null) {
            return false; // Le code postal ne doit pas être NULL.
        }
        // Vérifie le format du code postal canadien : "A1A 1A1"
        return codePostal.matches("^[A-Z]\\d[A-Z] \\d[A-Z]\\d$");
    }
}
