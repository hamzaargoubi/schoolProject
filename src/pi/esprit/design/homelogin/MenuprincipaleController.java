/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pi.esprit.entities.loggedmembre;
import pi.esprit.entities.personnes;

/**
 * FXML Controller class
 *
 * @author bureau
 */
public class MenuprincipaleController implements Initializable {

    @FXML
    private Button btn_home;
    @FXML
    private Button btn_account;
    @FXML
    private Button btn_videos;
    @FXML
    private Button btn_comp;
    @FXML
    private Button btn_stat;
    @FXML
    private Button btn_nrws;
    @FXML
    private Button btn_abbout;
    @FXML
    private Button btn_Rss;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(ActionEvent event) {
         btn_home.getScene().getWindow().hide();
            Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("login.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());       
        }
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene); 
            mainstage.show();
    }

    @FXML
    private void account(ActionEvent event) {
         Stage stage = (Stage) btn_videos.getScene().getWindow();
         Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("MyProfile.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
        Scene scene = new Scene(root);
        stage.setTitle("Hello Guys!");
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void pagevideos(ActionEvent event) {
         btn_videos.getScene().getWindow().hide();
            Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("video.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());       
        }
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene); 
            mainstage.show();
    }

    @FXML
    private void page_competions(ActionEvent event) {
        
    }

    @FXML
    private void pagestat(ActionEvent event) {
         btn_stat.getScene().getWindow().hide();
            Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("stat.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());       
        }
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene); 
            mainstage.show();
    
    }

    @FXML
    private void pagenews(ActionEvent event) {
         btn_Rss.getScene().getWindow().hide();
            Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("CNN.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());       
        }
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene); 
            mainstage.show();
        
    }

    @FXML
    private void pageabbout(ActionEvent event) {
         btn_abbout.getScene().getWindow().hide();
            Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("Web.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());       
        }
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene); 
            mainstage.show();
        
    }

    @FXML
    private void pageRss(ActionEvent event) {
         btn_nrws.getScene().getWindow().hide();
            Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("help.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());       
        }
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene); 
            mainstage.show();
    }
    
}
