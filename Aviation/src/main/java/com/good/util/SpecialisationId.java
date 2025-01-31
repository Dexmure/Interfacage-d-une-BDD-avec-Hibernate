package com.good.util;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SpecialisationId implements Serializable {

    @Column(name = "Technicien_matricule")
    private Integer technicienMatricule;

    @Column(name = "Type_nom")
    private String typeNom;
    
    public SpecialisationId() {
        // Nécessaire pour les opérations JPA
    }
    
    // Constructeur avec paramètres
    public SpecialisationId(Integer technicienMatricule, String typeNom) {
        this.technicienMatricule = technicienMatricule;
        this.typeNom = typeNom;
    }

    // Getters et setters
    
    public Integer getTechnicienMatricule() {
        return technicienMatricule;
    }

    public void setTechnicienMatricule(Integer technicienMatricule) {
        this.technicienMatricule = technicienMatricule;
    }

    public String getTypeNom() {
        return typeNom;
    }

    public void setTypeNom(String typeNom) {
        this.typeNom = typeNom;
    }

    // Implémentation de equals() 
    @Override
    public boolean equals(Object obj) {
        // Vérification de l'égalité avec soi-même
        if (this == obj) return true;
        
        // Vérification si l'objet est une instance de SpecialisationId
        if (obj == null || getClass() != obj.getClass()) return false;
        
        SpecialisationId that = (SpecialisationId) obj;
        
        // Comparaison des champs de la clé composite
        return Objects.equals(technicienMatricule, that.technicienMatricule) && 
               Objects.equals(typeNom, that.typeNom);
    }

    // Implémentation de hashCode()
    @Override
    public int hashCode() {
        return Objects.hash(technicienMatricule, typeNom);
    }
}
