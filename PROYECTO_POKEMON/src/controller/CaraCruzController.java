package controller;

import dao.EntrenadorDAO;
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
import model.Entrenador;

import java.io.File;

public class CaraCruzController {

    @FXML private Label     lblPokedollars;
    @FXML private Label     lblResultado;
    @FXML private TextField txtApuesta;
    @FXML private Button    btnCara;
    @FXML private Button    btnCruz;
    @FXML private ImageView imgMoneda;

    private String eleccion = null;
    private final EntrenadorDAO entrenadorDAO = new EntrenadorDAO();

    @FXML
    public void initialize() {
        refrescarPokedollars();
        lblResultado.setText("Elige cara o cruz y escribe tu apuesta");
    }

    @FXML
    public void handleElegirCara(ActionEvent event) {
        eleccion = "CARA";
        btnCara.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        btnCruz.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        lblResultado.setText("Has elegido CARA");
    }

    @FXML
    public void handleElegirCruz(ActionEvent event) {
        eleccion = "CRUZ";
        btnCruz.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        btnCara.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 6;");
        lblResultado.setText("Has elegido CRUZ");
    }

    @FXML
    public void handleLanzar(ActionEvent event) {
        if (eleccion == null) {
            lblResultado.setText("Primero elige CARA o CRUZ!");
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        int apuesta;
        try {
            apuesta = Integer.parseInt(txtApuesta.getText().trim());
        } catch (NumberFormatException e) {
            lblResultado.setText("Escribe una cantidad valida para apostar.");
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        Entrenador ent = Entrenador.entrenadorLogueado;

        if (apuesta <= 0) {
            lblResultado.setText("La apuesta debe ser mayor que 0.");
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #ffaa00;");
            return;
        }

        if (apuesta > ent.getPokedollars()) {
            lblResultado.setText("No tienes suficientes Pokedollars!");
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #ff4444;");
            return;
        }

        // Lanzar moneda
        String resultado = Math.random() < 0.5 ? "CARA" : "CRUZ";

        // Actualizar imagen de la moneda
        File archivoImg = new File("./Media/Img/moneda_" + resultado.toLowerCase() + ".png");
        if (archivoImg.exists()) {
            imgMoneda.setImage(new Image(archivoImg.toURI().toString()));
        }

        // Resolver apuesta
        if (resultado.equals(eleccion)) {
            ent.setPokedollars(ent.getPokedollars() + apuesta);
            lblResultado.setText("Ha salido " + resultado + "! Ganaste " + apuesta + " Pokedollars!");
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #44dd44;");
        } else {
            ent.setPokedollars(ent.getPokedollars() - apuesta);
            lblResultado.setText("Ha salido " + resultado + "! Perdiste " + apuesta + " Pokedollars.");
            lblResultado.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #ff4444;");
        }

        entrenadorDAO.actualizarPokedollars(ent.getIdEntrenador(), ent.getPokedollars());
        refrescarPokedollars();

        // Resetear elección
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
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void refrescarPokedollars() {
        if (Entrenador.entrenadorLogueado != null)
            lblPokedollars.setText("Pokedollars: " + Entrenador.entrenadorLogueado.getPokedollars());
    }
}