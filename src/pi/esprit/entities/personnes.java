/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.entities;

import java.util.Date;


import java.util.Objects;

/**
 *
 * @author bureau
 */
public class personnes {
    private int id_user ;
     private String nom;
    private String prenom;
    private String email;
    
  
    private String profil;
    private String photo;
    private String login;
    private String pwd;
      private String adress;

    public personnes(int id_user, String nom, String prenom,String profil, String photo, String pwd) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.profil=profil;
        this.photo = photo;
        this.pwd = pwd;
    }

    public personnes(int id_user, String nom, String prenom) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
    }

   
  

    public int getId_user() {
        return id_user;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

   

    public String getAdress() {
        return adress;
    }

    public String getProfil() {
        return profil;
    }

    public String getPhoto() {
        return photo;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public personnes() {
        
    }

    public personnes(int id_user, String nom, String prenom, String email, String profil, String photo, String login, String pwd, String adress) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.profil = profil;
        this.photo = photo;
        this.login = login;
        this.pwd = pwd;
        this.adress = adress;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_user;
        hash = 97 * hash + Objects.hashCode(this.nom);
        hash = 97 * hash + Objects.hashCode(this.prenom);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.profil);
        hash = 97 * hash + Objects.hashCode(this.photo);
        hash = 97 * hash + Objects.hashCode(this.login);
        hash = 97 * hash + Objects.hashCode(this.pwd);
        hash = 97 * hash + Objects.hashCode(this.adress);
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
        final personnes other = (personnes) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.profil, other.profil)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.pwd, other.pwd)) {
            return false;
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "personnes{" + "id_user=" + id_user + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", profil=" + profil + ", photo=" + photo + ", login=" + login + ", pwd=" + pwd + ", adress=" + adress + '}';
    }

   
   
   

    

   
   
   

   
    

  

    

}
