package controller;

import dao.PokemonDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Entrenador;
import model.Pokemon;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class EquipoController {

    @FXML private AnchorPane slot1, slot2, slot3, slot4, slot5, slot6;
    @FXML private ImageView imgPoke1, imgPoke2, imgPoke3, imgPoke4, imgPoke5, imgPoke6;
    @FXML private Label lblPoke1, lblPoke2, lblPoke3, lblPoke4, lblPoke5, lblPoke6;

    private ArrayList<Pokemon> equipo = new ArrayList<>();
    private PokemonDAO pokemonDAO = new PokemonDAO();
    private MediaPlayer mediaPlayer;
    
    @FXML
    public void initialize() {
        cargarEquipo();
        iniciarMusica();
    }

    private void cargarEquipo() {
        equipo = pokemonDAO.obtenerEquipo(Entrenador.entrenadorLogueado.getIdEntrenador());

        ImageView[]  imagenes = {imgPoke1, imgPoke2, imgPoke3, imgPoke4, imgPoke5, imgPoke6};
        Label[]      labels   = {lblPoke1, lblPoke2, lblPoke3, lblPoke4, lblPoke5, lblPoke6};

        for (int i = 0; i < 6; i++) {
            imagenes[i].setImage(null);
            labels[i].setText("[ Vacío ]");
        }

        for (int i = 0; i < equipo.size() && i < 6; i++) {
            Pokemon p = equipo.get(i);
            File f = new File("./Media/Front/" + p.getNumPokedex() + "f.png");
            if (f.exists()) imagenes[i].setImage(new Image(f.toURI().toString()));
            labels[i].setText(p.getMote());
        }
    }

    @FXML public void handleSlot1(MouseEvent event) { }
    @FXML public void handleSlot2(MouseEvent event) { }
    @FXML public void handleSlot3(MouseEvent event) { }
    @FXML public void handleSlot4(MouseEvent event) { }
    @FXML public void handleSlot5(MouseEvent event) { }
    @FXML public void handleSlot6(MouseEvent event) { }

    @FXML
    public void handleMover(ActionEvent event) {
        try {
        	pararMusica();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MoverPokemon.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void handleVolver(ActionEvent event) {
        try {
        	pararMusica();
            Parent root = FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
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