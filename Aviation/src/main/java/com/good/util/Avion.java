// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 4 relations
// 1 : N Reparation
// 1 : N Piloter
// N : 1 Type
// 1 : N AvionTest

package com.good.util;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Avion")
public class Avion {

    @Id
    @Column(name = "matricule", nullable = false)
    private Integer matricule;

    // Relation Many-to-One avec Type
    @ManyToOne
    @JoinColumn(name = "Type_nom", nullable = false)
    private Type type;  // Un avion a un seul type

    // Relation One-to-Many avec Reparation
    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reparation> reparations;  // Un avion peut avoir plusieurs réparations

    // Relation One-to-Many avec Piloter
    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Piloter> pilotes;  // Un avion peut être piloté par plusieurs pilotes

    // Relation One-to-Many avec AvionTest
    @OneToMany(mappedBy = "avion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvionTest> avionTests;  // Un avion peut avoir plusieurs tests

    // Getters et Setters
    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<Reparation> getReparations() {
        return reparations;
    }

    public void setReparations(List<Reparation> reparations) {
        this.reparations = reparations;
    }

    public List<Piloter> getPilotes() {
        return pilotes;
    }

    public void setPilotes(List<Piloter> pilotes) {
        this.pilotes = pilotes;
    }

    public List<AvionTest> getAvionTests() {
        return avionTests;
    }

    public void setAvionTests(List<AvionTest> avionTests) {
        this.avionTests = avionTests;
    }
}
