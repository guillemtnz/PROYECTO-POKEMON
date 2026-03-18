package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class CapturaController {

    @FXML private Label lblNombreSalvaje;
    @FXML private Label lblMensaje;
    @FXML private Label lblPokedollars;
    @FXML private ImageView imgPokemonSalvaje;
    @FXML private AnchorPane barraVida;

    @FXML
    public void initialize() {
        lblMensaje.setText("¡Un TORCHIC salvaje apareció!");
    }

    @FXML
    public void handleLanzarPokeball(ActionEvent event) {
        double probabilidad = Math.random();
        if (probabilidad > 0.5) {
            lblMensaje.setText("¡Pokémon capturado con éxito! 🎉");
            lblMensaje.setStyle("-fx-background-color: rgba(0,100,0,0.8); -fx-padding: 5; -fx-font-size: 11px; -fx-font-weight: bold;");
        } else {
            lblMensaje.setText("¡El Pokémon escapó! Inténtalo de nuevo.");
            lblMensaje.setStyle("-fx-background-color: rgba(100,0,0,0.8); -fx-padding: 5; -fx-font-size: 11px; -fx-font-weight: bold;");
        }
    }

    @FXML
    public void handleHuir(ActionEvent event) {
        navegarA("/view/MenuPrincipal.fxml", event);
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
}