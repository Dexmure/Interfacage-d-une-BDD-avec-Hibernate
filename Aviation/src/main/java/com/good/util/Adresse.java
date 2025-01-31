// Maxim Laurendeau, Mohamed Mehrazi, Samuel Simard
// 1 relation
// 1 : N Employe

package com.good.util;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;



import com.good.validation.CodePostal;

@Entity

@Table(name = "Adresse")
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NotNull(message = "L'Id ne peut pas être null.")
    private int id;

    @Column(name = "numeroRue", nullable = false)
    @NotNull(message = "Le numéro de rue ne peut pas être null.")
    private int numeroRue;

  
    @Column(name = "nomRue", length = 255)
    private String nomRue;

   
    @Column(name = "ville", nullable = false, length = 255)
    @NotNull(message = "La ville ne peut pas être null.")
    private String ville;

    @Column(name = "codePostal", nullable = false, length = 45)
    @NotNull(message = "Le code postal ne peut pas être null.")
    @CodePostal
    private String codePostal;

    @Column(name = "province", nullable = false, length = 255)
    @NotNull(message = "a province ne peut pas être null.")
    private String province;

    @Column(name = "pays", nullable = false, length = 255)
    @NotNull(message = "Le pays ne peut pas être null.")
    private String pays;

    // Relation bidirectionnelle
    @OneToMany(mappedBy = "adresse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Employe> employes = new ArrayList<>();

    public Adresse() {
    }

    public Adresse(int numeroRue, String nomRue, String ville, String codePostal, String province, String pays) {
        this.numeroRue = numeroRue;
        this.nomRue = nomRue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.province = province;
        this.pays = pays;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroRue() {
        return numeroRue;
    }

    public void setNumeroRue(int numeroRue) {
        this.numeroRue = numeroRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

}
