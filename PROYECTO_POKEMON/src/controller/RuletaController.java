package controller;

import dao.EntrenadorDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Entrenador;

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

    private String tipoApuesta  = null;
    private String colorElegido = null;

    private final EntrenadorDAO entrenadorDAO = new EntrenadorDAO();

    private String estiloBotonNormal       = "-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6; -fx-font-size: 10px;";
    private String estiloBotonSeleccionado = "-fx-background-color: #e94560; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6; -fx-font-size: 10px;";

    @FXML
    public void initialize() {
        refrescarPokedollars();
        lblResultado.setText("Elige tipo de apuesta y cantidad");
    }

    @FXML
    public void handleTipoNumero(ActionEvent event) {
        tipoApuesta  = "NUMERO";
        colorElegido = null;
        lblEtiquetaNumero.setVisible(true);
        txtNumero.setVisible(true);
        lblEtiquetaColor.setVisible(false);
        btnRojo.setVisible(false);
        btnNegro.setVisible(false);
        btnTipoNumero.setStyle(estiloBotonSeleccionado);
        btnTipoColor.setStyle(estiloBotonNormal);
        btnTipoAmbos.setStyle(estiloBotonNormal);
        lblResultado.setText("Introduce el numero al que quieres apostar (1-37)");
        lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: white;");
    }

    @FXML
    public void handleTipoColor(ActionEvent event) {
        tipoApuesta  = "COLOR";
        colorElegido = null;
        lblEtiquetaNumero.setVisible(false);
        txtNumero.setVisible(false);
        lblEtiquetaColor.setVisible(true);
        btnRojo.setVisible(true);
        btnNegro.setVisible(true);
        btnTipoColor.setStyle(estiloBotonSeleccionado);
        btnTipoNumero.setStyle(estiloBotonNormal);
        btnTipoAmbos.setStyle(estiloBotonNormal);
        lblResultado.setText("Elige el color al que quieres apostar");
        lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: white;");
    }

    @FXML
    public void handleTipoAmbos(ActionEvent event) {
        tipoApuesta  = "AMBOS";
        colorElegido = null;
        lblEtiquetaNumero.setVisible(true);
        txtNumero.setVisible(true);
        lblEtiquetaColor.setVisible(true);
        btnRojo.setVisible(true);
        btnNegro.setVisible(true);
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
        if (tipoApuesta == null) {
            lblResultado.setText("Primero elige el tipo de apuesta!");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        int apuesta;
        try {
            apuesta = Integer.parseInt(txtApuesta.getText().trim());
        } catch (NumberFormatException e) {
            lblResultado.setText("Escribe una cantidad valida para apostar.");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        Entrenador ent = Entrenador.entrenadorLogueado;

        if (apuesta <= 0) {
            lblResultado.setText("La apuesta debe ser mayor que 0.");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        if (apuesta > ent.getPokedollars()) {
            lblResultado.setText("No tienes suficientes Pokedollars!");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ff4444;");
            return;
        }

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

        if ((tipoApuesta.equals("COLOR") || tipoApuesta.equals("AMBOS")) && colorElegido == null) {
            lblResultado.setText("Elige un color antes de girar.");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        // Generar resultado
        int    numeroSalido = (int)(Math.random() * 37) + 1;
        String colorSalido  = Math.random() < 0.5 ? "ROJO" : "NEGRO";

        lblNumeroSalido.setText(numeroSalido + "  " + colorSalido);
        lblNumeroSalido.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: "
                + (colorSalido.equals("ROJO") ? "#cc0000" : "white") + ";");
        lblColorSalido.setText("");
        
        // Evaluar resultado
        boolean aciertoNumero = (tipoApuesta.equals("NUMERO") || tipoApuesta.equals("AMBOS")) && numeroSalido == numeroElegido;
        boolean aciertoColor  = (tipoApuesta.equals("COLOR")  || tipoApuesta.equals("AMBOS")) && colorSalido.equals(colorElegido);

        if (aciertoNumero && aciertoColor) {
            int ganancia = apuesta * 12;
            ent.setPokedollars(ent.getPokedollars() + ganancia);
            lblResultado.setText("Salio " + numeroSalido + " " + colorSalido + ". Acertaste numero y color! Ganaste " + ganancia + " Pokedollars!");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #44dd44;");
        } else if (aciertoNumero) {
            int ganancia = apuesta * 10;
            ent.setPokedollars(ent.getPokedollars() + ganancia);
            lblResultado.setText("Salio " + numeroSalido + " " + colorSalido + ". Acertaste el numero! Ganaste " + ganancia + " Pokedollars!");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #44dd44;");
        } else if (aciertoColor) {
            int ganancia = apuesta * 2;
            ent.setPokedollars(ent.getPokedollars() + ganancia);
            lblResultado.setText("Salio " + numeroSalido + " " + colorSalido + ". Acertaste el color! Ganaste " + ganancia + " Pokedollars!");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #44dd44;");
        } else {
            ent.setPokedollars(ent.getPokedollars() - apuesta);
            lblResultado.setText("Salio " + numeroSalido + " " + colorSalido + ". No acertaste. Perdiste " + apuesta + " Pokedollars.");
            lblResultado.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #ff4444;");
        }

        entrenadorDAO.actualizarPokedollars(ent.getIdEntrenador(), ent.getPokedollars());
        refrescarPokedollars();

        // Resetear selecciones
        tipoApuesta  = null;
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
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void refrescarPokedollars() {
        if (Entrenador.entrenadorLogueado != null)
            lblPokedollars.setText("Pokedollars: " + Entrenador.entrenadorLogueado.getPokedollars());
    }
}