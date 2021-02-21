
package pi.esprit.services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pi.esprit.entities.personnes;
import pi.esprit.utils.MyConnection;

/**
 *
 * @author bureau
 */
public class PersonneCRUD {
     Connection cnx;

    public PersonneCRUD() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void ajouterPersonne() {
        try {
            String requete1 = "INSERT INTO personnes(nom,prenom,profil,photo,login,pwd,adress,email)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete1);
            System.out.println("Personne ajoutée!");
        } catch (SQLException ex) {
          
            System.out.println(ex.getMessage());
        }
    }

    public void ajouterPersonne2(personnes p) {
        try {
           String requete2 = "INSERT INTO personnes(nom,prenom,profil,photo,login,pwd,adress,email)"
                    + "VALUES (?,?,?,?,?,?,?,?)";  
            PreparedStatement pst = cnx.prepareStatement(requete2);
          
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
           
            
            pst.setString(3, p.getProfil());
        
            
            pst.setString(4, p.getPhoto());
            pst.setString(5, p.getLogin());
            
            pst.setString(6, p.getPwd());   
            pst.setString(7, p.getAdress());
            pst.setString(8, p.getEmail());
            
           
            
            
            pst.executeUpdate();
            System.out.println("Person added!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void supprimerPersonne(int id_user) {
        try {
            String requete = "DELETE FROM personnes WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_user);
            pst.executeUpdate();
            System.out.println("Personne supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void updatePersonne(personnes p, int id_user) {
        try {
            String requete = "UPDATE personnes SET nom=?,prenom=? "
                    + "WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
          pst.setInt(1, p.getId_user());
            pst.setString(2, p.getNom());
            
            pst.setString(3, p.getPrenom());
            pst.executeUpdate();
            System.out.println("Personne modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   public List<personnes> displayAll(){
       List<personnes> listePersonnes = new ArrayList<>();
        try {
            String requete = "SELECT * FROM personnes";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
             personnes p=new personnes();
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setProfil(rs.getString("profil"));
                p.setPhoto(rs.getString("photo"));
                p.setLogin(rs.getString("login"));
                p.setPwd(rs.getString("pwd"));
                 p.setAdress(rs.getString("adress"));
                 p.setAdress(rs.getString("email"));
               
                
               
                listePersonnes.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listePersonnes;
   } 
   public  List<PersonForTab> selectUsers(){
       List<PersonForTab> listePersonnes = new ArrayList<>();
        try {
            String requete = "SELECT * FROM personnes";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                listePersonnes.add(new PersonForTab(Integer.toString(rs.getInt("id_user")), rs.getString("nom")
                , rs.getString("prenom"), rs.getString("email"), rs.getString("profil"), rs.getString("photo")
                        , rs.getString("login"), rs.getString("pwd"), rs.getString("adress")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listePersonnes;
   }
   public  PersonForTab selectUserForTab(int id){
       PersonForTab p = new PersonForTab();
        try {
            String requete = "SELECT * FROM personnes WHERE id_user="+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                p=(new PersonForTab(Integer.toString(rs.getInt("id_user")), rs.getString("nom")
                , rs.getString("prenom"), rs.getString("email"), rs.getString("profil"), rs.getString("photo")
                        , rs.getString("login"), rs.getString("pwd"), rs.getString("adress")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
   }
   public personnes selectUser(int id){
       personnes p = null;
       try {
            String requete = "SELECT * FROM personnes WHERE id_user = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                p= new personnes(rs.getInt("id_user"), rs.getString("nom"), 
                        rs.getString("prenom"), rs.getString("email"), rs.getString("profil")
                , rs.getString("photo"), rs.getString("login"), rs.getString("pwd"), rs.getString("adress"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
       
   }
   
   public  void updatePerCell(String attributeType,String attribute,int id){
         try {
             String requete = new String("UPDATE personne SET ?=? "
                    + "WHERE id=?");
             PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setString(1, attributeType);
             pst.setString(2, attribute);
             pst.setInt(3, id);
             pst.executeUpdate();
                         System.out.println("Personne modifiée");

         } catch (SQLException ex) {
             System.out.println(ex.getMessage());         }
        
    }
   
   public  void supprimerPersonne2(int id_user) {
        try {
            String requete = "DELETE FROM personnes WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id_user);
            pst.executeUpdate();
            System.out.println("Personne supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
   public  void ajouterPersonne3(personnes p) {
        try {
           String requete2 = "INSERT INTO personnes(nom,prenom,adress,profil,photo,login,pwd,email)"
                    + "VALUES (?,?,?,?,?,?,?,?)";  
            PreparedStatement pst = cnx.prepareStatement(requete2);
            
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setString(3, p.getAdress());
            pst.setString(4, p.getProfil());
            pst.setString(5, p.getPhoto());
            pst.setString(6, p.getLogin());
            pst.setString(7, p.getPwd());
            pst.setString(8, p.getEmail());
            pst.executeUpdate();
            System.out.println("Person added!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
   public void updatePassWord(int id,String newPassword){
       try {
           String requete = "Update personnes SET pwd="+newPassword+"WHERE id_user="+id;
           Statement pst = cnx.createStatement();
           pst.executeUpdate(requete);
           System.out.println("Password updated");
       } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
   
   public void updatePersonne2(PersonForTab p) {
        try {
            String requete = "UPDATE personnes SET id_user=?,nom=?,prenom=?,profil=?,photo=?,login=?,pwd=?,adress=?,email=? "
                    + "WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
           
          pst.setInt(1, Integer.parseInt(p.getId_user()));
            pst.setString(2, p.getNom());
            pst.setString(3, p.getPrenom());
            pst.setString(4, p.getProfil());
            pst.setString(5, p.getPhoto());
            pst.setString(6, p.getLogin());
            pst.setString(7, p.getPwd());
            pst.setString(8, p.getAdress());
            pst.setString(9, p.getEmail());
            pst.setInt(10, Integer.parseInt(p.getId_user()));
            pst.executeUpdate();
            System.out.println("Personne modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
   
   public  void initializeReportTab(int id) {
        try {
           
           String requete2 = "INSERT INTO reports(id_user,numberOfReports)"
                    + "VALUES (?,?)";  
            PreparedStatement pst = cnx.prepareStatement(requete2);
            
            pst.setInt(1, id);
            pst.setInt(2, 0);
            
            pst.executeUpdate();
            System.out.println("Ban added!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
   public int selectNumberOfReports(int id){
       int nReports = -1;
        try {
            String requete = "SELECT numberOfReports FROM reports WHERE id_user = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
               nReports = rs.getInt("numberOfReports");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return nReports;
   }
   
   public  void incrementNumberOfReports(int id) {
       int nReports = 0;
        try {
            String requete = "SELECT numberOfReports FROM reports WHERE id_user = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
               nReports = rs.getInt("numberOfReports");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        nReports++;
        try {
           
           String requete = "UPDATE reports SET numberOfReports=? "
                    + "WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, nReports);
            pst.setInt(2, id);
          
           
            pst.executeUpdate();
            System.out.println("report added");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
   public  void ban(PersonForTab p,int addedDays) {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
         try {
            date= addDays(addedDays, date);
         } catch (Exception ex) {
             System.out.println(ex.getMessage());         }
        try {
           
           String requete2 = "INSERT INTO bans(id_user,endOfBanDate)"
                    + "VALUES (?,?)";  
            PreparedStatement pst = cnx.prepareStatement(requete2);
            
            pst.setString(1, p.getId_user());
            pst.setTimestamp(2, date);
            
            pst.executeUpdate();
            System.out.println("Ban added!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
   
   
   
   
   public boolean banVerification(int id){
       Timestamp dateBan = null;
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
       try {
            String requete = "SELECT endOfBanDate FROM bans WHERE id_user = "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
               dateBan=rs.getTimestamp("endOfBanDate");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       VerificationCodeCRUD vc = new VerificationCodeCRUD();
       if(vc.compareTwoTimeStamps(dateBan,date)>0)
           return true;
        return false;
       
   }
   
   public void insertFriendRequest(int idReceiver,int idSender)
   {
       try {
           
           String requete2 = "INSERT INTO friendRequests(receiverId,senderId)"
                    + "VALUES (?,?)";  
            PreparedStatement pst = cnx.prepareStatement(requete2);
            
            pst.setInt(1, idReceiver);
            pst.setInt(2, idSender);
            
            pst.executeUpdate();
            System.out.println("Friend request added!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
   }   
   
   public List<Integer> selectFriendRequests(int receiverId){
       ArrayList<Integer> sendersIdList = new ArrayList<>();
       try {
            String requete = "SELECT senderId FROM friendRequests WHERE = "+receiverId;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
               sendersIdList.add(rs.getInt("senderId"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return sendersIdList;  
   }

private Long dayToMiliseconds(int days){
    Long result = Long.valueOf(days * 24 * 60 * 60 * 1000);
    return result;
}

public Timestamp addDays(int days, Timestamp t1) throws Exception{
    if(days < 0){
        throw new Exception("Day in wrong format.");
    }
    Long miliseconds = dayToMiliseconds(days);
    return new Timestamp(t1.getTime() + miliseconds);
}

}
