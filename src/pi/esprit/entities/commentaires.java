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
public class commentaires {
    private int id_comm;
    private String descr;
    private int id_vid;
    private int id_user;

    public commentaires() {
    }

    public commentaires(String descr, int id_vid, int id_user) {
        this.descr = descr;
        this.id_vid = id_vid;
        this.id_user = id_user;
    }

   
    public int getId_comm() {
        return id_comm;
    }

    public String getDescr() {
        return descr;
    }

    public int getId_vid() {
        return id_vid;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_comm(int id_comm) {
        this.id_comm = id_comm;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setId_vid(int id_vid) {
        this.id_vid = id_vid;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public commentaires(int id_comm, String descr, int id_vid, int id_user) {
        this.id_comm = id_comm;
        this.descr = descr;
        this.id_vid = id_vid;
        this.id_user = id_user;
    }
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id_comm;
        hash = 97 * hash + Objects.hashCode(this.descr);
        hash = 97 * hash + this.id_vid;
        hash = 97 * hash + this.id_user;
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
        final commentaires other = (commentaires) obj;
        if (this.id_comm != other.id_comm) {
            return false;
        }
        if (this.id_vid != other.id_vid) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.descr, other.descr)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "commentaires{" + "id_comm=" + id_comm + ", descr=" + descr + ", id_vid=" + id_vid + ", id_user=" + id_user + '}';
    }

    
    
}
