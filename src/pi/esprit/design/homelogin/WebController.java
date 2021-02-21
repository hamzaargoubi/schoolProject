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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bureau
 */
public class WebController implements Initializable {

    @FXML
    private WebView vieww;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    final WebEngine web=vieww.getEngine();
    String urlweb="http://google.com";
    web.load(urlweb);
    }    

    @FXML
    private void fb(ActionEvent event) {
         final WebEngine web=vieww.getEngine();
    String urlweb="https://web.facebook.com/";
    web.load(urlweb);
    }

    @FXML
    private void wh(ActionEvent event) {
         final WebEngine web=vieww.getEngine();
    String urlweb="http://web.whatsapp.com/";
    web.load(urlweb);
    }

    @FXML
    private void yt(ActionEvent event) {
         final WebEngine web=vieww.getEngine();
    String urlweb="https://www.youtube.com/";
    web.load(urlweb);
    }

    @FXML
    private void back(ActionEvent event) {
         back.getScene().getWindow().hide();
            Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("Menuprincipale.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());       
        }
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene); 
            mainstage.show();
        
    }

}
