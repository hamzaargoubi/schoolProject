/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pi.esprit.entities.personnes;
import pi.esprit.entities.videos;
import pi.esprit.services.statistiqueCrud;
import pi.esprit.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author bureau
 */
public class StatController implements Initializable {

    @FXML
    private PieChart pc;
    @FXML
    private Button btn_load;
    @FXML
    private Label lb;
    @FXML
    private Button btn_back;
    Connection cnx;
    PreparedStatement pst;
    ResultSet st;
   ArrayList<String> nom=new ArrayList<>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    

    @FXML
    private void load_pc(ActionEvent event) throws SQLException  {   
                statistiqueCrud sr=new statistiqueCrud();
                
            ObservableList<Data> details= FXCollections.observableArrayList();
               details.addAll(new PieChart.Data("sports",sr.getSport()),
                        new PieChart.Data("arts",sr.getArts()),
                        new PieChart.Data("music",sr.getMusic()),
                        new PieChart.Data("danse",sr.getDanse()),
                        new PieChart.Data("other",sr.getOther())
                );
                pc.setData(details);
                pc.setTitle("statistiques des videos");
                pc.setLegendSide(Side.BOTTOM);
                pc.setLabelsVisible(true);
                for(final PieChart.Data data :pc.getData()){
                    
                    data.getNode().addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>(){
                        @Override
                        public void handle(MouseEvent event) {
                            lb.setText(String.valueOf((data.getPieValue()/150)*100+"%"));
                        }
                        
                    });
                }
       
           
    }
    
    @FXML
    private void back(ActionEvent event) {
         btn_back.getScene().getWindow().hide();
            Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("Menuprincipale.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());       
        }
            Stage mainstage=new Stage();
            Scene scene=new Scene(root);
            mainstage.setScene(scene); 
            mainstage.show();
    }
    
}
