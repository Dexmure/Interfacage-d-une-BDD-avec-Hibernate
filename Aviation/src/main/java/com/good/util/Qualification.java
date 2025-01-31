// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 2 relations
// N : 1 Pilote
// N : 1 Type


package com.good.util;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Qualification {

    @EmbeddedId
    private QualificationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Pilote_matricule", referencedColumnName = "matricule", insertable = false, updatable = false)
    private Pilote pilote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Type_nom", referencedColumnName = "nom", insertable = false, updatable = false)
    private Type type;

    // Constructeur sans paramètres 
    public Qualification() {
    }

    // Constructeur avec paramètres
    public Qualification(QualificationId id, Pilote pilote, Type type) {
        this.id = id;
        this.pilote = pilote;
        this.type = type;
    }
    
    // Getters et setters
    public QualificationId getId() {
        return id;
    }

    public void setId(QualificationId id) {
        this.id = id;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Qualification that = (Qualification) obj;

        if (!id.equals(that.id)) return false;
        if (!pilote.equals(that.pilote)) return false;
        return type.equals(that.type);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + pilote.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
