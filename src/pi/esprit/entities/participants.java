/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.entities;

/**
 *
 * @author bureau
 */
public class participants {
    private int id_user;
    private int id_comp;

    public participants(int id_user, int id_comp) {
        this.id_user = id_user;
        this.id_comp = id_comp;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_comp() {
        return id_comp;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_comp(int id_comp) {
        this.id_comp = id_comp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final participants other = (participants) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.id_comp != other.id_comp) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "participants{" + "id_user=" + id_user + ", id_comp=" + id_comp + '}';
    }
    
}
