// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// N : 1 Technicien
// N : 1 Avion

package com.good.util;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@IdClass(ReparationId.class) // Spécifie l'ID composite
public class Reparation {

    @Id
    @Column(name = "Avion_matricule", insertable = true, updatable = true) // Clé primaire partagée
    private Integer avionMatricule;

    @Id
    @Column(name = "Technicien_matricule", insertable = true, updatable = true) // Clé primaire partagée
    private Integer technicienMatricule;

    // Relation ManyToOne pour Avion
    @ManyToOne
    @JoinColumn(name = "Avion_matricule", referencedColumnName = "matricule", insertable = false, updatable = false)
    private Avion avion;

    // Relation ManyToOne pour Technicien
    @ManyToOne
    @JoinColumn(name = "Technicien_matricule", referencedColumnName = "matricule", insertable = false, updatable = false)
    private Technicien technicien;

    @Column(name = "CoutTotal", nullable = false)
    private BigDecimal coutTotal;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    // Constructeur sans paramètre
    public Reparation() {
    	
    }

    // Constructeur paramétré
    public Reparation(Integer avionMatricule, Integer technicienMatricule, Avion avion, Technicien technicien, BigDecimal coutTotal, LocalDate date) {
        this.avionMatricule = avionMatricule;
        this.technicienMatricule = technicienMatricule;
        this.avion = avion;
        this.technicien = technicien;
        this.coutTotal = coutTotal;
        this.date = date;
    }
    
    // Getters et setters
    public Integer getAvionMatricule() {
        return avionMatricule;
    }

    public void setAvionMatricule(Integer avionMatricule) {
        this.avionMatricule = avionMatricule;
    }

    public Integer getTechnicienMatricule() {
        return technicienMatricule;
    }

    public void setTechnicienMatricule(Integer technicienMatricule) {
        this.technicienMatricule = technicienMatricule;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Technicien getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }

    public BigDecimal getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(BigDecimal coutTotal) {
        this.coutTotal = coutTotal;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
