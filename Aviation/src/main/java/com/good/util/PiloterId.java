package com.good.util;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PiloterId implements Serializable {

    @Column(name = "Pilote_matricule")
    private int Pilote_matricule;

    @Column(name = "Avion_matricule")
    private int Avion_matricule;

    // Getters and Setters
    public int getPilote_matricule() {
        return Pilote_matricule;
    }

    public void setPilote_matricule(int Pilote_matricule) {
        this.Pilote_matricule = Pilote_matricule;
    }

    public int getAvion_matricule() {
        return Avion_matricule;
    }

    public void setAvion_matricule(int Avion_matricule) {
        this.Avion_matricule = Avion_matricule;
    }

    // Override equals() and hashCode() for proper comparison
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiloterId that = (PiloterId) o;
        return Pilote_matricule == that.Pilote_matricule &&
        		Avion_matricule == that.Avion_matricule;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Pilote_matricule, Avion_matricule);
    }
}