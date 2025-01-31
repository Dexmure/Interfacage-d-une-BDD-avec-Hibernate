// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 2 relations
// N : 1 Avions
// N : 1 Test

package com.good.util;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class AvionTest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer matricule;

    // Relation Many-to-One avec Avion
    @ManyToOne
    @JoinColumn(name = "avion_matricule", referencedColumnName = "matricule")
    private Avion avion;
    
    @ManyToOne
    @JoinColumn(name = "Test_numero", referencedColumnName = "numero")
    private Test test; // Relation vers Test
    
    @Column(name = "dateTest")
    @Temporal(TemporalType.DATE)
    private Date dateTest;

    // Constructeur sans arguments pour Hibernate
    public AvionTest() {
    }

    // Constructeur avec paramètres pour JUnit
    public AvionTest(Integer matricule, Avion avion, Test test) {
        this.matricule = matricule;
        this.avion = avion;
        this.test = test;
    }

    // Getters et Setters
    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
    public Date getDateTest() {
        return dateTest;
    }
    public void setDateTest(Date dateTest) {
        this.dateTest = dateTest;
    }
}
