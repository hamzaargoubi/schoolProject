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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pi.esprit.entities.InputValidation;
import pi.esprit.entities.personnes;
import pi.esprit.entities.videos;
import pi.esprit.services.PersonneCRUD;
import pi.esprit.services.VideoCRUD;

/**
 * FXML Controller class
 *
 * @author Fakhri Argoubi
 */
public class AddButtonInterfaceController implements Initializable {

    @FXML
    private GridPane gridpane;
    @FXML
    private TextField lastNameTf;
    @FXML
    private TextField firstNameTf;
    @FXML
    private TextField adressTf;
    @FXML
    private TextField profileTf;
    @FXML
    private TextField loginTf;
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
    private Button SaveBtn;
    @FXML
    private PasswordField pwdPF;
    @FXML
    private Button backBtn;
    @FXML
    private Label addLb;
    @FXML
    private Button uploadBtn;
    @FXML
    private ImageView imgViewer;
    @FXML
    private Label emailLb;
    @FXML
    private Label pwdLb1;
    @FXML
    private TextField emailtf;
    
    
    
    Image image ;
    String photo;
    
    @FXML
    private Text textImage;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save(ActionEvent event) {
        final personnes p= new personnes(0, lastNameTf.getText(), firstNameTf.getText(), emailtf.getText()
                , profileTf.getText(),null , loginTf.getText(), pwdPF.getText(),
                adressTf.getText());
                    
            PersonneCRUD pc = new PersonneCRUD();
            pc.ajouterPersonne3(p);
            
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION); 
            a.setContentText("User successfully added");
            if (a.getResult() == ButtonType.OK){
               
            }
            a.show();
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) lastNameLb.getScene().getWindow();
         Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("AdminAccountManagement.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
        Scene scene = new Scene(root);
        stage.setTitle("Hello Guys!");
        stage.setScene(scene);
        stage.show();
    }

    
    
    
    @FXML
    private void uploadAvatar(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file);
        
        if (file != null) {
            String p = file.toURI().toString();
            System.out.println(p);
            
            photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            image = new Image(file.getAbsoluteFile().toURI().toString(), imgViewer.getFitWidth(), imgViewer.getFitHeight(), true, true);
            // txt_vid.setText(video);
            textImage.setText(photo);
            InputValidation u = new InputValidation();
            String image1;
            image1 = "C:\\wamp64\\www\\mawhebti\\images\\" + photo;
            System.out.println(photo);
            imgViewer.setImage(image);
           
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
    
}
