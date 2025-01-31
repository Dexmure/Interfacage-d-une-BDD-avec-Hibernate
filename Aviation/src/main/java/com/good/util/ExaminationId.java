// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
package com.good.util;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExaminationId implements Serializable {

    @Column(name = "Pilote_matricule")
    private int Pilote_matricule;

    @Column(name = "Examen_identifiant")
    private int Examen_identifiant;

    // Getters and Setters
    public int getPilote_matricule() {
        return Pilote_matricule;
    }

    public void setPilote_matricule(int Pilote_matricule) {
        this.Pilote_matricule = Pilote_matricule;
    }

    public int getExamen_identifiant() {
        return Examen_identifiant;
    }

    public void setExamen_identifiant(int Examen_identifiant) {
        this.Examen_identifiant = Examen_identifiant;
    }

    // Override equals() and hashCode() for proper comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExaminationId that = (ExaminationId) o;
        return Pilote_matricule == that.Pilote_matricule &&
        		Examen_identifiant == that.Examen_identifiant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Pilote_matricule, Examen_identifiant);
    }
}