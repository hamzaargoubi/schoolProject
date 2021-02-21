/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.entities;

import java.util.Objects;

/**
 *
 * @author bureau
 */
public class categoris {
    private int id_cat;
    private String nom_cat;

    public int getId_cat() {
        return id_cat;
    }

    public String getNom_cat() {
        return nom_cat;
    }

    public void setId_cat(int id_cat) {
        this.id_cat = id_cat;
    }

    public void setNom_cat(String nom_cat) {
        this.nom_cat = nom_cat;
    }

    public categoris(int id_cat, String nom_cat) {
        this.id_cat = id_cat;
        this.nom_cat = nom_cat;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id_cat;
        hash = 89 * hash + Objects.hashCode(this.nom_cat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final categoris other = (categoris) obj;
        if (this.id_cat != other.id_cat) {
            return false;
        }
        if (!Objects.equals(this.nom_cat, other.nom_cat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "categoris{" + "id_cat=" + id_cat + ", nom_cat=" + nom_cat + '}';
    }
    
    
}
