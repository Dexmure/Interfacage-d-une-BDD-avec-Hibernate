// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 2 relations
// N : 1 Pilote
// N : 1 Avion

package com.good.util;

import jakarta.persistence.*;

@Entity
@Table(name = "piloter")
public class Piloter {

    @EmbeddedId
    private PiloterId id;

    @ManyToOne
    @MapsId("Pilote_matricule") // Lier le matricule du pilote à la clé composite
    @JoinColumn(name = "Pilote_matricule", nullable = false)
    private Pilote pilote;

    @ManyToOne
    @MapsId("Avion_matricule") // Lier le matricule de l'avion à la clé composite
    @JoinColumn(name = "Avion_matricule", nullable = false)
    private Avion avion;

    // Getters et Setters
    public PiloterId getId() {
        return id;
    }

    public void setId(PiloterId id) {
        this.id = id;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }
}
