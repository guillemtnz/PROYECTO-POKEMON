package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class CombateController {

    @FXML private ImageView imgRival;
    @FXML private ImageView imgPropio;
    @FXML private AnchorPane barraVidaRival;
    @FXML private AnchorPane barraEstaminaRival;
    @FXML private AnchorPane barraVidaPropia;
    @FXML private AnchorPane barraEstaminaPropia;
    @FXML private javafx.scene.control.Label lblNombreRival;
    @FXML private javafx.scene.control.Label lblNombrePropio;
    @FXML private TextArea txtLog;
    @FXML private Button btnAtaque1;
    @FXML private Button btnAtaque2;
    @FXML private Button btnAtaque3;
    @FXML private Button btnAtaque4;
    @FXML private Button btnDescansar;
    @FXML private Button btnCambiar;
    @FXML private AnchorPane panelAtaques;
    @FXML private javafx.scene.control.Label lblMensajeHuir;
    
    private int turno = 1;
    private MediaPlayer mediaPlayer;
    
    @FXML
    public void initialize() {
        txtLog.setText("Turno 1: El combate ha comenzado!\n");
        lblNombrePropio.setText("MI POKEMON");
        lblNombreRival.setText("RIVAL");
        iniciarMusica();
    }

    @FXML
    public void handleLuchar(ActionEvent event) {
        panelAtaques.setVisible(true);
    }

    @FXML
    public void handleVolverAcciones(ActionEvent event) {
        panelAtaques.setVisible(false);
    }

    @FXML
    public void handleAtaque1(ActionEvent event) {
        registrarTurno("Ataque 1");
        panelAtaques.setVisible(false);
    }

    @FXML
    public void handleAtaque2(ActionEvent event) {
        registrarTurno("Ataque 2");
        panelAtaques.setVisible(false);
    }

    @FXML
    public void handleAtaque3(ActionEvent event) {
        registrarTurno("Ataque 3");
        panelAtaques.setVisible(false);
    }

    @FXML
    public void handleAtaque4(ActionEvent event) {
        registrarTurno("Ataque 4");
        panelAtaques.setVisible(false);
    }

    @FXML
    public void handleDescansar(ActionEvent event) {
        registrarTurno("Descanso - recupera estamina");
    }

    @FXML
    public void handleCambiar(ActionEvent event) {
        registrarTurno("Cambio de Pokemon");
    }
    
    @FXML
    public void handleHuir(ActionEvent event) {
        lblMensajeHuir.setText("No puedes huir de un combate contra un entrenador!");
        lblMensajeHuir.setVisible(true);
    }

    @FXML
    public void handleGritoPropio(ActionEvent event) {
        System.out.println("Grito del pokemon propio");
    }

    @FXML
    public void handleGritoRival(ActionEvent event) {
        System.out.println("Grito del pokemon rival");
    }


    private void registrarTurno(String accion) {
        txtLog.appendText("Turno " + turno + ": Entrenador usa " + accion + "\n");
        turno++;
    }
    
    private void iniciarMusica() {
        try {
            File archivo = new File("./Media/Music/Combate.mp3");
            if (!archivo.exists()) return;
            Media media = new Media(archivo.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error al cargar música: " + e.getMessage());
        }
    }
}