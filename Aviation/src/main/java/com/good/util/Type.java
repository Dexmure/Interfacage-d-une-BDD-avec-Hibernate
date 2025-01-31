// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 3 relations 
// 1 : N Specialisation
// 1 : N Qualification
// 1 : N Avion

package com.good.util;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

import java.util.List;

@Entity
@Table(name = "Type")
public class Type {

    @Id
    @Column(name = "nom", length = 45, unique = true)
    private String nom;
    
    @Min(value = 1, message = "La capacité doit être positive et non nulle.")
    @Column(name = "capacite")
    private int capacite;

    @Column(name = "poids")
    private double poids;

    @Column(name = "rayonAction")
    private double rayonAction;
    
    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Avion> avions;

    // Getters et setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getRayonAction() {
        return rayonAction;
    }

    public void setRayonAction(double rayonAction) {
        this.rayonAction = rayonAction;
    }

    public List<Avion> getAvions() {
        return avions;
    }

    public void setAvions(List<Avion> avions) {
        this.avions = avions;
    }
}
 