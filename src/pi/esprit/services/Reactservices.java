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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javax.swing.JOptionPane;
import pi.esprit.entities.loggedmembre;
import pi.esprit.entities.personnes;
import pi.esprit.entities.reacts;
import pi.esprit.utils.MyConnection;

/**
 *
 * @author bureau
 */
public class Reactservices {
     Connection cnx;

    public Reactservices() {
        cnx = MyConnection.getInstance().getCnx();
    }
    personnes pt=loggedmembre.getP();
    public  void like(reacts r){
        try {
        
           
            String requete2="INSERT INTO reacts (id_vid,id_user,etat) " + "VALUES(?,?,?)";
            PreparedStatement pst2 = cnx.prepareStatement(requete2);
            pst2.setInt(1,r.getId_vid());
            pst2.setInt(2,pt.getId_user());
            //pst2.setInt(2,r.getId_user());
             pst2.setString(3,"like");
            pst2.executeUpdate();
            System.out.println("like ajoutee");
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
     public void dislike(reacts r){
        try {
        
           
            String requete2="INSERT INTO reacts (id_vid,id_user,etat) " + "VALUES(?,?,?)";
            PreparedStatement pst2 = cnx.prepareStatement(requete2);
            pst2.setInt(1,r.getId_vid());
            pst2.setInt(2,pt.getId_user());
             pst2.setString(3,"dislike");
            pst2.executeUpdate();
            System.out.println("dislike ajouté");
          
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
     
      public String  like_number(reacts r) {
          String count = null;
        try {
            
            String sql="SELECT count(*) FROM reacts WHERE id_vid=? and etat=? ";
            PreparedStatement pst1 = cnx.prepareStatement(sql);
            pst1.setInt(1,r.getId_vid());
            pst1.setString(2,"like");
            ResultSet results = pst1.executeQuery();
                    if(results.next()){
                        count=results.getString("count(*)");
                    
                    }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
         return count;
           
        
    }
      public String  dislike_number(reacts r) {
          String count = null;
        try {
            
            String sql="SELECT count(*) FROM reacts WHERE id_vid=? and etat=? ";
            PreparedStatement pst1 = cnx.prepareStatement(sql);
            pst1.setInt(1,r.getId_vid());
            pst1.setString(2,"dislike");
            ResultSet results = pst1.executeQuery();
                    if(results.next()){
                        count=results.getString("count(*)");
                    
                    }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
         return count;
      }
         public void Supprimerlike(int i,int j)
{
  String requete="DELETE FROM reacts WHERE id_user='"+i+"' and id_vid='"+j+"' and etat='"+"like"+"' ";     
        Statement st;
        try {
            st = (Statement) cnx.createStatement(); 
            st.executeUpdate(requete);
      System.out.println("like supprimé");
   JOptionPane.showMessageDialog(null,"like deleted!!!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
            public void Supprimerdislike(int i, int j )
{
  String requete="DELETE FROM reacts WHERE id_user='"+i+"' and id_vid='"+j+"' and etat='"+"dislike"+"' ";     
        Statement st;
        try {
            st = (Statement) cnx.createStatement(); 
            st.executeUpdate(requete);
      System.out.println("dislike supprimé");
      JOptionPane.showMessageDialog(null,"dislike deleted!!!");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
            public boolean getlikeparid(reacts r){
         try {
             String sql="SELECT * FROM reacts WHERE id_vid=? and id_user=? and etat=?";
             PreparedStatement pst1 = cnx.prepareStatement(sql);
             pst1.setInt(1,r.getId_vid());
            pst1.setInt(2,pt.getId_user());
             //pst1.setInt(2,r.getId_user());
             pst1.setString(3,"like");
             ResultSet results = pst1.executeQuery();
            if(results.next()){
                return true;
            }
             
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());         }
         return false;
            }
            
              public boolean getdislikeparid(reacts r){
         try {
             String sql="SELECT * FROM reacts WHERE id_vid=? and id_user=? and etat=?";
             PreparedStatement pst1 = cnx.prepareStatement(sql);
             pst1.setInt(1,r.getId_vid());
            pst1.setInt(2,pt.getId_user());
             //pst1.setInt(2,r.getId_user());
             pst1.setString(3,"dislike");
             ResultSet results = pst1.executeQuery();
            if(results.next()){
                return true;
            }
             
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());         }
         return false;
            }
}
