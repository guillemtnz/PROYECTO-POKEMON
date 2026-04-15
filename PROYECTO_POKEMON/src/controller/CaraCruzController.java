package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.File;

public class CaraCruzController {

    @FXML private Label lblPokedollars;
    @FXML private Label lblResultado;
    @FXML private ImageView imgMoneda;
    @FXML private TextField txtApuesta;
    @FXML private Button btnCara;
    @FXML private Button btnCruz;

    // Opcion elegida por el jugador: "CARA" o "CRUZ"
    private String eleccion = null;

    // Pokedollars del jugador
    // TODO: obtener del entrenador logueado cuando este implementada la sesion
    private int pokedollars = 1000;

    @FXML
    public void initialize() {
        lblPokedollars.setText("Pokedollars: " + pokedollars);
        lblResultado.setText("Elige cara o cruz y escribe tu apuesta");
    }

    @FXML
    public void handleElegirCara(ActionEvent event) {
        eleccion = "CARA";
        // Resaltamos el boton seleccionado
        btnCara.setStyle("-fx-background-color: red ; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        btnCruz.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        lblResultado.setText("Has elegido CARA");
    }

    @FXML
    public void handleElegirCruz(ActionEvent event) {
        eleccion = "CRUZ";
        // Resaltamos el boton seleccionado
        btnCruz.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        btnCara.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        lblResultado.setText("Has elegido CRUZ");
    }

    @FXML
    public void handleLanzar(ActionEvent event) {
        // Validamos que haya elegido cara o cruz
        if (eleccion == null) {
            lblResultado.setText("Primero elige CARA o CRUZ!");
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        // Validamos que haya escrito una apuesta valida
        int apuesta;
        try {
            apuesta = Integer.parseInt(txtApuesta.getText().trim());
        } catch (NumberFormatException e) {
            lblResultado.setText("Escribe una cantidad valida para apostar.");
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        // Validamos que tenga suficientes pokedollars
        if (apuesta <= 0) {
            lblResultado.setText("La apuesta debe ser mayor que 0.");
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        if (apuesta > pokedollars) {
            lblResultado.setText("No tienes suficientes Pokedollars!");
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #ff4444;");
            return;
        }

        // Lanzamos la moneda (50% de probabilidad)
        String resultado = Math.random() < 0.5 ? "CARA" : "CRUZ";

        // Actualizamos la imagen de la moneda
        String rutaImg = "./Media/Img/moneda_" + resultado.toLowerCase() + ".png";
        File archivoImg = new File(rutaImg);
        if (archivoImg.exists()) {
            imgMoneda.setImage(new Image(archivoImg.toURI().toString()));
        }

        // Comprobamos si gana o pierde
        if (resultado.equals(eleccion)) {
            pokedollars += apuesta;
            lblResultado.setText("Ha salido " + resultado + "! Ganaste " + apuesta + " Pokedollars! Total: " + pokedollars);
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #44dd44;");
        } else {
            pokedollars -= apuesta;
            lblResultado.setText("Ha salido " + resultado + "! Perdiste " + apuesta + " Pokedollars. Total: " + pokedollars);
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #ff4444;");
        }

        // Actualizamos el contador de pokedollars
        lblPokedollars.setText("Pokedollars: " + pokedollars);

        // Reseteamos la eleccion para la siguiente ronda
        eleccion = null;
        btnCara.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        btnCruz.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
    }

    @FXML
    public void handleVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Casino.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}