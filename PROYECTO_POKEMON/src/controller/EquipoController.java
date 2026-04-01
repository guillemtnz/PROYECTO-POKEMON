package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class EquipoController {

    @FXML private Button btnPoke1;
    @FXML private Button btnPoke2;
    @FXML private Button btnPoke3;
    @FXML private Button btnPoke4;
    @FXML private Button btnPoke5;
    @FXML private Button btnPoke6;
    @FXML private Button btnVolver;

    @FXML public void handleSlot1(ActionEvent event) { }
    @FXML public void handleSlot2(ActionEvent event) { }
    @FXML public void handleSlot3(ActionEvent event) { }
    @FXML public void handleSlot4(ActionEvent event) { }
    @FXML public void handleSlot5(ActionEvent event) { }
    @FXML public void handleSlot6(ActionEvent event) { }
    
    // Método para volver al menú principal
    @FXML
    public void handleVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}