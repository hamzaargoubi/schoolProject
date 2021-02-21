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
import pi.esprit.utils.MyConnection;

/**
 *
 * @author Fakhri Argoubi
 */
public class FriendRequestsAndMessagesCRUD {
    Connection cnx;

    public FriendRequestsAndMessagesCRUD() {
        cnx = MyConnection.getInstance().getCnx();
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
   
   public void deleteFriendRequest(int receiverId,int senderId){
       try {
            String requete = "DELETE FROM friendRequests WHERE senderId=? AND receiverId=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, senderId);
            pst.setInt(2, receiverId);
            pst.executeUpdate();
            System.out.println("Personne supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
   
   public void addFriend(int personId,int hisFriendId){
       try {
           
           String requete1 = "INSERT INTO friends(personId,hisFriendId)"
                    + "VALUES (?,?)";  
            PreparedStatement pst = cnx.prepareStatement(requete1);
            
            pst.setInt(1,personId );
            pst.setInt(2, hisFriendId);
            
            pst.executeUpdate();
            System.out.println("Friend request added!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       
       try {
           
           String requete1 = "INSERT INTO friends(personId,hisFriendId)"
                    + "VALUES (?,?)";  
            PreparedStatement pst = cnx.prepareStatement(requete1);
            
            pst.setInt(1,hisFriendId );
            pst.setInt(2, personId);
            
            pst.executeUpdate();
            System.out.println("Friend request added!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
   }
   public void deleteFriend(int personId,int hisFriendId){
     try {
            String requete = "DELETE FROM friends WHERE personId=? AND hisFriendId=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, personId);
            pst.setInt(2, hisFriendId);
            pst.executeUpdate();
            System.out.println("Personne supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     try {
            String requete = "DELETE FROM friends WHERE personId=? AND hisFriendId=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, hisFriendId);
            pst.setInt(2, personId);
            pst.executeUpdate();
            System.out.println("Personne supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   }
   
   
   public List<Integer> selectListOfFriends(int personId){
       List<Integer> listOfFriends = new ArrayList<>();
       try{
       String requete = "SELECT hisFriendId FROM friends WHERE personId= "+personId;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
               listOfFriends.add(rs.getInt("senderId"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return listOfFriends;
   }
   
   public boolean checkFriendship(int personId,int hisFriendId){
       try{
       String requete = "SELECT * FROM friends WHERE personId= ? AND hisFriendId=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, personId);
            pst.setInt(1, hisFriendId);
            ResultSet rs = pst.executeQuery(requete);
            if(rs.next()){
               return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return false;
   }
   
}
