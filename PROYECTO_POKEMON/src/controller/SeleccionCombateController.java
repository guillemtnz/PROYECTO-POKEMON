package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox; // Importa el tipo de tu contenedor raíz
import javafx.stage.Stage;

public class SeleccionCombateController {

    @FXML private VBox rootPane; // Añade esto (y pon fx:id="rootPane" en el FXML)

    @FXML
    private void handleCombateNormal() {
        navegar("/view/Combate.fxml");
    }

    @FXML
    private void handleLigaPokemon() {
        navegar("/view/CombateLiga.fxml");
    }

    @FXML
    private void handleVolver() {
        navegar("/view/MenuPrincipal.fxml");
    }

    private void navegar(String ruta) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ruta));
            // Esta es la forma que NO falla:
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }
}