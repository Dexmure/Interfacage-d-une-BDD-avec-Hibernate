// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 2 relations
// N : 1 Technicien
// N : 1 Type

package com.good.util;

import jakarta.persistence.*;

@Entity
@Table(name = "Specialisation")
public class Specialisation {

    @EmbeddedId
    private SpecialisationId id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Technicien_matricule", referencedColumnName = "matricule", insertable = false, updatable = false)
    private Technicien technicien;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Type_nom", referencedColumnName = "nom", insertable = false, updatable = false)
    private Type type;

    // Constructeur vide 
    public Specialisation() {
    }

    // Constructeur avec paramètres
    public Specialisation(SpecialisationId id, Technicien technicien, Type type) {
        this.id = id;
        this.technicien = technicien;
        this.type = type;
    }

    // Getters et setters
    public SpecialisationId getId() {
        return id;
    }

    public void setId(SpecialisationId id) {
        this.id = id;
    }

    public Technicien getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
