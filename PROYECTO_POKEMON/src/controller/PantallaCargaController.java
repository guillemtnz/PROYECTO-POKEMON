package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import javafx.animation.RotateTransition;

public class PantallaCargaController {
	
	 /*
	 * Controlador de la pantalla de carga.
	 * Se encarga de gestionar la animación de la barra de progreso
	 * y la Pokéball giratoria antes de navegar al menú principal.
	 * Está vinculado a la vista PantallaCarga.fxml
	 */

    @FXML private ProgressBar progressBar;
    @FXML private Button btnJugar;
    @FXML private ImageView pokeball;
    
    /*
    * Método que se ejecuta al pulsar el botón "JUGAR".
    * Oculta el botón y muestra la barra de carga junto a la Pokéball
    * Cuando la barra llega al 100%, navega automáticamente al menú principal.
    */
    @FXML
    public void handleJugar(ActionEvent event) {
        // Ocultar botón y mostrar barra
        btnJugar.setVisible(false);
        progressBar.setVisible(true);
        pokeball.setVisible(true);

        // Animación de carga
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.millis(30), e -> {
            double progress = progressBar.getProgress();
            if (progress < 1.0) {
                progressBar.setProgress(progress + 0.01);
            }
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(100);
        timeline.play();
        
     // Rotar la Pokéball
        RotateTransition rotate = new RotateTransition(Duration.millis(500), pokeball);
        rotate.setByAngle(360);
        rotate.setCycleCount(Timeline.INDEFINITE);
        rotate.play();

        // Al terminar navegar al Menú Principal
        timeline.setOnFinished(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
                Stage stage = (Stage) progressBar.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}