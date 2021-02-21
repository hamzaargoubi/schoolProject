/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.entities;

import java.sql.Timestamp;

/**
 *
 * @author ER2i
 */
public class VerificationCode {
   private int id_user;
   private String verificationCode;
   private Timestamp insertionDate;
   private boolean validity;
   
   public VerificationCode(){
       
   }

    public VerificationCode(int id_user, String verificationCode, Timestamp insertionDate, boolean validity) {
        this.id_user = id_user;
        this.verificationCode = verificationCode;
        this.insertionDate = insertionDate;
        this.validity = validity;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Timestamp getInsertionDate() {
        return insertionDate;
    }

    public void setInsertionDate(Timestamp insertionDate) {
        this.insertionDate = insertionDate;
    }

    public boolean isValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    @Override
    public String toString() {
        return "VerificationCode{" + "id_user=" + id_user + ", verificationCode=" + verificationCode + ", insertionDate=" + insertionDate + ", validity=" + validity + '}';
    }
   
   
}
