/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pi.esprit.entities.personnes;
import pi.esprit.services.PersonneCRUD;

/**
 *
 * @author Fakhri Argoubi
 */
public class UserGestionCompte {
    public static void UserPersonalInofrmation(Stage stage){
      BorderPane bp  = new BorderPane();
      HBox upperh = new HBox();
      VBox vcenter = new VBox();
      
      
      //left side
      Button backBtn = new Button ("back");
      backBtn.setOnAction(new EventHandler<ActionEvent>(){
          @Override
          public void handle(ActionEvent event) {
          }
        
    });
      Label titleLb = new Label();
      titleLb.setText("Personal Informations");
      VBox imgBox = new VBox();
      imgBox.setSpacing(40);
        ImageView img = new ImageView();
        Button changeAvatarBtn = new Button("change avatar");
      upperh.getChildren().addAll(backBtn, titleLb,imgBox);
      upperh.setSpacing(150);
      bp.setTop(upperh);
        HBox nameBox = new HBox();
        HBox firstNameBox = new HBox();
        HBox adressBox = new HBox();
        HBox loginBox = new HBox();
        HBox pwdBox = new HBox();
        
        //id from login interface
        int id = 1;
        PersonneCRUD pc = new PersonneCRUD();
        personnes p = pc.selectUser(id);
        
        //setting up the name
        Label nameLb = new Label();
        nameLb.setText("Last name");
        nameLb.setPrefWidth(100);
        Label sqlNameLb = new Label();
        sqlNameLb.setText(p.getNom());
        sqlNameLb.setPrefWidth(100);
        nameBox.setSpacing(40);
        nameBox.getChildren().addAll(nameLb,sqlNameLb);
        //setting up the first name
        Label firstNameLb = new Label();
        firstNameLb.setText("First Name");
        firstNameLb.setPrefWidth(100);
        Label sqlFirstNameLb = new Label();
        sqlFirstNameLb.setText(p.getPrenom());
        sqlFirstNameLb.setPrefWidth(100);
        firstNameBox.setSpacing(40);
        firstNameBox.getChildren().addAll(firstNameLb,sqlFirstNameLb);
        //setting up the adress
        Label adressLb = new Label();
        adressLb.setText("Adress");
        adressLb.setPrefWidth(100);
        Label sqlAdressLb = new Label();
        sqlAdressLb.setText(p.getAdress());
        sqlAdressLb.setPrefWidth(100);
        adressBox.setSpacing(40);
        adressBox.getChildren().addAll(adressLb,sqlAdressLb);
        //setting up the login
        Label loginLb = new Label();
        loginLb.setText("Login");
        loginLb.setPrefWidth(100);
        Label sqlLoginLb = new Label();
        sqlLoginLb.setText(p.getLogin());
        sqlLoginLb.setPrefWidth(100);
        loginBox.setSpacing(40);
        loginBox.getChildren().addAll(loginLb,sqlLoginLb);
        
        Button changePwdBtn = new Button("change Password");
        changePwdBtn.setOnAction(e->{
            PasswordChangeInterface.changePassword(stage);
        });
        pwdBox.getChildren().addAll(changePwdBtn);
         
        vcenter.setSpacing(40);
        vcenter.getChildren().addAll(nameBox,firstNameBox,adressBox,loginBox,pwdBox);
        bp.setCenter(vcenter);
        
        
      stage.setMinHeight(600);
      stage.setMinWidth(800);
      Scene scene = new Scene(bp);
      stage.setScene(scene);
      stage.show();
         
        
    }
}
