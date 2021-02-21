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
public class videos {
    private int id_vid;
    private String nom_vid;
    private String path_vid;
    private String desc_vid;
    private int id_comp;
    private String type;

    public videos(int id_vid, String nom_vid, String path_vid, String desc_vid, int id_comp, String type) {
        this.id_vid = id_vid;
        this.nom_vid = nom_vid;
        this.path_vid = path_vid;
        this.desc_vid = desc_vid;
        this.id_comp = id_comp;
        this.type = type;
    }
    public videos(){
        
    }

    public int getId_vid() {
        return id_vid;
    }

    public String getNom_vid() {
        return nom_vid;
    }

    public String getPath_vid() {
        return path_vid;
    }

    public String getDesc_vid() {
        return desc_vid;
    }

    public int getId_comp() {
        return id_comp;
    }

    public String getType() {
        return type;
    }

    public void setId_vid(int id_vid) {
        this.id_vid = id_vid;
    }

    public void setNom_vid(String nom_vid) {
        this.nom_vid = nom_vid;
    }

    public void setPath_vid(String path_vid) {
        this.path_vid = path_vid;
    }

    public void setDesc_vid(String desc_vid) {
        this.desc_vid = desc_vid;
    }

    public void setId_comp(int id_comp) {
        this.id_comp = id_comp;
    }

    public void setType(String type) {
        this.type = type;
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
        final videos other = (videos) obj;
        if (this.id_vid != other.id_vid) {
            return false;
        }
        if (this.id_comp != other.id_comp) {
            return false;
        }
        if (!Objects.equals(this.nom_vid, other.nom_vid)) {
            return false;
        }
        if (!Objects.equals(this.path_vid, other.path_vid)) {
            return false;
        }
        if (!Objects.equals(this.desc_vid, other.desc_vid)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "videos{" + "id_vid=" + id_vid + ", nom_vid=" + nom_vid + ", path_vid=" + path_vid + ", desc_vid=" + desc_vid + ", id_comp=" + id_comp + ", type=" + type + '}';
    }

    
    
    
}
