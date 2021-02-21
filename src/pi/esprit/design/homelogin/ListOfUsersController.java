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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import pi.esprit.entities.loggedmembre;
import pi.esprit.entities.personnes;
import pi.esprit.services.BanAndReportsCRUD;
import pi.esprit.services.FriendRequestsAndMessagesCRUD;
import pi.esprit.services.PersonForTab;
import pi.esprit.services.PersonneCRUD;

/**
 * FXML Controller class
 *
 * @author Fakhri Argoubi
 */
public class ListOfUsersController implements Initializable {

    @FXML
    private TableView<PersonForTab> tableview;
    @FXML
    private TableColumn<PersonForTab,String> lastNameColumn;
    @FXML
    private TableColumn<PersonForTab,String> firstNameColumn;
    @FXML
    private Button reportBtn;
    @FXML
    private Label label;
    @FXML
    private Button backBtn;
    @FXML
    private TextField searchTf;
    
    FriendRequestsAndMessagesCRUD framc = new FriendRequestsAndMessagesCRUD();
    BanAndReportsCRUD brc = new BanAndReportsCRUD();
    personnes p = loggedmembre.getP();
    PersonneCRUD pc = new PersonneCRUD();
    final ObservableList<PersonForTab> data = FXCollections.observableArrayList(pc.selectUsers());
    @FXML
    private TableColumn<PersonForTab,String> idColumn;
    @FXML
    private Button sendFriendRequestBtn;
    @FXML
    private Button viewProfileBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        idColumn.setCellValueFactory(new PropertyValueFactory<PersonForTab, String>("id_user"));
        lastNameColumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("nom"));
        firstNameColumn.setCellValueFactory(
            new PropertyValueFactory<PersonForTab, String>("prenom"));
        FilteredList<PersonForTab> filteredData = new FilteredList<>(data, b -> true);
        searchTf.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(PersonForTab -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				  if (PersonForTab.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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
                tableview.getColumns().addAll(idColumn,lastNameColumn,firstNameColumn);
    }    

    @FXML
    private void report(ActionEvent event) {
         PersonForTab pSelected = tableview.getSelectionModel().getSelectedItem();
         PersonneCRUD pc = new PersonneCRUD();
         personnes pSelect2=pc.selectUser(Integer.parseInt(pSelected.getId_user()) );
        System.out.println(pSelect2.toString()+p.toString());
        if(brc.checkReportStatus(pSelect2.getId_user(), p.getId_user())){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("reporting");
            alert1.setHeaderText("Information");
            alert1.setContentText("you already have reported this person");
            alert1.show();
        }
        else{
        brc.incrementNumberOfReports(Integer.parseInt(pSelected.getId_user()));
        if(brc.selectNumberOfReports(Integer.parseInt(pSelected.getId_user()))>20){
            brc.ban(Integer.parseInt(pSelected.getId_user()),14);
        }
        brc.reportRelations(Integer.parseInt(pSelected.getId_user()), p.getId_user());
        
        }
        

    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) searchTf.getScene().getWindow();
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
    private void sendFriendRequest(ActionEvent event) {
        PersonForTab pSelected = tableview.getSelectionModel().getSelectedItem();
        if (framc.checkFriendship(p.getId_user(),Integer.parseInt(pSelected.getId_user()))){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Sending friend request");
            alert1.setHeaderText("Information");
            alert1.setContentText("you are already a friend of this person");
            alert1.show();
        }
        else{
            framc.insertFriendRequest(p.getId_user(),Integer.parseInt(pSelected.getId_user()));
    }
    }

    @FXML
    private void viewProfile(ActionEvent event) {
        PersonForTab pSelected = tableview.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) searchTf.getScene().getWindow();
        OtherProfilesController op = new OtherProfilesController();
        op.setId(Integer.parseInt(pSelected.getId_user()));
         Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("OtherProfiles.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());        }
        Scene scene = new Scene(root);
        stage.setTitle("Hello Guys!");
        stage.setScene(scene);
        stage.show();
    }
    
}
