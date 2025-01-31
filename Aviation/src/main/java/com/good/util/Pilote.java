// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 4 relations
// 1 : 1 Employe
// 1 : N Piloter
// 1 : N Qualification
// 1 : N Examination 

package com.good.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Pilote extends Employe {

    // Relation avec Qualification (1:N)
    @OneToMany(mappedBy = "pilote", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Qualification> qualifications;

    // Constructeur sans arguments pour JPA
    public Pilote() {
        super(); // Appel du constructeur de la classe parent (Employe)
    }

    // Constructeur avec paramètres pour Pilote
    public Pilote(Integer matricule, String nom, String prenom, Adresse adresse, String tel, LocalDate dateEngagement, BigDecimal salaire, Set<Qualification> qualifications) {
        super(matricule, nom, prenom, adresse, tel, dateEngagement, salaire); // Appel du constructeur parent
        this.qualifications = qualifications;
    }
    
    // Getters et Setters pour les relations
    public Set<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Set<Qualification> qualifications) {
        this.qualifications = qualifications;
    }
}
