/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pi.esprit.services.PersonForTab;
import pi.esprit.services.PersonneCRUD;

/**
 *
 * @author Fakhri Argoubi
 */
public class GestionDesCompteInterface {
     public  void showAdminInterface(Stage stage){
        
    
    PersonneCRUD p = new PersonneCRUD();
         TableView<PersonForTab> tableview = new TableView<PersonForTab>();
        final ObservableList<PersonForTab> data = FXCollections.observableArrayList(p.selectUsers());
        final HBox hb = new HBox();
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(1280);
        stage.setHeight(720);
 
        final Label label = new Label("Users");
        label.setFont(new Font("Arial", 20));
        TextField searchTf = new TextField();
        searchTf.setPromptText("search");
        
        tableview.setEditable(true);
        //id column 
        TableColumn idColumn = new TableColumn("Id");
        idColumn.setMinWidth(100);
        idColumn.setCellValueFactory(
        new PropertyValueFactory<PersonForTab, String>("id_user"));
        // name column
        TableColumn nomColumn = new TableColumn("Name");
        nomColumn.setMinWidth(100);
        nomColumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("nom"));
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<PersonForTab, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<PersonForTab, String> t) {
                    ((PersonForTab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setNom(t.getNewValue());
                    PersonForTab p = tableview.getSelectionModel().getSelectedItem();
                    PersonneCRUD pc = new PersonneCRUD();
                    pc.updatePersonne2(p);
                }
            }
        );
        //prenom Column
        
        TableColumn prenomColumn = new TableColumn("Prenom");
        prenomColumn.setMinWidth(100);
        prenomColumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("prenom"));
        prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomColumn.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<PersonForTab, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<PersonForTab, String> t) {
                    ((PersonForTab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setPrenom(t.getNewValue());
                    PersonForTab p = tableview.getSelectionModel().getSelectedItem();
                    PersonneCRUD pc = new PersonneCRUD();
                    pc.updatePersonne2(p);

                }
            }
        );
 
        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<PersonForTab, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<PersonForTab, String> t) {
                    ((PersonForTab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEmail(t.getNewValue());
                    PersonForTab p = tableview.getSelectionModel().getSelectedItem();
                    PersonneCRUD pc = new PersonneCRUD();
                    pc.updatePersonne2(p);

                }
            }
        );
        
        TableColumn profileColumn = new TableColumn("Profile");
        profileColumn.setMinWidth(50);
        profileColumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("profil"));
        profileColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        profileColumn.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<PersonForTab, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<PersonForTab, String> t) {
                    ((PersonForTab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setProfil(t.getNewValue());
                    PersonForTab p = tableview.getSelectionModel().getSelectedItem();
                    PersonneCRUD pc = new PersonneCRUD();
                    pc.updatePersonne2(p);
                }
            }
        );
        
        TableColumn loginColumn = new TableColumn("Login");
        loginColumn.setMinWidth(100);
        loginColumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("login"));
        loginColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        loginColumn.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<PersonForTab, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<PersonForTab, String> t) {
                    ((PersonForTab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setLogin(t.getNewValue());
                    PersonForTab p = tableview.getSelectionModel().getSelectedItem();
                    PersonneCRUD pc = new PersonneCRUD();
                    pc.updatePersonne2(p);
                }
            }
        );
        
        TableColumn pwdColumn = new TableColumn("Password");
        pwdColumn.setMinWidth(100);
        pwdColumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("pwd"));
        pwdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pwdColumn.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<PersonForTab, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<PersonForTab, String> t) {
                    ((PersonForTab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setPwd(t.getNewValue());
                    PersonForTab p = tableview.getSelectionModel().getSelectedItem();
                    PersonneCRUD pc = new PersonneCRUD();
                    pc.updatePersonne2(p);
                }
            }
        );
        
        TableColumn adressColumn = new TableColumn("Adress");
        adressColumn.setMinWidth(100);
        adressColumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("adress"));
        adressColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        adressColumn.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<PersonForTab, String>>() {
                @Override
                public void handle(TableColumn.CellEditEvent<PersonForTab, String> t) {
                    ((PersonForTab) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setAdress(t.getNewValue());
                    PersonForTab p = tableview.getSelectionModel().getSelectedItem();
                    PersonneCRUD pc = new PersonneCRUD();
                    pc.updatePersonne2(p);
                }
            }
        );
        
        FilteredList<PersonForTab> filteredData = new FilteredList<>(data, b -> true);
        searchTf.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(PersonForTab -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (PersonForTab.getId_user().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (PersonForTab.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(PersonForTab.getPrenom()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});        
		SortedList<PersonForTab> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		tableview.setItems(sortedData);
        
        tableview.getColumns().addAll(idColumn, nomColumn,prenomColumn, 
                emailCol,profileColumn,loginColumn,pwdColumn,adressColumn);
 
        
        
        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                
              AddButton.addUser(stage);
                
            }
        });
        
        //Delete process
        final Button deleteButton = new Button("delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override 
        public void handle(ActionEvent e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + "Are you sure you want to delete this person"
                    + " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                PersonForTab p = tableview.getSelectionModel().getSelectedItem();
                data.remove(p);
                PersonneCRUD pc = new PersonneCRUD();
                pc.supprimerPersonne2(Integer.parseInt(p.getId_user()));
            }
        }
    });
        
    
    
    
    
    
    
    
        hb.getChildren().addAll(deleteButton, addButton);
        hb.setSpacing(10);
        
        final Button back = new Button("back");
        back.setOnAction(e->{
            Parent root=null;
            Stage mainstage=new Stage();
        try {
             root=FXMLLoader.load(getClass().getResource("adminmenu.fxml"));
            
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
        Scene scene1=new Scene(root);
            mainstage.setScene(scene1);
            mainstage.show();
        });
        HBox h = new HBox();
        h.setSpacing(100);
        h.getChildren().addAll(back,searchTf);
        final VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(h,label, tableview, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();

           
    
    
    }
    
}
