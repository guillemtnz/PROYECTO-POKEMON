package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class CasinoController {


    @FXML
    public void handleCaraCruz(ActionEvent event) {
        navegarA("/view/CaraCruz.fxml", event);
    }

    @FXML
    public void handleRuleta(ActionEvent event) {
        navegarA("/view/Ruleta.fxml", event);
    }

    @FXML
    public void handleAdivinarNumero(ActionEvent event) {
        navegarA("/view/AdivinarNumero.fxml", event);
    }

    @FXML
    public void handleVolver(ActionEvent event) {
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