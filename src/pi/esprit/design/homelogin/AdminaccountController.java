/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pi.esprit.entities.InputValidation;
import pi.esprit.entities.loggedmembre;
import pi.esprit.entities.personnes;
import pi.esprit.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author bureau
 */
public class AdminaccountController implements Initializable {

    @FXML
    private Button btn_back;
    @FXML
    private TextField tfirstname;
    @FXML
    private TextField tlastname;
    @FXML
    private TextField tprofil;
    @FXML
    private TextField tadress;
    @FXML
    private TextField tlogin;
    @FXML
    private TextField temail;
    @FXML
    private TextField tpassword;
    @FXML
    private TableView<personnes> tview;
    @FXML
    private TableColumn<personnes, String> colfirstname;
    @FXML
    private TableColumn<personnes, String> collastname;
    @FXML
    private TableColumn<personnes, String> colprofil;
    @FXML
    private TableColumn<personnes, String> coladress;
    @FXML
    private TableColumn<personnes, String> collogin;
    @FXML
    private TableColumn<personnes, String> colemail;
    @FXML
    private TableColumn<personnes, Integer> colpassword;
    @FXML
    private Button btn_insert;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_update;
    @FXML
    private ImageView img;
    @FXML
    private Button cmd_attach;
    @FXML
    private TextField txt_path;
ResultSet rs=null;
PreparedStatement pst;
private ImageIcon format=null;
String filename=null;
int s=0;
byte[] personne_image=null;
String photo;
Image image;
    @FXML
    private TableColumn<personnes, String> colphoto;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showpersonnes();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }
 Connection cnx;    
 personnes pd = loggedmembre.getP();

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

    @FXML
    private void insert(ActionEvent event) throws SQLException {
   try {
            cnx = MyConnection.getInstance().getCnx();
            String requete="insert into personnes(nom,prenom,profil,photo,login,pwd,adress,email) values (?,?,?,?,?,?,?,?)";
            pst=cnx.prepareStatement(requete);
            pst.setString(1,tfirstname.getText());
            pst.setString(2,tlastname.getText());
            pst.setString(3,tprofil.getText());
            pst.setBytes(4,personne_image);
            pst.setString(5,tlogin.getText());
            pst.setString(6,tpassword.getText());
            pst.setString(7,tadress.getText());
            pst.setString(8,temail.getText());
            pst.execute();
            showpersonnes();
            JOptionPane.showMessageDialog(null,"Person added");
        } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null,ex);
        }

         
    
    }

    @FXML
    private void delete(ActionEvent event) {
   cnx = MyConnection.getInstance().getCnx();
    try {
            String requete = "DELETE FROM personnes WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            personnes p=new personnes();
            pst.setInt(1, p.getId_user());
            pst.executeUpdate();
            showpersonnes();
            System.out.println("Person deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
   

    }

    @FXML
    private void update(ActionEvent event) {
        try {
            cnx = MyConnection.getInstance().getCnx();
            String requete = "UPDATE personnes SET nom=?,prenom=? "
                    + "WHERE id_user=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            personnes p=new personnes();
            pst.setInt(1, p.getId_user());
            pst.setString(2,tlastname.getText());
            
            pst.setString(3,tfirstname.getText());
            pst.executeUpdate();
            showpersonnes();
            System.out.println("Person updated");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
    }

    @FXML
    private void attach(ActionEvent event) {
         FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            photo = UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
            image = new Image(file.getAbsoluteFile().toURI().toString(), img.getFitWidth(), img.getFitHeight(), true, true);

            txt_path.setText(photo);
            InputValidation u = new InputValidation();
            String photo1;
            photo1 = "C:\\wamp\\www\\mawhebti\\images\\" + photo;
            System.out.println(photo);
             try {
                 u.CopyImage(photo1, file.toPath().toString());
             } catch (IOException ex) {
                 System.out.println(ex.getMessage());             }
            img.setImage(image);

        }
        
    }
    public ObservableList<personnes> getpersonneslist() throws SQLException{
         ObservableList<personnes> personneslist=FXCollections.observableArrayList();
          cnx = MyConnection.getInstance().getCnx();
          String sql="SELECT * FROM personnes";
          Statement st;
          st=cnx.createStatement();
          rs=st.executeQuery(sql);
          while(rs.next()){
             try {
                 personnes p=new personnes();
                 p.setNom(rs.getString("nom"));
                 p.setPrenom(rs.getString("prenom"));
                 p.setProfil(rs.getString("profil"));
                 p.setPhoto(rs.getString("photo"));
                 p.setLogin(rs.getString("login"));
                 p.setPwd(rs.getString("pwd"));
                 p.setAdress(rs.getString("email"));
                 p.setAdress(rs.getString("adress"));
                 
                 
                 
               personneslist.add(p);
                         
                         } catch (SQLException ex) {
                 System.out.println(ex.getMessage());             }
          
    }
          return personneslist;
    }
    public void showpersonnes() throws SQLException{
        ObservableList<personnes> list=getpersonneslist();
        colfirstname.setCellValueFactory(new PropertyValueFactory<personnes,String>("nom"));
        collastname.setCellValueFactory(new PropertyValueFactory<personnes,String>("prenom"));
         colprofil.setCellValueFactory(new PropertyValueFactory<personnes,String>("profil"));
         colphoto.setCellValueFactory(new PropertyValueFactory<personnes,String>("photo"));
         collogin.setCellValueFactory(new PropertyValueFactory<personnes,String>("login"));
          colpassword.setCellValueFactory(new PropertyValueFactory<personnes,Integer>("pwd"));
          colemail.setCellValueFactory(new PropertyValueFactory<personnes,String>("email"));
          coladress.setCellValueFactory(new PropertyValueFactory<personnes,String>("adress"));







tview.setItems(list);


    }

    @FXML
    private void tableview(MouseEvent event) {
        personnes personne=tview.getSelectionModel().getSelectedItem();
        tfirstname.setText(personne.getPrenom());
        tlastname.setText(personne.getNom());
        tprofil.setText(personne.getProfil());
        tlogin.setText(personne.getLogin());
        tpassword.setText(personne.getPwd());
        temail.setText(personne.getEmail());
        tadress.setText(personne.getAdress());
    }
    
}
