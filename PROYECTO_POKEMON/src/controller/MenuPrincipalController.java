package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MenuPrincipalController {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}