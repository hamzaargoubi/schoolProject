/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Fakhri Argoubi
 */
public class MessagesInterfaceController implements Initializable {

    @FXML
    private VBox conversationsVbox;
    @FXML
    private Button backBtn;
    @FXML
    private TextField chatBox;
    @FXML
    private Label receiverLabel;
    @FXML
    private VBox messagesVbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void sendMessages(ActionEvent event) {
    }
    
}
