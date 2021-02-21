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
import pi.esprit.entities.videos;
import pi.esprit.utils.MyConnection;

/**
 *
 * @author bureau
 */
public class statistiqueCrud {
    Connection cnx;

    public statistiqueCrud() {
        cnx = MyConnection.getInstance().getCnx();
    }
    public int getSport(){
        int count = 0;
        try {
           
            String sql="SELECT count(*) from videos  WHERE  type=?";
            videos v=new videos();
            PreparedStatement pst1 = (PreparedStatement) cnx.prepareStatement(sql);
            pst1.setString(1,"sport");
            ResultSet results = pst1.executeQuery();
            if(results.next()){
                count=results.getInt("count(*)");
            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
        
}
   
     public int getArts(){
        int count = 0;
        try {
           
            String sql="SELECT count(*) from videos  WHERE  type=?";
            videos v=new videos();
            PreparedStatement pst1 = (PreparedStatement) cnx.prepareStatement(sql);
            pst1.setString(1,"arts");
            ResultSet results = pst1.executeQuery();
            if(results.next()){
                count=results.getInt("count(*)");
            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    
     }
     
      public int getMusic(){
        int count = 0;
        try {
           
            String sql="SELECT count(*) from videos  WHERE  type=?";
            videos v=new videos();
            PreparedStatement pst1 = (PreparedStatement) cnx.prepareStatement(sql);
            pst1.setString(1,"music");
            ResultSet results = pst1.executeQuery();
            if(results.next()){
                count=results.getInt("count(*)");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    
     }
     
      public int getOther(){
        int count = 0;
        try {
           
            String sql="SELECT count(*) from videos  WHERE  type=?";
            videos v=new videos();
            PreparedStatement pst1 = (PreparedStatement) cnx.prepareStatement(sql);
            pst1.setString(1,"other");
            ResultSet results = pst1.executeQuery();
            if(results.next()){
                count=results.getInt("count(*)");
            }
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    
     }
       public int getDanse(){
        int count = 0;
        try {
           
            String sql="SELECT count(*) from videos  WHERE  type=?";
            videos v=new videos();
            PreparedStatement pst1 = (PreparedStatement) cnx.prepareStatement(sql);
            pst1.setString(1,"danse");
            ResultSet results = pst1.executeQuery();
            if(results.next()){
                count=results.getInt("count(*)");
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    
     }
}
