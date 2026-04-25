package controller;

import dao.PokemonDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Entrenador;
import model.Pokemon;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MoverPokemonController {

    // Slots equipo fijos
    @FXML private AnchorPane slotE1, slotE2, slotE3, slotE4, slotE5, slotE6;
    @FXML private ImageView  imgE1, imgE2, imgE3, imgE4, imgE5, imgE6;
    @FXML private Label      lblE1, lblE2, lblE3, lblE4, lblE5, lblE6;

    // Caja dinámica
    @FXML private FlowPane  flowCaja;
    @FXML private ScrollPane scrollCaja;

    private ArrayList<Pokemon> equipo = new ArrayList<>();
    private ArrayList<Pokemon> caja   = new ArrayList<>();
    private Pokemon pokemonEquipoSeleccionado = null;
    private MediaPlayer mediaPlayer;
    private PokemonDAO pokemonDAO = new PokemonDAO();

    @FXML
    public void initialize() {
        cargarEquipo();
        cargarCaja();
        setCajaActiva(false);
        iniciarMusica();
    }

    // CARGAR EQUIPO

    private void cargarEquipo() {
        equipo = pokemonDAO.obtenerEquipo(Entrenador.entrenadorLogueado.getIdEntrenador());

        ImageView[]  imgs  = {imgE1, imgE2, imgE3, imgE4, imgE5, imgE6};
        Label[]      lbls  = {lblE1, lblE2, lblE3, lblE4, lblE5, lblE6};
        AnchorPane[] slots = {slotE1, slotE2, slotE3, slotE4, slotE5, slotE6};

        for (int i = 0; i < 6; i++) {
            imgs[i].setImage(null);
            lbls[i].setText("[ Vacío ]");
            slots[i].setStyle(estiloEquipo(false));
        }
        for (int i = 0; i < equipo.size() && i < 6; i++) {
            cargarSprite(imgs[i], equipo.get(i).getNumPokedex());
            lbls[i].setText(equipo.get(i).getMote());
        }
    }

    // CARGAR CAJA DINÁMICA

    private void cargarCaja() {
        caja = pokemonDAO.obtenerCaja(Entrenador.entrenadorLogueado.getIdEntrenador());
        flowCaja.getChildren().clear();

        for (int i = 0; i < caja.size(); i++) {
            Pokemon p = caja.get(i);
            AnchorPane slot = crearSlotCaja(p, i);
            flowCaja.getChildren().add(slot);
        }

        if (caja.isEmpty()) {
            Label lblVacia = new Label("La caja está vacía");
            lblVacia.setStyle("-fx-text-fill: #aaaaaa; -fx-font-size: 10px;");
            flowCaja.getChildren().add(lblVacia);
        }
    }

    // Crea un slot de caja dinámicamente
    private AnchorPane crearSlotCaja(Pokemon p, int indice) {
        AnchorPane slot = new AnchorPane();
        slot.setPrefHeight(90);
        slot.setPrefWidth(90);
        slot.setStyle(estiloCaja(false));

        ImageView img = new ImageView();
        img.setFitHeight(60);
        img.setFitWidth(60);
        img.setLayoutX(15);
        img.setLayoutY(3);
        img.setPreserveRatio(true);
        cargarSprite(img, p.getNumPokedex());

        Label lbl = new Label(p.getMote());
        lbl.setLayoutX(0);
        lbl.setLayoutY(65);
        lbl.setPrefWidth(90);
        lbl.setTextFill(javafx.scene.paint.Color.WHITE);
        lbl.setStyle("-fx-font-size: 8px; -fx-font-weight: bold; -fx-alignment: center;");

        slot.getChildren().addAll(img, lbl);

        // Click handler
        slot.setOnMouseClicked(e -> seleccionarCaja(indice, slot));

        return slot;
    }

    // HANDLERS EQUIPO 

    @FXML public void handleSlotEquipo1(MouseEvent e) { seleccionarEquipo(0); }
    @FXML public void handleSlotEquipo2(MouseEvent e) { seleccionarEquipo(1); }
    @FXML public void handleSlotEquipo3(MouseEvent e) { seleccionarEquipo(2); }
    @FXML public void handleSlotEquipo4(MouseEvent e) { seleccionarEquipo(3); }
    @FXML public void handleSlotEquipo5(MouseEvent e) { seleccionarEquipo(4); }
    @FXML public void handleSlotEquipo6(MouseEvent e) { seleccionarEquipo(5); }

    private void seleccionarEquipo(int indice) {
        if (indice >= equipo.size()) return;

        AnchorPane[] slots = {slotE1, slotE2, slotE3, slotE4, slotE5, slotE6};
        for (AnchorPane s : slots) s.setStyle(estiloEquipo(false));

        pokemonEquipoSeleccionado = equipo.get(indice);
        slots[indice].setStyle(estiloEquipo(true));
        setCajaActiva(true);
    }

    // HANDLER CAJA DINÁMICO

    private void seleccionarCaja(int indice, AnchorPane slotClickado) {
        if (pokemonEquipoSeleccionado == null) {
            return;
        }
        if (indice >= caja.size()) return;

        Pokemon pokemonCajaSeleccionado = caja.get(indice);

        pokemonDAO.cambiarUbicacion(pokemonEquipoSeleccionado.getIdPokemon(), "CAJA");
        pokemonDAO.cambiarUbicacion(pokemonCajaSeleccionado.getIdPokemon(), "EQUIPO");

        pokemonEquipoSeleccionado = null;
        setCajaActiva(false);
        cargarEquipo();
        cargarCaja();
    }

    // VOLVER

    @FXML
    public void handleVolver(ActionEvent event) {
        try {
        	pararMusica();
            Parent root = FXMLLoader.load(getClass().getResource("/view/Equipo.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // UTILIDADES

    private void setCajaActiva(boolean activa) {
        scrollCaja.setOpacity(activa ? 1.0 : 0.4);
        scrollCaja.setMouseTransparent(!activa);
    }

    private void cargarSprite(ImageView iv, int numPokedex) {
        File f = new File("./Media/Front/" + numPokedex + "f.png");
        if (f.exists()) iv.setImage(new Image(f.toURI().toString()));
    }

    private String estiloEquipo(boolean sel) {
        return "-fx-background-color: rgba(0,0,50,0.75); -fx-border-color: " + (sel ? "#00FF00" : "#FFD700") + ";"
                + "-fx-border-width: " + (sel ? "3" : "2") + "; -fx-border-radius: 10; -fx-background-radius: 10; -fx-cursor: hand;";
    }

    private String estiloCaja(boolean sel) {
        return "-fx-background-color: rgba(0,50,0,0.75); -fx-border-color: " + (sel ? "#00FF00" : "#aaaaaa") + ";"
                + "-fx-border-width: " + (sel ? "3" : "2") + "; -fx-border-radius: 10; -fx-background-radius: 10; -fx-cursor: hand;";
    }
    
    private void iniciarMusica() {
        try {
            File archivo = new File("./Media/Music/Equipo.mp3");
            if (!archivo.exists()) return;
            Media media = new Media(archivo.toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        } catch (Exception e) {
            System.out.println("Error al cargar música: " + e.getMessage());
        }
    }

    private void pararMusica() {
        if (mediaPlayer != null) mediaPlayer.stop();
    }
}