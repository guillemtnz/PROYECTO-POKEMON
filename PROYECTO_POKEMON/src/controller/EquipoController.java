package controller;

import dao.EntrenadorDAO;
import dao.ObjetoDAO;
import dao.PokemonDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Entrenador;
import model.Objeto;
import model.Pokemon;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class EquipoController {

    @FXML private AnchorPane slot1, slot2, slot3, slot4, slot5, slot6;
    @FXML private ImageView imgPoke1, imgPoke2, imgPoke3, imgPoke4, imgPoke5, imgPoke6;
    @FXML private Label lblPoke1, lblPoke2, lblPoke3, lblPoke4, lblPoke5, lblPoke6;
    
    @FXML
    private ComboBox<String> cbObjetos;

    private ArrayList<Pokemon> equipo = new ArrayList<>();
    private PokemonDAO pokemonDAO = new PokemonDAO();
    private MediaPlayer mediaPlayer;
    private ObjetoDAO objetoDAO = new ObjetoDAO();
    
    // --- VARIABLES QUE FALTABAN ---
    private Entrenador jugador = Entrenador.entrenadorLogueado;
    private Pokemon pokemonSeleccionado;
    
    @FXML
    public void initialize() {
        cargarEquipo();
        iniciarMusica();
    }

    private void cargarEquipo() {
        equipo = pokemonDAO.obtenerEquipo(jugador.getIdEntrenador());

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

    // --- AHORA LOS SLOTS SÍ SELECCIONAN AL POKÉMON ---
    @FXML public void handleSlot1(MouseEvent event) { seleccionarPokemon(0); }
    @FXML public void handleSlot2(MouseEvent event) { seleccionarPokemon(1); }
    @FXML public void handleSlot3(MouseEvent event) { seleccionarPokemon(2); }
    @FXML public void handleSlot4(MouseEvent event) { seleccionarPokemon(3); }
    @FXML public void handleSlot5(MouseEvent event) { seleccionarPokemon(4); }
    @FXML public void handleSlot6(MouseEvent event) { seleccionarPokemon(5); }

    private void seleccionarPokemon(int indice) {
        if (equipo.size() > indice) {
            pokemonSeleccionado = equipo.get(indice);
            
            EntrenadorDAO eDao = new EntrenadorDAO();
            jugador.setMochila(eDao.obtenerMochila(jugador.getIdEntrenador()));
            
            cargarObjetosDesplegable(pokemonSeleccionado);
            System.out.println("Seleccionado: " + pokemonSeleccionado.getMote() + 
                    " | Objetos en mochila: " + jugador.getMochila().size());
        }
    }

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
    
    private void cargarObjetosDesplegable(Pokemon pSeleccionado) {
        if (cbObjetos == null) return;
        
        cbObjetos.getItems().clear();
        cbObjetos.getItems().add("Ninguno");

        if (jugador.getMochila() != null && !jugador.getMochila().isEmpty()) {
            for (Objeto obj : jugador.getMochila()) {
                if (obj.getCantidad() > 0) {
                    cbObjetos.getItems().add(obj.getNombre());
                }
            }
        }

        if (pSeleccionado.getObjeto() != null) {
            String nombreEquipado = pSeleccionado.getObjeto().getNombre();
            if (!cbObjetos.getItems().contains(nombreEquipado)) {
                cbObjetos.getItems().add(nombreEquipado);
            }
            cbObjetos.setValue(nombreEquipado);
        } else {
            cbObjetos.setValue("Ninguno");
        }
    }
    
    @FXML
    private void handleCambiarObjeto() {
        if (cbObjetos == null || cbObjetos.getValue() == null || pokemonSeleccionado == null) return;

        String seleccion = cbObjetos.getValue();
        Pokemon p = pokemonSeleccionado; 
        
        Objeto objetoViejo = p.getObjeto();
        
        if (seleccion.equals("Ninguno")) {
            if (objetoViejo != null) {
                devolverObjetoAMochila(objetoViejo);
                p.setObjeto(null);
                actualizarPokemonBD(p); 
                System.out.println("Se ha quitado el objeto a " + p.getMote());
            }
        } else {
            Objeto objetoNuevo = obtenerObjetoPorNombre(seleccion); 
            
            // CORREGIDO: Usamos getIdObjeto()
            if (objetoViejo != null && objetoViejo.getIdObjeto() != objetoNuevo.getIdObjeto()) {
                devolverObjetoAMochila(objetoViejo); 
            }
            
            if (objetoViejo == null || objetoViejo.getIdObjeto() != objetoNuevo.getIdObjeto()) {
                quitarObjetoDeMochila(objetoNuevo); 
                p.setObjeto(objetoNuevo);
                actualizarPokemonBD(p); 
                System.out.println(p.getMote() + " se ha equipado " + objetoNuevo.getNombre());
            }
        }
    }
    
    // --- MÉTODOS AUXILIARES PARA LOS OBJETOS ---

    private Objeto obtenerObjetoPorNombre(String nombre) {
        if (jugador.getMochila() != null) {
            for (Objeto obj : jugador.getMochila()) {
                if (obj.getNombre().equals(nombre)) {
                    return obj;
                }
            }
        }
        return null;
    }

    private void devolverObjetoAMochila(Objeto obj) {
        obj.setCantidad(obj.getCantidad() + 1);
        objetoDAO.actualizarCantidadMochila(jugador.getIdEntrenador(), obj.getIdObjeto(), obj.getCantidad());
    }

    private void quitarObjetoDeMochila(Objeto obj) {
        obj.setCantidad(obj.getCantidad() - 1);
        objetoDAO.actualizarCantidadMochila(jugador.getIdEntrenador(), obj.getIdObjeto(), obj.getCantidad());
    }
    
    // --- MÉTODO QUE FALTABA PARA GUARDAR EL POKÉMON ---
    private void actualizarPokemonBD(Pokemon p) {
        // Llama al método de tu PokemonDAO
        pokemonDAO.actualizarObjetoEquipado(p);
    }
}