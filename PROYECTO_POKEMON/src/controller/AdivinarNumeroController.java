package controller;

import dao.EntrenadorDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Entrenador;

public class AdivinarNumeroController {

    @FXML private TextField txtNumero;
    @FXML private Button    btnIniciar;
    @FXML private Button    btnAdivinar;
    @FXML private Button    btnOtraVez;
    @FXML private Label     lblPokedollars;
    @FXML private Label     lblIntentos;
    @FXML private Label     lblResultado;
    @FXML private Label     lblMensaje;

    private int     numeroSecreto;
    private int     intentosUsados;
    private static final int MAX_INTENTOS = 5;

    private static final int[] PREMIOS = {1000, 750, 500, 250, 0};

    private final EntrenadorDAO entrenadorDAO = new EntrenadorDAO();

    // INICIALIZAMOS

    @FXML
    public void initialize() {
        refrescarPokedollars();
    }

    // INICIAR 

    @FXML
    public void handleIniciar(ActionEvent event) {
        numeroSecreto  = (int)(Math.random() * 20) + 1;
        intentosUsados = 0;

        lblIntentos.setText(String.valueOf(MAX_INTENTOS));
        lblResultado.setText("");
        lblMensaje.setText("¡Partida iniciada! Introduce un número entre 1 y 20.");
        txtNumero.clear();

        txtNumero.setDisable(false);
        btnAdivinar.setDisable(false);
        btnIniciar.setDisable(true);
        btnOtraVez.setVisible(false);
    }

    // ADIVINAR

    @FXML
    public void handleAdivinar(ActionEvent event) {
        int intento;
        try {
            intento = Integer.parseInt(txtNumero.getText().trim());
        } catch (NumberFormatException e) {
            lblMensaje.setText("Introduce un número válido.");
            return;
        }

        if (intento < 1 || intento > 20) {
            lblMensaje.setText("El número debe estar entre 1 y 20.");
            return;
        }

        intentosUsados++;
        txtNumero.clear();
        lblIntentos.setText(String.valueOf(MAX_INTENTOS - intentosUsados));

        if (intento == numeroSecreto) {
            // Acierto
            int premio = PREMIOS[intentosUsados - 1];
            Entrenador ent = Entrenador.entrenadorLogueado;
            ent.setPokedollars(ent.getPokedollars() + premio);
            entrenadorDAO.actualizarPokedollars(ent.getIdEntrenador(), ent.getPokedollars());
            refrescarPokedollars();

            if (premio > 0) {
                lblResultado.setTextFill(Color.web("#4CAF50"));
                lblResultado.setText("¡CORRECTO! Era el " + numeroSecreto
                        + ". Acertaste en el intento " + intentosUsados
                        + ". Ganas " + premio + " P$.");
            } else {
                lblResultado.setTextFill(Color.web("#FFB300"));
                lblResultado.setText("Correcto, era el " + numeroSecreto
                        + ", pero lo acertaste en el 5º intento. No ganas nada.");
            }
            lblMensaje.setText("");
            terminarPartida();

        } else if (intentosUsados >= MAX_INTENTOS) {
            // Sin intentos
 
            lblResultado.setTextFill(Color.web("#E53935"));
            lblResultado.setText("Sin más intentos. El número era el " + numeroSecreto + ". No pierdes nada.");
            lblMensaje.setText("");
            terminarPartida();

        } else {
            lblMensaje.setText("Te quedan " + (MAX_INTENTOS - intentosUsados) + " intentos.");
        }
    }

    // OTRA VEZ
    @FXML
    public void handleOtraVez(ActionEvent event) {
        lblResultado.setText("");
        lblIntentos.setText(String.valueOf(MAX_INTENTOS));
        lblMensaje.setText("Pulsa 'Iniciar partida' para comenzar.");
        txtNumero.clear();
        txtNumero.setDisable(true);
        btnAdivinar.setDisable(true);
        btnIniciar.setDisable(false);
        btnOtraVez.setVisible(false);
    }

    // VOLVER

    @FXML
    public void handleVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Casino.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // UTILIDADES

    private void terminarPartida() {
        txtNumero.setDisable(true);
        btnAdivinar.setDisable(true);
        btnIniciar.setDisable(false);
        btnOtraVez.setVisible(true);
    }

    private void refrescarPokedollars() {
        if (Entrenador.entrenadorLogueado != null)
            lblPokedollars.setText(Entrenador.entrenadorLogueado.getPokedollars() + " P$");
    }
}