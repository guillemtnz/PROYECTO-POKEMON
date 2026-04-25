package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class CasinoController {
	
	 private MediaPlayer mediaPlayer;

	    @FXML
	    public void initialize() {
	        iniciarMusica();
	    }
	    
    private void navegarA(String ruta, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ruta));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void iniciarMusica() {
        try {
            File archivo = new File("./Media/Music/Casino.mp3");
            if (!archivo.exists()) return;
            Media media = new Media(archivo.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error al cargar música: " + e.getMessage());
        }
    }

    private void pararMusica() {
        if (mediaPlayer != null) mediaPlayer.stop();
    }
    
    @FXML
    public void handleCaraCruz(ActionEvent event) {
        pararMusica();
        navegarA("/view/CaraCruz.fxml", event);
    }
    @FXML
    public void handleRuleta(ActionEvent event) {
        pararMusica();
        navegarA("/view/Ruleta.fxml", event);
    }
    @FXML
    public void handleAdivinarNumero(ActionEvent event) {
        pararMusica();
        navegarA("/view/AdivinarNumero.fxml", event);
    }
    @FXML
    public void handleVolver(ActionEvent event) {
        pararMusica();
        navegarA("/view/MenuPrincipal.fxml", event);
    }
}