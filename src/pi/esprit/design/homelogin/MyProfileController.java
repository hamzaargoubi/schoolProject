/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pi.esprit.entities.InputValidation;
import pi.esprit.entities.loggedmembre;
import pi.esprit.entities.personnes;
import pi.esprit.services.MailService;
import pi.esprit.services.PersonneCRUD;

/**
 * FXML Controller class
 *
 * @author Fakhri Argoubi
 */
public class MyProfileController implements Initializable {

    @FXML
    private GridPane gridpane;
    @FXML
    private Label lastNameLb;
    @FXML
    private Label firstNameLb;
    @FXML
    private Label adressLb;
    @FXML
    private Label profileLb;
    @FXML
    private Label loginLb;
    @FXML
    private Label emailLb;
    @FXML
    private Button backBtn;
    @FXML
    private Label addLb;
    @FXML
    private Button uploadBtn;
    @FXML
    private Label sqlLastNameLb;
    @FXML
    private Label sqlEmailLB;
    @FXML
    private Label sqlLoginLb;
    @FXML
    private Label sqlProfileLb;
    @FXML
    private Label sqlAdressLb;
    @FXML
    private ImageView imgviewer;
    @FXML
    private Label sqlFirstNameLb;
    @FXML
    private Button changePwdBtn;
    
    Image image ;
    String photo;
    @FXML
    private Text textImage;
    @FXML
    private Button searchBtn;
    @FXML
    private Button friendRequestsBtn;
    @FXML
    private Button conversationBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        personnes lm = loggedmembre.getP();
        PersonneCRUD pc = new PersonneCRUD();
        personnes p = pc.selectUser(lm.getId_user());
        
        sqlLastNameLb.setText(p.getNom());
        sqlFirstNameLb.setText(p.getPrenom());
        sqlProfileLb.setText(p.getProfil());
        sqlEmailLB.setText(p.getEmail());
        sqlAdressLb.setText(p.getAdress());
        sqlLoginLb.setText(p.getLogin());
    }    

    @FXML
    private void changePassword(ActionEvent event) {
        personnes p = loggedmembre.getP();
        
        MailService ms = new MailService();
        try {
            ms.sendVerifMail(p.getEmail(),p.getId_user());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());        }
        Stage stage = (Stage) sqlAdressLb.getScene().getWindow();
         Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("PasswordChange.fxml"));
        } catch (IOException ex) {
                  
            
    
            System.out.println(ex.getMessage());        }
        Scene scene = new Scene(root);
    
       
        
        
        stage.setTitle("Hello Guys!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            sqlAdressLb.getScene().getWindow().hide();
            Parent root=FXMLLoader.load(getClass().getResource("Menuprincipale.fxml"));
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene);
            mainstage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    @FXML
    private void changeAvatar(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file);
        
        if (file != null) {
            String p = file.toURI().toString();
            System.out.println(p);
            
            photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            image = new Image(file.getAbsoluteFile().toURI().toString(), imgviewer.getFitWidth(), imgviewer.getFitHeight(), true, true);
            // txt_vid.setText(video);
            textImage.setText(photo);
            InputValidation u = new InputValidation();
            String image1;
            image1 = "C:\\wamp64\\www\\mawhebti\\images\\" + photo;
            System.out.println(photo);
            imgviewer.setImage(image);
           
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Saving image");
            alert1.setHeaderText("Information");
            alert1.setContentText("do you want save this image?");
            Optional<ButtonType> action = alert1.showAndWait();
            if (action.get() == ButtonType.OK) {
                
                u.CopyImage(image1, file.toPath().toString());
                
                
            }
        }
    }

    @FXML
    private void search(ActionEvent event) {
        try {
            sqlAdressLb.getScene().getWindow().hide();
            Parent root=FXMLLoader.load(getClass().getResource("ListOfusers.fxml"));
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene);
            mainstage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    @FXML
    private void friendRequests(ActionEvent event) {
        try {
            sqlAdressLb.getScene().getWindow().hide();
            Parent root=FXMLLoader.load(getClass().getResource("ListOfFriendRequests.fxml"));
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene);
            mainstage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    @FXML
    private void conversation(ActionEvent event) {
    }
    
}
