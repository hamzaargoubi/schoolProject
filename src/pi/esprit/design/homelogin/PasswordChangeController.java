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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pi.esprit.entities.VerificationCode;
import pi.esprit.entities.loggedmembre;
import pi.esprit.entities.personnes;
import pi.esprit.services.PersonneCRUD;
import pi.esprit.services.VerificationCodeCRUD;

/**
 * FXML Controller class
 *
 * @author Fakhri Argoubi
 */
public class PasswordChangeController implements Initializable {

    @FXML
    private Label changePwdLb;
    @FXML
    private Button backBtn;
    @FXML
    private TextField verifCodeTf;
    @FXML
    private PasswordField currentPwdPf;
    @FXML
    private PasswordField newPwdPf;
    @FXML
    private PasswordField verifNewPwdPf;
    @FXML
    private Button saveBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) {
        verifCodeTf.clear();
        currentPwdPf.clear();
        newPwdPf.clear();
        verifNewPwdPf.clear();
        Stage stage = (Stage) verifCodeTf.getScene().getWindow();
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
    private void save(ActionEvent event) {
        personnes p = loggedmembre.getP();
        VerificationCode vCode = new VerificationCode();
        VerificationCodeCRUD vc = new VerificationCodeCRUD();
        vCode=vc.selectVerifCode(p.getId_user());
        System.out.println(vCode.toString());
        if (vCode.getVerificationCode().equals(verifCodeTf.getText())&& currentPwdPf.getText().equals(p.getPwd())
                && newPwdPf.getText().equals(verifNewPwdPf.getText())){
                PersonneCRUD pc = new PersonneCRUD();
                pc.updatePassWord(p.getId_user(), newPwdPf.getText());
                vc.updateValidity(p.getId_user());
                verifCodeTf.clear();
            currentPwdPf.clear();
            newPwdPf.clear();
            verifNewPwdPf.clear(); 
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION); 
            a.setContentText("passwod successfully changed");
            if (a.getResult() == ButtonType.OK){
               
            }
            a.show();
            }
        else {
            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.INFORMATION); 
            a.setContentText("something went wrong");
            if (a.getResult() == ButtonType.OK){
               
            }
            a.show();
        }
    }
    
}
