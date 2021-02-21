/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import pi.esprit.entities.loggedmembre;
import pi.esprit.entities.personnes;
import pi.esprit.services.PersonneCRUD;

/**
 *
 * @author Fakhri Argoubi
 */
public class PasswordChangeInterface {
    public static void changePassword(Stage stage){
        personnes p = loggedmembre.getP();
        
        Label currentPwdLb = new Label();
        currentPwdLb.setText("current password");
        currentPwdLb.setPrefWidth(100);
        PasswordField currentPwdPf = new PasswordField();
        currentPwdPf.setPrefWidth(100);
        
        Label newPwdLb = new Label();
        newPwdLb.setText("new password");
        newPwdLb.setPrefWidth(100);
        PasswordField newPwdPf = new PasswordField();
        newPwdPf.setPrefWidth(100);
        
        Label confirmNewPwdLb = new Label();
        confirmNewPwdLb.setText("confirm your new password");
        confirmNewPwdLb.setPrefWidth(100);
        PasswordField confirmNewPwdPf = new PasswordField();
        confirmNewPwdPf.setPrefWidth(100);
        
        Button save = new Button("save");
        save.setOnAction(e->{
            if (currentPwdPf.getText().equals(p.getPwd())&& newPwdPf.getText().equals(confirmNewPwdPf.getText())){
                PersonneCRUD pc = new PersonneCRUD();
                pc.updatePassWord(p.getId_user(), newPwdPf.getText());
            }
            
        });
        
    }
    
}
