/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import static java.nio.file.Files.list;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import pi.esprit.entities.InputValidation;
import pi.esprit.entities.commentaires;
import pi.esprit.entities.loggedmembre;
import pi.esprit.entities.personnes;
import pi.esprit.entities.reacts;
import pi.esprit.entities.videos;
import pi.esprit.services.CommentairesService;
import pi.esprit.services.Reactservices;
import pi.esprit.services.VideoCRUD;
import pi.esprit.utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author bureau
 */
public class VideolistController implements Initializable {

    Connection cnx;
    PreparedStatement pst;
    ResultSet st;

    @FXML
    private MediaView mediaView;
    @FXML
    private ListView<videos> listvideo;
    @FXML
    private Button btnbk;
    private Button btnnext;
    private MediaPlayer mediaplayer;
    private Media media;
    private FileChooser filechooser;
    private FileChooser.ExtensionFilter filter;
    @FXML
    private TextField txt_vid;
    private TextField test;
    String video;
    Image image;
    @FXML
    private Pane PaneVideo;
    @FXML
    private Slider volume;
    @FXML
    private MenuItem btn_open;
    @FXML
    private Menu btn_help;
    @FXML
    private MenuItem btn_close;
    @FXML
    private ImageView btn_play;
    @FXML
    private ImageView btn_pause;
    @FXML
    private ImageView btn_stop;
    @FXML
    private ImageView btn_like;
    @FXML
    private TextField num_like;
    @FXML
    private ImageView btn_dislike;
    @FXML
    private TextField num_dislike;
    @FXML
    private ImageView btn_comment;
    @FXML
    private TextField txt_des;
    @FXML
    private Button like_nb;
    @FXML
    private Button dislike_nb;
    private String pathfile;
    @FXML
    private TextField idvid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        VideoCRUD vc = new VideoCRUD();
        like_nb.setVisible(false);
        dislike_nb.setVisible(false);
        idvid.setVisible(false);
        txt_vid.setVisible(false);
        // ObservableList<videos> list = FXCollections.observableArrayList(vc.displayAll());
        // listvideo.setItems(list);
        ObservableList<videos> list = FXCollections.observableArrayList(vc.displayAll());
        listvideo.setCellFactory((ListView<videos> arg0) -> {
            ListCell<videos> cell = new ListCell<videos>() {
                @Override
                protected void updateItem(videos e, boolean btl) {
                    super.updateItem(e, btl);
                    if (e != null) {

                        TextField id = new TextField(String.valueOf(e.getId_vid()));
                        id.setVisible(true);
                        setId(String.valueOf(e.getId_vid()));
                        listvideo.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                                    videos M = listvideo.getItems().get(listvideo.getSelectionModel().getSelectedIndex());
                                    idvid.setText(String.valueOf(M.getId_vid()));
                                  
                                    String pathh = "C:\\wamp\\www\\mawhebti\\videos\\" + M.getPath_vid();
                                    File file = new File(pathh);
                                    System.out.println(file);

                                    media = new Media("file:///C:/wamp/www/mawhebti/videos/"+M.getPath_vid());
                                    mediaplayer = new MediaPlayer(media);

                                    mediaView.setMediaPlayer(mediaplayer);

                                }

                            }
                        }
                        );
                        if (e.getId_vid()
                                != 0) {
                            setText("Video  : " + e.getNom_vid() + "\n" + "Category " + e.getType()
                                    + "\n" + "Description :  \n " + e.getDesc_vid()+"\n" + "Id video :  \n "+e.getId_vid()
                            );
            pathfile="C:\\wamp\\www\\mawhebti\\videos\\"+e.getPath_vid();
                        }
                    }
                }
            };
               
            return cell;
        }
        );
          
        listvideo.setItems(list);

    }
   
 personnes pp = loggedmembre.getP();
  /*  @FXML

    public void uploa(ActionEvent event) throws MalformedURLException, IOException {
        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            video = UUID.randomUUID().toString().replaceAll("-", "") + ".mp4";
            image = new Image(file.getAbsoluteFile().toURI().toString(), mediaView.getFitWidth(), mediaView.getFitHeight(), true, true);

            txt_vid.setText(video);

            InputValidation u = new InputValidation();
            String video1;
            video1 = "C:\\wamp\\www\\mawhebti\\videos\\" + video;
            System.out.println(video);

          mediaplayer.stop();
            media = new Media(file.toURI().toURL().toExternalForm());
            mediaplayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaplayer);
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Saving video");
            alert1.setHeaderText("Information");
            alert1.setContentText("do you want save this video?");
            Optional<ButtonType> action = alert1.showAndWait();
            if (action.get() == ButtonType.OK) {
                u.CopyImage(video1, file.toPath().toString());
                VideoCRUD rc = new VideoCRUD();

                rc.ajouterVideo();
            }
        }

    }*/

    @FXML
    private void back(ActionEvent event
    ) {
        btnbk.getScene().getWindow().hide();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("video.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Stage mainstage = new Stage();
        Scene scene = new Scene(root);
        mainstage.setScene(scene);
        mainstage.show();
    }

    

    @FXML
    private void playvideo(MouseEvent event) {
        mediaplayer.play();

    }

    @FXML
    private void pausevideo(MouseEvent event) {

        mediaplayer.pause();
    }

    @FXML
    private void stopvideo(MouseEvent event) {
        mediaplayer.seek(mediaplayer.getStopTime());
        mediaplayer.stop();
    }

  /*  @FXML
    private void open(ActionEvent event) throws MalformedURLException {
        filechooser = new FileChooser();
        filter = new FileChooser.ExtensionFilter("choisir un video", "*.mp4");
        filechooser.setSelectedExtensionFilter(filter);
        File file = filechooser.showOpenDialog(null);
        if (file != null) {
            mediaplayer.stop();
            media = new Media(file.toURI().toURL().toExternalForm());
            mediaplayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaplayer);

        }

    }*/

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void help(ActionEvent event) {
    }

    @FXML
    private void like(MouseEvent event) {
        Reactservices rs = new Reactservices();
        videos M = listvideo.getItems().get(listvideo.getSelectionModel().getSelectedIndex());

        reacts r = new reacts(M.getId_vid(), pp.getId_user());
        if (rs.getlikeparid(r) == true) {
            // rs.Supprimerdislike(pp.getId_user(), 2);
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("deleting like");
            alert1.setHeaderText("Information");
            alert1.setContentText("you liked this video before");
            alert1.show();
        } else {
            if (rs.getdislikeparid(r) == true) {

                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("deleting like");
                alert1.setHeaderText("Information");
                alert1.setContentText("do you want delete your dislike ?");
                Optional<ButtonType> action = alert1.showAndWait();
                if (action.get() == ButtonType.OK) {
                    rs.like(r);

                    rs.Supprimerdislike(pp.getId_user(),M.getId_vid());
                    System.out.println("dislike deleted");
                    num_dislike.setText(rs.dislike_number(r));
                         num_like.setText(rs.like_number(r));
                }
            } else {
                rs.like(r);
                System.out.println("like added");

            }

        }
        
    }

    @FXML
    private void dislike(MouseEvent event) {
         Reactservices rs = new Reactservices();
       videos M = listvideo.getItems().get(listvideo.getSelectionModel().getSelectedIndex());

        reacts r = new reacts(M.getId_vid(), pp.getId_user());
        if (rs.getdislikeparid(r) == true) {
            // rs.Supprimerdislike(pp.getId_user(), 2);
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("deleting dislike");
            alert1.setHeaderText("Information");
            alert1.setContentText("vous avez deja fait un dislike");
            alert1.show();
        } else {
            if (rs.getlikeparid(r) == true) {

                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("deleting dislike");
                alert1.setHeaderText("Information");
                alert1.setContentText("do you want delete your like ?");
                Optional<ButtonType> action = alert1.showAndWait();
                if (action.get() == ButtonType.OK) {
                    rs.dislike(r);

                    rs.Supprimerlike(pp.getId_user(),M.getId_vid());
                    System.out.println("like deleted");
                     num_dislike.setText(rs.dislike_number(r));
                         num_like.setText(rs.like_number(r));
                }
            } else {
                rs.dislike(r);
                System.out.println("dislike added");

            }

        }
    }

    @FXML
    private boolean comment(MouseEvent event) {
        videos M = listvideo.getItems().get(listvideo.getSelectionModel().getSelectedIndex());

        commentaires r = new commentaires(txt_des.getText(),M.getId_vid(),pp.getId_user());
        Connection cnx = MyConnection.getInstance().getCnx();
        CommentairesService Sr=new CommentairesService();
       if(CommentairesService.number(txt_des.getText())==true){
           Sr.ajouterCommentaire(r);
         JOptionPane.showMessageDialog(null, "comment added!!!");
       }else{
          JOptionPane.showMessageDialog(null, "Ooops!!Try to be cool"); 
       }
        
         
      //  String requete = "INSERT INTO commentaires(descr,id_vid,id_user)" + " VALUES (?,?,?) ";
      //  try {
      //      java.sql.PreparedStatement pst = cnx.prepareStatement(requete);

        //    pst.setString(1, txt_des.getText());
        //    pst.setInt(2,M.getId_vid() );
        //    pst.setInt(3, pp.getId_user());
        //    pst.executeUpdate();
       //     System.out.println("ajout commentaires reussit");
       //     JOptionPane.showMessageDialog(null, "comment added!!!");

       // } catch (SQLException ex) {
       //     System.err.println(ex.getMessage());
      //  }
      //  return false;
        return true;
    }

    @FXML
    private void like_number(ActionEvent event) {
         Reactservices rs = new Reactservices();
      videos M = listvideo.getItems().get(listvideo.getSelectionModel().getSelectedIndex());

        reacts r = new reacts(M.getId_vid(), pp.getId_user());
        rs.like_number(r);
        num_like.setText(rs.like_number(r));
    }

    @FXML
    private void dislikenumber(ActionEvent event) {
        Reactservices rs = new Reactservices();
        videos M = listvideo.getItems().get(listvideo.getSelectionModel().getSelectedIndex());

        reacts r = new reacts(M.getId_vid(), pp.getId_user());
        rs.dislike_number(r);
        num_dislike.setText(rs.dislike_number(r));
    }

}
