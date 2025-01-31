// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 3 relations
// N : 1 Adresse
// 1 : 1 Technicien
// 1 : 1 Pilote

package com.good.util;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.good.validation.Telephone;

@Entity
@Table(name = "Employe")
@Inheritance(strategy = InheritanceType.JOINED)  // Héritage avec stratégie JOINED 
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matricule;

    @Column(name = "nom", length = 45, nullable = false)
    private String nom;

    @Column(name = "prenom", length = 45, nullable = false)
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "adresse", referencedColumnName = "id", nullable = false)
    private Adresse adresse;

    @Telephone
    @Column(name = "tel", length = 45, nullable = false)
    private String tel;

    @Column(name = "dateEngagement", nullable = false)
    private LocalDate dateEngagement;

    @Column(name = "salaire", precision = 10, scale = 2, nullable = false)
    private BigDecimal salaire;


    // Constructeur sans arguments pour Hibernate
    public Employe() {
    }

    // Constructeur avec paramètres pour faciliter la création d'instances
    public Employe(Integer matricule, String nom, String prenom, Adresse adresse, String tel, LocalDate dateEngagement, BigDecimal salaire) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.dateEngagement = dateEngagement;
        this.salaire = salaire;
    }
    
    // Getters et setters
    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getDateEngagement() {
        return dateEngagement;
    }

    public void setDateEngagement(LocalDate dateEngagement) {
        this.dateEngagement = dateEngagement;
    }

    public BigDecimal getSalaire() {
        return salaire;
    }

    public void setSalaire(BigDecimal salaire) {
        this.salaire = salaire;
    }
}

