/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import pi.esprit.services.PersonForTab;
import pi.esprit.services.PersonneCRUD;

/**
 * FXML Controller class
 *
 * @author Fakhri Argoubi
 */
public class AdminAccountManagementController implements Initializable {

    @FXML
    private TableView<PersonForTab> tableview;
    @FXML
    private TableColumn<PersonForTab,String> idColumn;
    @FXML
    private TableColumn<PersonForTab,String> lastNameColumn;
    @FXML
    private TableColumn<PersonForTab,String> firstNameColumn;
    @FXML
    private TableColumn<PersonForTab,String> adressColumn;
    @FXML
    private Button addUserBtn;
    @FXML
    private Button deleteUserBtn;
    @FXML
    private TextField searchTf;
    @FXML
    private TableColumn<PersonForTab,String> emailColumn;
    @FXML
    private TableColumn<PersonForTab,String> profileColumn;
    @FXML
    private TableColumn<PersonForTab,String> loginColumn;
    @FXML
    private TableColumn<PersonForTab,String> passwordClolumn;
    @FXML
    private Label listUsersLb;

    PersonneCRUD p = new PersonneCRUD();
    final ObservableList<PersonForTab> data = FXCollections.observableArrayList(p.selectUsers());
    @FXML
    private Button backBtn;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableview.setEditable(true);
        idColumn.setCellValueFactory(new PropertyValueFactory<PersonForTab, String>("id_user"));
        //lastName 
        lastNameColumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("nom"));
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setOnEditCommit(
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
        //first name
        firstNameColumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("prenom"));
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(
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
        emailColumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("email"));
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(
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
        passwordClolumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("pwd"));
        passwordClolumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordClolumn.setOnEditCommit(
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
                tableview.getColumns().clear();
                tableview.getColumns().addAll(idColumn,lastNameColumn,firstNameColumn,emailColumn,profileColumn,
                        loginColumn,passwordClolumn,adressColumn);
        
    }    

    @FXML
    private void addUser(ActionEvent event) {
        Stage stage = (Stage) deleteUserBtn.getScene().getWindow();
         Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("AddButtonInterface.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
        Scene scene = new Scene(root);
        stage.setTitle("Hello Guys!");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void deleteUser(ActionEvent event) {
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

    @FXML
    private void back(ActionEvent event) {
        try {
            listUsersLb.getScene().getWindow().hide();
            Parent root=FXMLLoader.load(getClass().getResource("adminmenu.fxml"));
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene);
            mainstage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
    }

    
}
