package com.good.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TelephoneValidator implements ConstraintValidator<Telephone, String> {

    // Expression régulière pour valider le numéro de téléphone
    private static final String PHONE_REGEX = "^\\d{10}$|^\\d{3}-\\d{3}-\\d{4}$";

    @Override
    public void initialize(Telephone constraintAnnotation) {
        // Initialisation si nécessaire
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // On suppose que NULL n'est pas valide
        }
        return value.matches(PHONE_REGEX); // Vérifie si la chaîne correspond au format spécifié
    }
}
