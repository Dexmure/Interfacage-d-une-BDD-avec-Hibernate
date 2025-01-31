// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 1 relations
// 1 : N AvionTest

package com.good.util;

import jakarta.persistence.*;
import java.util.List;



@Entity

@Table(name = "Test")
public class Test {

    @Id
    @Column(name ="numero", nullable = false)
    private Integer numero;
    
    @Column(name ="nom")
    private String nom;
    
    @Column(name = "seuil")
    private Double seuil;

    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvionTest> avionTests;

    // Getters et setters
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Double getSeuil() {
        return seuil;
    }
    
    public void setSeuil(Double seuil) {
        this.seuil = seuil;
    }

    public List<AvionTest> getAvionTests() {
        return avionTests;
    }

    public void setAvionTests(List<AvionTest> avionTests) {
        this.avionTests = avionTests;
    }
}
