package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class RuletaController {

    @FXML private Label lblPokedollars;
    @FXML private Label lblResultado;
    @FXML private Label lblNumeroSalido;
    @FXML private Label lblColorSalido;
    @FXML private TextField txtApuesta;
    @FXML private TextField txtNumero;
    @FXML private Button btnTipoNumero;
    @FXML private Button btnTipoColor;
    @FXML private Button btnTipoAmbos;
    @FXML private Button btnRojo;
    @FXML private Button btnNegro;
    @FXML private Label lblEtiquetaNumero;
    @FXML private Label lblEtiquetaColor;

    // Tipo de apuesta: "NUMERO", "COLOR", "AMBOS"
    private String tipoApuesta = null;

    // Color elegido por el jugador
    private String colorElegido = null;

    // TODO: obtener del entrenador logueado cuando este implementada la sesion
    private int pokedollars = 1000;

    private String estiloBotonNormal = "-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6; -fx-font-size: 10px;";
    private String estiloBotonSeleccionado = "-fx-background-color: #e94560; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6; -fx-font-size: 10px;";

    @FXML
    public void initialize() {
        lblPokedollars.setText("Pokedollars: " + pokedollars);
        lblResultado.setText("Elige tipo de apuesta y cantidad");
    }

    @FXML
    public void handleTipoNumero(ActionEvent event) {
        tipoApuesta = "NUMERO";
        colorElegido = null;
        // Mostramos solo el campo de numero
        lblEtiquetaNumero.setVisible(true);
        txtNumero.setVisible(true);
        lblEtiquetaColor.setVisible(false);
        btnRojo.setVisible(false);
        btnNegro.setVisible(false);
        // Resaltamos boton seleccionado
        btnTipoNumero.setStyle(estiloBotonSeleccionado);
        btnTipoColor.setStyle(estiloBotonNormal);
        btnTipoAmbos.setStyle(estiloBotonNormal);
        lblResultado.setText("Introduce el numero al que quieres apostar (1-37)");
        lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: white;");
    }

    @FXML
    public void handleTipoColor(ActionEvent event) {
        tipoApuesta = "COLOR";
        colorElegido = null;
        // Mostramos solo los botones de color
        lblEtiquetaNumero.setVisible(false);
        txtNumero.setVisible(false);
        lblEtiquetaColor.setVisible(true);
        btnRojo.setVisible(true);
        btnNegro.setVisible(true);
        // Resaltamos boton seleccionado
        btnTipoColor.setStyle(estiloBotonSeleccionado);
        btnTipoNumero.setStyle(estiloBotonNormal);
        btnTipoAmbos.setStyle(estiloBotonNormal);
        lblResultado.setText("Elige el color al que quieres apostar");
        lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: white;");
    }

    @FXML
    public void handleTipoAmbos(ActionEvent event) {
        tipoApuesta = "AMBOS";
        colorElegido = null;
        // Mostramos tanto numero como color
        lblEtiquetaNumero.setVisible(true);
        txtNumero.setVisible(true);
        lblEtiquetaColor.setVisible(true);
        btnRojo.setVisible(true);
        btnNegro.setVisible(true);
        // Resaltamos boton seleccionado
        btnTipoAmbos.setStyle(estiloBotonSeleccionado);
        btnTipoNumero.setStyle(estiloBotonNormal);
        btnTipoColor.setStyle(estiloBotonNormal);
        lblResultado.setText("Introduce el numero y elige el color");
        lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: white;");
    }

    @FXML
    public void handleElegirRojo(ActionEvent event) {
        colorElegido = "ROJO";
        btnRojo.setStyle("-fx-background-color: #cc0000; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        btnNegro.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
    }

    @FXML
    public void handleElegirNegro(ActionEvent event) {
        colorElegido = "NEGRO";
        btnNegro.setStyle("-fx-background-color: #111; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        btnRojo.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
    }

    @FXML
    public void handleGirar(ActionEvent event) {
        // Validamos que haya elegido tipo de apuesta
        if (tipoApuesta == null) {
            lblResultado.setText("Primero elige el tipo de apuesta!");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        // Validamos apuesta
        int apuesta;
        try {
            apuesta = Integer.parseInt(txtApuesta.getText().trim());
        } catch (NumberFormatException e) {
            lblResultado.setText("Escribe una cantidad valida para apostar.");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        if (apuesta <= 0) {
            lblResultado.setText("La apuesta debe ser mayor que 0.");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        if (apuesta > pokedollars) {
            lblResultado.setText("No tienes suficientes Pokedollars!");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ff4444;");
            return;
        }

        // Validamos numero si hace falta
        int numeroElegido = -1;
        if (tipoApuesta.equals("NUMERO") || tipoApuesta.equals("AMBOS")) {
            try {
                numeroElegido = Integer.parseInt(txtNumero.getText().trim());
                if (numeroElegido < 1 || numeroElegido > 37) {
                    lblResultado.setText("El numero debe estar entre 1 y 37.");
                    lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
                    return;
                }
            } catch (NumberFormatException e) {
                lblResultado.setText("Escribe un numero valido entre 1 y 37.");
                lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
                return;
            }
        }

        // Validamos color si hace falta
        if ((tipoApuesta.equals("COLOR") || tipoApuesta.equals("AMBOS")) && colorElegido == null) {
            lblResultado.setText("Elige un color antes de girar.");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        // Generamos resultado aleatorio
        int numeroSalido = (int)(Math.random() * 37) + 1;
        String colorSalido = Math.random() < 0.5 ? "ROJO" : "NEGRO";

        // Mostramos resultado
        lblNumeroSalido.setText(String.valueOf(numeroSalido));
        lblColorSalido.setText(colorSalido);
        if (colorSalido.equals("ROJO")) {
            lblColorSalido.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #cc0000;");
        } else {
            lblColorSalido.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white;");
        }

        // Calculamos si gana o pierde
        boolean aciertoNumero = (tipoApuesta.equals("NUMERO") || tipoApuesta.equals("AMBOS")) && numeroSalido == numeroElegido;
        boolean aciertoColor = (tipoApuesta.equals("COLOR") || tipoApuesta.equals("AMBOS")) && colorSalido.equals(colorElegido);

        if (aciertoNumero && aciertoColor) {
            // Acierta numero y color: gana x10 + x2
            int ganancia = apuesta * 12;
            pokedollars += ganancia;
            lblResultado.setText("Salio " + numeroSalido + " " + colorSalido + ". Acertaste numero y color! Ganaste " + ganancia + " Pokedollars! Total: " + pokedollars);
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #44dd44;");
        } else if (aciertoNumero) {
            int ganancia = apuesta * 10;
            pokedollars += ganancia;
            lblResultado.setText("Salio " + numeroSalido + " " + colorSalido + ". Acertaste el numero! Ganaste " + ganancia + " Pokedollars! Total: " + pokedollars);
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #44dd44;");
        } else if (aciertoColor) {
            int ganancia = apuesta * 2;
            pokedollars += ganancia;
            lblResultado.setText("Salio " + numeroSalido + " " + colorSalido + ". Acertaste el color! Ganaste " + ganancia + " Pokedollars! Total: " + pokedollars);
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #44dd44;");
        } else {
            pokedollars -= apuesta;
            lblResultado.setText("Salio " + numeroSalido + " " + colorSalido + ". No acertaste. Perdiste " + apuesta + " Pokedollars. Total: " + pokedollars);
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ff4444;");
        }

        lblPokedollars.setText("Pokedollars: " + pokedollars);

        // Reseteamos selecciones para la siguiente ronda
        tipoApuesta = null;
        colorElegido = null;
        btnTipoNumero.setStyle(estiloBotonNormal);
        btnTipoColor.setStyle(estiloBotonNormal);
        btnTipoAmbos.setStyle(estiloBotonNormal);
        btnRojo.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        btnNegro.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        lblEtiquetaNumero.setVisible(false);
        txtNumero.setVisible(false);
        lblEtiquetaColor.setVisible(false);
        btnRojo.setVisible(false);
        btnNegro.setVisible(false);
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