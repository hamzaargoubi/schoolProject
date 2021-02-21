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
public class reacts {
    private int id_react;
    private int id_vid;
    private int id_user;
     private String etat;

    public reacts(int id_react, int id_vid, int id_user, String etat) {
        this.id_react = id_react;
        this.id_vid = id_vid;
        this.id_user = id_user;
        this.etat = etat;
    }
    public reacts(){
        
    }

    public reacts(int id_vid, int id_user) {
        this.id_vid = id_vid;
        this.id_user = id_user;
    }

    

    public int getId_react() {
        return id_react;
    }

    public int getId_vid() {
        return id_vid;
    }

    public int getId_user() {
        return id_user;
    }

    public String getEtat() {
        return etat;
    }

    public void setId_react(int id_react) {
        this.id_react = id_react;
    }

    public void setId_vid(int id_vid) {
        this.id_vid = id_vid;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final reacts other = (reacts) obj;
        if (this.id_react != other.id_react) {
            return false;
        }
        if (this.id_vid != other.id_vid) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.etat, other.etat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "reacts{" + "id_react=" + id_react + ", id_vid=" + id_vid + ", id_user=" + id_user + ", etat=" + etat + '}';
    }

    
}