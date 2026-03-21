package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;



import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MenuPrincipalController {
	
	private boolean musica = false;
	private MediaPlayer mediaPlayer;
	
	@FXML private ImageView imgMusica;

	@FXML
	public void initialize() {
		musica();
	}
	
	@FXML
    void activarDesactivarSonido(MouseEvent event) {
		musica();
    }

    @FXML private Button btnCapturar;
    @FXML private Button btnEquipo;
    @FXML private Button btnCrianza;
    @FXML private Button btnCasino;
    @FXML private Button btnCerrarSesion;

    @FXML
    public void handleCapturar(ActionEvent event) {
        navegarA("/view/Captura.fxml", event);
    }

    @FXML
    public void handleEquipo(ActionEvent event) {
        navegarA("/view/Equipo.fxml", event);
    }

    @FXML
    public void handleCrianza(ActionEvent event) {
        mostrarAviso("Crianza - Próximamente");
    }

    @FXML
    public void handleCasino(ActionEvent event) {
        mostrarAviso("Casino - Próximamente");
    }

    @FXML
    public void handleCerrarSesion(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarAviso(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Pokémon Simulator");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    private void navegarA(String ruta, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ruta));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            mediaPlayer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void musica() {
    	
    	if(!this.musica) {
    		String musica = "./Media/Music/littleroot_town.mp3";
        	Media sound = new Media(new File(musica).toURI().toString());
        	
        	mediaPlayer = new MediaPlayer(sound);
        	mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        	mediaPlayer.play();
        	imgMusica.setImage(new Image(new File("./Media/Img/unmuted.png").toURI().toString()));
        	
        	this.musica =true;
    	}else {
    		mediaPlayer.stop();
    		this.musica =false;
    		imgMusica.setImage(new Image(new File("./Media/Img/muted.png").toURI().toString()));
    	}
    	
    }
}