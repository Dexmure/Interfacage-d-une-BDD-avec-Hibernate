package com.good.util;

import java.io.Serializable;
import java.util.Objects;

public class ReparationId implements Serializable {

    private Integer avionMatricule;
    private Integer technicienMatricule;

    // Constructeurs, getters, setters, equals et hashCode

    public ReparationId() {
    }

    public ReparationId(Integer avionMatricule, Integer technicienMatricule) {
        this.avionMatricule = avionMatricule;
        this.technicienMatricule = technicienMatricule;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReparationId that = (ReparationId) o;
        return Objects.equals(avionMatricule, that.avionMatricule) &&
               Objects.equals(technicienMatricule, that.technicienMatricule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(avionMatricule, technicienMatricule);
    }
}
