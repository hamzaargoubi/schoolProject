/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.services;

import pi.esprit.entities.commentaires;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi.esprit.utils.MyConnection;

/**
 *
 * @author bureau
 */
public class CommentairesService  {
    


     Connection cnx;

    public CommentairesService() {
        cnx = MyConnection.getInstance().getCnx();
    }
 public void ajouterCommentaire() {
        try {
            String requete1 = "INSERT INTO commentaires(descr,id_vid,id_user)"
                    + "VALUES (?,?,?)";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete1);
            System.out.println("Commentaire ajoutée!");
        } catch (SQLException ex) {
          
            System.out.println(ex.getMessage());
        }
    }
 
    
      public boolean ajouterCommentaire(commentaires r) {
       String requete = "INSERT INTO commentaires( descr,id_vid,id_user)" + " VALUES (?,?,?) ";
        
        
 
     
           try {
            PreparedStatement pst =cnx.prepareStatement(requete);
            pst.setString(1,r.getDescr());
            pst.setInt(2,r.getId_vid());
            pst.setInt(3,r.getId_user());
             pst.executeUpdate();
            System.out.println("ajout commentaires reussit");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
            return false;
    }
       public void SupprimerCommentaire(int i)
{
  String requete="DELETE FROM commentaires WHERE id_comm='"+i+"' ";     
        Statement st;
        try {
            st = (Statement) cnx.createStatement(); 
            st.executeUpdate(requete);
      System.out.println("comm supprimé");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
}
       public boolean ModifierComm(commentaires r)
    {
        int nbr_ligne;
        
        try{
            
            String requete="UPDATE commentaires set id_comm = ?, descr=?  WHERE id_comm="+r.getId_comm()+"";
            PreparedStatement pst = cnx.prepareStatement(requete);
           pst.setInt(1,r.getId_comm());
            pst.setString(2,r.getDescr());
         
           
            
          
            
            nbr_ligne=pst.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        if(nbr_ligne == 0){
            return false;
        }else{
            return true;
        }
        
    }
      public List<commentaires> ListerCommentaires(String i){
         List<commentaires> listCom = new ArrayList<>();
        commentaires listForm= new commentaires();
         Statement ste = null;
        try {  
            String requete = "SELECT * FROM commentaires WHERE id_comm='"+i+"'";

           
            ResultSet rs = ste.executeQuery(requete);

             while(rs.next()){
                
                 listForm.setId_comm(rs.getInt("id_comm"));
              
               
                 listForm.setDescr(rs.getString("descr"));
                 
                 listCom.add(listForm);
                 
            }
             
        } catch (SQLException ex) {
System.out.println(ex.getMessage());        }
        return listCom;
    
    }
       public ResultSet RechercherComByImm(String i)
    {
         try {
             Statement ste = null;
             ResultSet rs=null;
             String requete = "SELECT * FROM commentaires WHERE id_comm ='"+i+"'";
             rs = ste.executeQuery(requete);
             return rs ;
         } catch (SQLException ex) {
             System.out.println(ex.getMessage());         }
         return null;

       
    }
       public static String[] Insultes = {
        "bite", "baisable", "baise", "baiser", "bander", "branler", "branlette", "bordel", "burnes", "chatte", "chiant", "Chiante", "chiasse",
        "chier", "chiottes", "con", "conne", "connerie", "coucougnettes", "couille", "couillu", "cul", "déconner", "déconneur",
         "emmerder", "emmerdant", "emmerdante", "emmerdeur", "emmerdeuse", "empapaouter", "enculer", "entuber", "faire chier",
        "faire une pipe", "foutoir", "foutre", "foutre le camp", "foutu", "foutue", "gueule", "gueuler", "merde", "merder", "merdier", "merdique",
         "niquer", "nique ta mère", "pisser", "putain", "pute", "roubignoles", "roupettes", "roustons", "se démerder",
        "s’emmerder", "se faire chier", "se faire sauter", "sucer", "ta gueule", "tirer un coup", "turlutte", "zigounette", "fais chier", "je te saute",
        "zob","fuck","fuck you","slut","homosexuel","bicth","bhim","msatek" ,"hallouf","mou3a9"  };

    public static String[] getInsultes() {
        return Insultes;
    }

    public static void setInsultes(String[] Insultes) {
        CommentairesService.Insultes = Insultes;
    }

     public static boolean number( String com){
        String[] splitString = com.split(" ");
        for (String wordString : splitString) {
            for (String wordArray : Insultes) {
                if (wordString.equalsIgnoreCase(wordArray)) {                    
                    return false;
                }
            }

}
         return true;
}
}