/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.services;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Fakhri Argoubi
 */
public class PersonForTab {
    private final SimpleStringProperty id_user ;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty email;
    private final SimpleStringProperty profil;
    private final SimpleStringProperty photo;
    private final SimpleStringProperty login;
    private final SimpleStringProperty pwd;
    private final SimpleStringProperty adress;

    public PersonForTab() {
        this.id_user = null;
        this.nom = null;
        this.prenom = null;
        this.email = null;
        this.profil = null;
        this.photo = null;
        this.login = null;
        this.pwd = null;
        this.adress = null;
    }



    
    
    
    public PersonForTab(String id_user, String nom, String prenom,String email,String profil,String photo,String login,String pwd,String adress) {
            this.id_user = new SimpleStringProperty(id_user);
            this.nom = new SimpleStringProperty(nom);
            this.prenom = new SimpleStringProperty(prenom);
            this.email = new SimpleStringProperty(email);
            this.profil = new SimpleStringProperty(profil);
            this.photo = new SimpleStringProperty(photo);
            this.login = new SimpleStringProperty(login);
            this.pwd = new SimpleStringProperty(pwd);
            this.adress = new SimpleStringProperty(adress);
            
        }
    public String getId_user() {
            return id_user.get();
        }
 
    public void setId_user(String id_user) {
            this.id_user.set(id_user);
        }    
    public String getNom() {
            return nom.get();
        }
 
    public void setNom(String nom){
            this.nom.set(nom);

        }
    
    public String getPrenom() {
            return prenom.get();
        }
 
    public void setPrenom(String prenom){
            this.prenom.set(prenom);

        }

    public String getEmail() {
            return prenom.get();
        }
 
    public void setEmail(String email){
            this.email.set(email);

        }    
    public String getProfil() {
            return profil.get();
        }
 
    public void setProfil(String profil){
            this.profil.set(profil);

        } 
    public String getPhoto() {
            return photo.get();
        }
 
    public void setPhoto(String photo){
            this.photo.set(photo);

        }
    public String getLogin() {
            return login.get();
        }
 
    public void setLogin(String login){
            this.login.set(login);

        }
    public String getPwd() {
            return pwd.get();
        }
 
    public void setPwd(String pwd){
            this.pwd.set(pwd);
        }
    
    public String getAdress() {
            return adress.get();
        }
 
    public void setAdress(String adress){
            this.adress.set(adress);
        }   
}
