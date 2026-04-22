package controller;

import dao.PokemonDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Entrenador;
import model.Pokemon;

import java.io.File;
import java.util.ArrayList;

/*
 * Controlador de la pantalla de Crianza
 * Permite seleccionar un Pokémon MACHO (padre) y uno HEMBRA (madre)
 * del equipo/caja del entrenador logueado, generar un huevo y
 * revelar al Pokemon hijo con atributos mezclados de ambos progenitores
 * Está vinculado a la vista Crianza.fxml
 */
public class CrianzaController {

    
    //  FXML – controles de la vista
    

    @FXML private ComboBox<Pokemon> cbPadre;
    @FXML private ComboBox<Pokemon> cbMadre;

    @FXML private ImageView imgPadre;
    @FXML private ImageView imgMadre;

    @FXML private Label lblInfoPadre;
    @FXML private Label lblInfoMadre;

    @FXML private Button btnCriar;

    @FXML private AnchorPane panelHuevo;
    @FXML private ImageView imgHuevoOHijo;
    @FXML private Button btnAbrirHuevo;
    @FXML private Label lblNombreHijo;
    @FXML private Label lblStatsHijo;

    @FXML private Label lblMensaje;

    
    //  Estado interno
    

    /** Pokémon hijo generado, pendiente de ser revelado */
    private Pokemon hijoGenerado;

    
    //  Inicialización
    

    @FXML
    public void initialize() {
        cargarPokemonEnCombos();
    }

    /**
     * Rellena ambos ComboBox con los Pokémon disponibles del entrenador
     * (equipo principal + caja), filtrando por sexo
     */
    private void cargarPokemonEnCombos() {
        Entrenador entrenador = Entrenador.entrenadorLogueado;

        // Unimos equipo principal y secundario (caja)
        ArrayList<Pokemon> todos = new ArrayList<>();
        if (entrenador.getEquipoPrincipal() != null)
            todos.addAll(entrenador.getEquipoPrincipal());
        if (entrenador.getEquipoSecundario() != null)
            todos.addAll(entrenador.getEquipoSecundario());

        ObservableList<Pokemon> machos = FXCollections.observableArrayList();
        ObservableList<Pokemon> hembras = FXCollections.observableArrayList();

        for (Pokemon p : todos) {
            if (p.getSexo() == Pokemon.Sexo.MACHO)  machos.add(p);
            if (p.getSexo() == Pokemon.Sexo.HEMBRA) hembras.add(p);
        }

        cbPadre.setItems(machos);
        cbMadre.setItems(hembras);

        // Muestra el nombre del Pokémon en el combo
        cbPadre.setCellFactory(lv -> new PokemonListCell());
        cbPadre.setButtonCell(new PokemonListCell());
        cbMadre.setCellFactory(lv -> new PokemonListCell());
        cbMadre.setButtonCell(new PokemonListCell());

        lblMensaje.setText("Selecciona un MACHO y una HEMBRA para criar.");
    }

    
    //  Eventos de selección en los combos
    

    @FXML
    public void onSeleccionarPadre() {
        Pokemon padre = cbPadre.getValue();
        if (padre == null) return;
        actualizarPanelPokemon(padre, imgPadre, lblInfoPadre);
    }

    @FXML
    public void onSeleccionarMadre() {
        Pokemon madre = cbMadre.getValue();
        if (madre == null) return;
        actualizarPanelPokemon(madre, imgMadre, lblInfoMadre);
    }

    /**
     * Actualiza la imagen y la etiqueta informativa de un progenitor.
     */
    private void actualizarPanelPokemon(Pokemon p, ImageView img, Label lbl) {
        // Imagen frontal del Pokémon según su número de Pokédex
        File imgFile = new File("./Media/Front/" + p.getNumPokedex() + "f.png");
        if (imgFile.exists())
            img.setImage(new Image(imgFile.toURI().toString()));

        lbl.setText("Nv." + p.getNivel()
                + "  Fertilidad:" + p.getFertilidad()
                + "  " + (p.getTipo1() != null ? p.getTipo1() : "")
                + (p.getTipo2() != null ? "/" + p.getTipo2() : ""));
    }

   
    //  Lógica principal: CRIAR
    

    /**
     * Valida la selección y llama a la lógica de crianza del entrenador.
     * Si todo es correcto, muestra el panel del huevo.
     */
    @FXML
    public void handleCriar(ActionEvent event) {
        Pokemon padre = cbPadre.getValue();
        Pokemon madre = cbMadre.getValue();

        //  Validaciones 
        if (padre == null || madre == null) {
            lblMensaje.setText("Debes seleccionar un PADRE y una MADRE.");
            return;
        }

        if (padre.getSexo() != Pokemon.Sexo.MACHO) {
            lblMensaje.setText("El padre debe ser MACHO.");
            return;
        }
        if (madre.getSexo() != Pokemon.Sexo.HEMBRA) {
            lblMensaje.setText("La madre debe ser HEMBRA.");
            return;
        }
        if (padre.getFertilidad() <= 0) {
            lblMensaje.setText(padre.getMote() + " no tiene fertilidad suficiente.");
            return;
        }
        if (madre.getFertilidad() <= 0) {
            lblMensaje.setText(madre.getMote() + " no tiene fertilidad suficiente.");
            return;
        }

        // ── Crianza ──
        hijoGenerado = Entrenador.entrenadorLogueado.criarPokemon(padre, madre);

        if (hijoGenerado == null) {
            lblMensaje.setText("La crianza no pudo completarse.");
            return;
        }

        // ── Mostrar huevo ──
        mostrarHuevo();
        lblMensaje.setText("¡Ha aparecido un huevo! Pulsa 'ABRIR HUEVO' para ver al nuevo bebé.");

        // Deshabilitar combos y botón criar mientras hay huevo pendiente
        cbPadre.setDisable(true);
        cbMadre.setDisable(true);
        btnCriar.setDisable(true);
    }

    // ──────────────────────────────────────────────
    //  ABRIR HUEVITO
    // ──────────────────────────────────────────────

    /**
     * Revela al Pokémon hijo: muestra su imagen, nombre y estadisticas
     * Tambien lo guarda en la base de datos (caja del entrenador)
     */
    @FXML
    public void handleAbrirHuevo(ActionEvent event) {
        if (hijoGenerado == null) return;

        // Imagen del hijo
        File imgFile = new File("./Media/Front/" + hijoGenerado.getNumPokedex() + "f.png");
        if (imgFile.exists())
            imgHuevoOHijo.setImage(new Image(imgFile.toURI().toString()));

        // Ocultar botón abrir y mostrar info
        btnAbrirHuevo.setVisible(false);

        lblNombreHijo.setText(hijoGenerado.getNombre() + " (" + hijoGenerado.getMote() + ")");
        lblNombreHijo.setVisible(true);

        lblStatsHijo.setText(
                "Nv.1  Vit:" + hijoGenerado.getVitalidad()
                + "  Atk:" + hijoGenerado.getAtaque()
                + "  Def:" + hijoGenerado.getDefensa()
                + "  AtkE:" + hijoGenerado.getAtaqueEspecial()
                + "  DefE:" + hijoGenerado.getDefensaEspecial()
                + "  Vel:" + hijoGenerado.getVelocidad()
        );
        lblStatsHijo.setVisible(true);

        // Guardar hijo en la caja del entrenador BD
        try {
        	PokemonDAO pokemonDAO = new PokemonDAO();
        	pokemonDAO.guardarPokemonCapturado(hijoGenerado, Entrenador.entrenadorLogueado.getIdEntrenador());
            Entrenador.entrenadorLogueado.capturarPokemon(hijoGenerado); // lo añade a equipoSecundario
        } catch (Exception e) {
            e.printStackTrace();
        }

        lblMensaje.setText("¡" + hijoGenerado.getNombre() + " ha nacido y está en tu caja!");

        // Reactivar la interfaz para una nueva crianza
        cbPadre.setDisable(false);
        cbMadre.setDisable(false);
        btnCriar.setDisable(false);
        hijoGenerado = null;
    }

    // ──────────────────────────────────────────────
    //  Helpers de UI
    // ──────────────────────────────────────────────

    /** Muestra el panel del huevo con la imagen de la pokeball/huevo */
    private void mostrarHuevo() {
        panelHuevo.setVisible(true);
        btnAbrirHuevo.setVisible(true);
        lblNombreHijo.setVisible(false);
        lblStatsHijo.setVisible(false);

        // Imagen de huevo (reutilizamos pokeball.png como placeholder de huevo)
        File huevoImg = new File("./Media/Img/pokeball.png");
        if (huevoImg.exists())
            imgHuevoOHijo.setImage(new Image(huevoImg.toURI().toString()));
    }

    // ──────────────────────────────────────────────
    //  Navegación
    // ──────────────────────────────────────────────

    @FXML
    public void handleVolver(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/MenuPrincipal.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    //  Celda personalizada para el ComboBox
   

    /**
     * Muestra "Mote (Nombre) Nvel" en cada opción del combo.
     */
    private static class PokemonListCell extends javafx.scene.control.ListCell<Pokemon> {
        @Override
        protected void updateItem(Pokemon p, boolean empty) {
            super.updateItem(p, empty);
            if (empty || p == null) {
                setText(null);
            } else {
                String mote = (p.getMote() != null && !p.getMote().isEmpty())
                        ? p.getMote() : p.getNombre();
                setText(mote + " (" + p.getNombre() + ") Nv." + p.getNivel());
            }
        }
    }
}
