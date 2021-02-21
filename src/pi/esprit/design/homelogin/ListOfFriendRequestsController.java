/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.stage.Stage;
import pi.esprit.entities.loggedmembre;
import static pi.esprit.entities.loggedmembre.p;
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
public class ListOfFriendRequestsController implements Initializable {

    @FXML
    private TableView<PersonForTab> tableview;
    @FXML
    private TableColumn<PersonForTab,String> idColumn;
    @FXML
    private TableColumn<PersonForTab,String> firstNameColumn;
    @FXML
    private TableColumn<PersonForTab,String> lastNameColumn;
    @FXML
    private Button reportBtn;
    @FXML
    private Label label;
    @FXML
    private Button backBtn;
    @FXML
    private TextField searchTf;
    @FXML
    private Button viewProfileBtn;
    @FXML
    private Button acceptInvitationBtn;
    @FXML
    private Button declineInviteBtn;
    
    
    
    
    final ObservableList<PersonForTab> data = FXCollections.observableArrayList();
    PersonneCRUD pc = new PersonneCRUD();
   personnes p = loggedmembre.getP();
    BanAndReportsCRUD brc = new BanAndReportsCRUD();
    FriendRequestsAndMessagesCRUD framc = new FriendRequestsAndMessagesCRUD();
    List<Integer> listOfFriendRequests = framc.selectFriendRequests(p.getId_user());
    
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listOfFriendRequests.forEach(e->data.add(pc.selectUserForTab(e)));
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
                tableview.getColumns().addAll(lastNameColumn,firstNameColumn);
    }    

    @FXML
    private void report(ActionEvent event) {
        PersonForTab pSelected = tableview.getSelectionModel().getSelectedItem();
        
        if(brc.checkReportStatus(Integer.parseInt(pSelected.getId_user()), p.getId_user())){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("reporting");
            alert1.setHeaderText("Information");
            alert1.setContentText("you have already reported this person");
            alert1.show();
        }
        else{
        brc.incrementNumberOfReports(Integer.parseInt(pSelected.getId_user()));
        if(brc.selectNumberOfReports(Integer.parseInt(pSelected.getId_user()))>20){
            brc.incrementNumberOfReports(Integer.parseInt(pSelected.getId_user()));
        }
        brc.reportRelations(Integer.parseInt(pSelected.getId_user()), p.getId_user());
        }
    }

    @FXML
    private void back(ActionEvent event) {
        Stage stage = (Stage) label.getScene().getWindow();
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
    private void viewProfile(ActionEvent event) {
        PersonForTab pSelected = tableview.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void acceptInvite(ActionEvent event) {
        PersonForTab pSelected = tableview.getSelectionModel().getSelectedItem();
        framc.addFriend(p.getId_user(),Integer.parseInt(pSelected.getId_user()));
        framc.deleteFriendRequest(p.getId_user(),Integer.parseInt(pSelected.getId_user()));
    }

    @FXML
    private void declineInvite(ActionEvent event) {
        PersonForTab pSelected = tableview.getSelectionModel().getSelectedItem();
        framc.deleteFriendRequest(p.getId_user(),Integer.parseInt(pSelected.getId_user()));
    }
    
}
