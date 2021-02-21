/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.esprit.design.homelogin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pi.esprit.entities.InputValidation;
import pi.esprit.entities.videos;
import pi.esprit.services.VideoCRUD;

/**
 * FXML Controller class
 *
 * @author bureau
 */
public class VideoController implements Initializable {

    private MediaPlayer mediaplayer;
    private Media media;
    private FileChooser filechooser;
    private FileChooser.ExtensionFilter filter;
    Connection cnx;
    ResultSet rs = null;
    PreparedStatement pst;
    @FXML
    private ImageView btn_play;
    @FXML
    private ImageView btn_pause;
    @FXML
    private ImageView btn_stop;
    @FXML
    private Slider volume;
    @FXML
    private MenuItem btn_open;
    @FXML
    private MenuItem btn_uplo;
    @FXML
    private Button btn_list;
    @FXML
    private Button btn_back;
    @FXML
    private MediaView mediaView;
    String video;
    Image image;
    @FXML
    private TextField txt_nomvid;
    @FXML
    private TextField txtpath;
    @FXML
    private TextField txtname;
    private MenuItem btn_about;
    @FXML
    private TextField txt_desc;
    @FXML
    private TextField txt_path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String path = new File("src/vid/avion.mp4").getAbsolutePath();
        media = new Media(new File(path).toURI().toString());
        mediaplayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaplayer);
        mediaplayer.setAutoPlay(true);
        volume.setValue(mediaplayer.getVolume() * 100);
        volume.valueProperty().addListener((Observable observable) -> {
            mediaplayer.setVolume(volume.getValue() / 100);
        });
        txtpath.setVisible(false);
        txt_path.setVisible(false);
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
    
    @FXML
    private void open(ActionEvent event) {
        filechooser = new FileChooser();
        filter = new FileChooser.ExtensionFilter("choisir un video", "*.mp4");
        filechooser.setSelectedExtensionFilter(filter);
        File file = filechooser.showOpenDialog(null);
        if (file != null) {
            mediaplayer.stop();
            try {
                media = new Media(file.toURI().toURL().toExternalForm());
            } catch (MalformedURLException ex) {
                System.out.println(ex.getMessage());
            }
            mediaplayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaplayer);
        }
    }
    
    @FXML
    private void uploa(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        final Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file);
        if (file != null) {
            String p = file.toURI().toString();
            System.out.println(p);
            video = UUID.randomUUID().toString().replaceAll("-", "") + ".mp4";
            image = new Image(file.getAbsoluteFile().toURI().toString(), mediaView.getFitWidth(), mediaView.getFitHeight(), true, true);

            // txt_vid.setText(video);
            txtpath.setText(video);
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
                
                videos vv = new videos(0, txt_nomvid.getText(), txtpath.getText(), txt_desc.getText(), 4, txtname.getText());
                rc.ajouterVideo2(vv);
            }
        }
        
    }
    
    @FXML
    private void list(ActionEvent event) {
        btn_list.getScene().getWindow().hide();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("videolist.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());            
        }
        Stage mainstage = new Stage();
        Scene scene = new Scene(root);
        mainstage.setScene(scene);        
        mainstage.show();
    }
    
    @FXML
    private void back(ActionEvent event) {
        btn_back.getScene().getWindow().hide();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("Menuprincipale.fxml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());            
        }
        Stage mainstage = new Stage();
        Scene scene = new Scene(root);
        mainstage.setScene(scene);        
        mainstage.show();
    }
    
}
