// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 2 relations
// N : 1 Pilote
// N : 1 Examen

package com.good.util;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "examination")
public class Examination {

    @EmbeddedId
    private ExaminationId id;

    @ManyToOne
    @MapsId("Pilote_matricule") 
    @JoinColumn(name = "Pilote_matricule", nullable = false)
    private Pilote Pilote_matricule;

    @ManyToOne
    @MapsId("Examen_identifiant") 
    @JoinColumn(name = "Examen_identifiant", nullable = false)
    private Examen Examen_identifiant;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "rapport")
    private String rapport;

    // Getters and Setters
    public ExaminationId getId() {
        return id;
    }

    public void setId(ExaminationId id) {
        this.id = id;
    }

    public Pilote getPilote_matricule() {
        return Pilote_matricule;
    }

    public void setPilote_matricule(Pilote Pilote_matricule) {
        this.Pilote_matricule = Pilote_matricule;
    }

    public Examen getExamen_identifiant() {
        return Examen_identifiant;
    }

    public void setExamen_identifiant(Examen Examen_identifiant) {
        this.Examen_identifiant = Examen_identifiant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRapport() {
        return rapport;
    }

    public void setRapport(String rapport) {
        this.rapport = rapport;
    }
}