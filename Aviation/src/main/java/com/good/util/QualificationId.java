// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard


package com.good.util;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;



@Embeddable
public class QualificationId implements Serializable {

    @Column(name = "Pilote_matricule")
    private Integer piloteMatricule;

    @Column(name = "Type_nom")
    private String typeNom;

    // Constructeur sans paramètre 
    public QualificationId() {

    }
    
    // Constructeur paramétré
    public QualificationId(Integer piloteMatricule, String typeNom) {
        this.piloteMatricule = piloteMatricule;
        this.typeNom = typeNom;
    }
    
    // getters, setters, equals, et hashCode

    public Integer getPiloteMatricule() {
        return piloteMatricule;
    }

    public void setPiloteMatricule(Integer piloteMatricule) {
        this.piloteMatricule = piloteMatricule;
    }

    public String getTypeNom() {
        return typeNom;
    }

    public void setTypeNom(String typeNom) {
        this.typeNom = typeNom;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QualificationId that = (QualificationId) obj;

        if (!piloteMatricule.equals(that.piloteMatricule)) return false;
        return typeNom.equals(that.typeNom);
    }

    @Override
    public int hashCode() {
        int result = piloteMatricule.hashCode();
        result = 31 * result + typeNom.hashCode();
        return result;
    }
}

