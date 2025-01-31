// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 1 : N Examination

package com.good.util;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "examen")
public class Examen {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identifiant;

    @Column(name = "description")
    private String description;

    @Column(name = "Examencol")
    private String Examencol;
    
    // Getters and setters
    public int getIdentifiant() {
        return identifiant;
    }
    public void setIdentifiant(int identifiant) {
        this.identifiant = identifiant;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getExamencol() {
        return Examencol;
    }

    public void setExamencol(String Examencol) {
        this.Examencol = Examencol;
    }
}