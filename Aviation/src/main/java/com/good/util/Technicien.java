package com.good.util;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Technicien extends Employe {

    // Relation avec Specialisation (1:N)
    @OneToMany(mappedBy = "technicien", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Specialisation> specialisations;

    // Relation avec Reparation (1:N)
    @OneToMany(mappedBy = "technicien", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reparation> reparations;

    // Constructeur sans arguments pour JPA
    public Technicien() {
        super(); // Appel du constructeur de la classe parent (Employe)
    }

    // Constructeur avec paramètres pour Technicien
    public Technicien(Integer matricule, String nom, String prenom, Adresse adresse, String tel, LocalDate dateEngagement, BigDecimal salaire, Set<Specialisation> specialisations, Set<Reparation> reparations) {
        super(matricule, nom, prenom, adresse, tel, dateEngagement, salaire); // Appel du constructeur parent
        this.specialisations = specialisations;
        this.reparations = reparations;
    }

    // Getters et Setters pour les relations
    public Set<Specialisation> getSpecialisations() {
        return specialisations;
    }

    public void setSpecialisations(Set<Specialisation> specialisations) {
        this.specialisations = specialisations;
    }

    public Set<Reparation> getReparations() {
        return reparations;
    }

    public void setReparations(Set<Reparation> reparations) {
        this.reparations = reparations;
    }

    // Méthode toString pour faciliter le débogage
    @Override
    public String toString() {
        return "Technicien{" +
                "matricule=" + getMatricule() +
                ", nom='" + getNom() + '\'' +
                ", prenom='" + getPrenom() + '\'' +
                ", adresse=" + getAdresse() +
                ", tel='" + getTel() + '\'' +
                ", dateEngagement=" + getDateEngagement() +
                ", salaire=" + getSalaire() +
                ", specialisations=" + specialisations +
                ", reparations=" + reparations +
                '}';
    }
}
