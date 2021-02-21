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
import java.util.ArrayList;
import java.util.List;
import pi.esprit.entities.personnes;
import pi.esprit.entities.videos;
import pi.esprit.utils.MyConnection;

/**
 *
 * @author bureau
 */
public class VideoCRUD {
     Connection cnx;

    public VideoCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterVideo() {
        try {
            String requete1 = "INSERT INTO videos (nom_vid,path_vid,desc_vid,id_comp,type)"
                    + "VALUES (?,?,?,?,?)";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete1);
            System.out.println("video ajoutée!");
        } catch (SQLException ex) {
          
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterVideo2(videos v) {
        try {
           String requete2 = "INSERT INTO videos (nom_vid, path_vid, desc_vid, id_comp, type)"
                    + "VALUES (?,?,?,?,?)";  
            PreparedStatement pst = cnx.prepareStatement(requete2);
          
            pst.setString(1,v.getNom_vid() );
            pst.setString(2,v.getPath_vid() );
           
            
            pst.setString(3, v.getDesc_vid());
        
            
            pst.setInt(4, v.getId_comp());
            pst.setString(5, v.getType());
            pst.executeUpdate();
            System.out.println("Video added!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerPersonne(int id_vid) {
        try {
            String requete = "DELETE FROM videos WHERE id_vid=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_vid);
            pst.executeUpdate();
            System.out.println("video supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updatePersonne(videos v, int id_vid) {
        try {
            String requete = "UPDATE videos SET nom_vid=?,path_vid=? "
                    + "WHERE id_vid=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
          pst.setInt(1, v.getId_vid());
            pst.setString(2, v.getNom_vid());
            
            pst.setString(3, v.getPath_vid());
            pst.executeUpdate();
            System.out.println("video modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   public List<videos> displayAll(){
       List<videos> listeVideos = new ArrayList<>();
        try {
            String requete = "SELECT * FROM videos";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
            videos v=new videos();
                v.setId_vid(rs.getInt("id_vid"));
                v.setNom_vid(rs.getString("nom_vid"));
                v.setPath_vid(rs.getString("path_vid"));
                v.setDesc_vid(rs.getString("desc_vid"));
                v.setId_comp(rs.getInt("id_comp"));
                v.setType(rs.getString("type"));
                
               
                
               
               listeVideos.add(v);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listeVideos;
   } 
}
