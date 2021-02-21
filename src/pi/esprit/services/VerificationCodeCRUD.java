/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi.esprit.entities.VerificationCode;
import pi.esprit.entities.personnes;
import pi.esprit.utils.MyConnection;

/**
 *
 * @author ER2i
 */
public class VerificationCodeCRUD {
    Connection cnx;

    public VerificationCodeCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }
    
    public void insertCode(String code,int id){
        try {
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            String requete = "INSERT INTO verificationCodes(id_user,verificationCode,insertionDate,validity)"  
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.setString(2, code);
            pst.setTimestamp(3,date);
            pst.setBoolean(4, true);
            pst.executeUpdate();
            System.out.println("Code inserted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
    
    public VerificationCode selectVerifCode(int id){
        VerificationCode vc = new VerificationCode();
        try {
            java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
            String requete = "SELECT * FROM verificationCodes WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){    
            vc.setId_user(rs.getInt("id_user"));
            vc.setVerificationCode(rs.getString("verificationCode"));
            vc.setInsertionDate(rs.getTimestamp("insertionDate"));
            vc.setValidity(rs.getBoolean("validity"));
            if ( compareTwoTimeStamps(date,vc.getInsertionDate())>30 && vc.isValidity()){
                return vc;
                
            }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
        return null;
    }
    
    public void updateValidity(int id){
       
        try {
            String requete = "UPDATE verificationCodes SET validity=false "
                    + "WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("code updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
    
    public  long compareTwoTimeStamps(java.sql.Timestamp currentTime, java.sql.Timestamp oldTime)
{
    long milliseconds1 = oldTime.getTime();
    long milliseconds2 = currentTime.getTime();

  long diff = milliseconds2 - milliseconds1;
  long diffSeconds = diff / 1000;
  long diffMinutes = diff / (60 * 1000);
  long diffHours = diff / (60 * 60 * 1000);
  long diffDays = diff / (24 * 60 * 60 * 1000);

    return diffMinutes;
}
}
