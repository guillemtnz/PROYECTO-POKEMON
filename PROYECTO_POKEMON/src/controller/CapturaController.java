package controller;

import dao.PokemonDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Entrenador;
import model.Pokemon;
import java.io.File;

 /*
 * Controlador de la pantalla de captura de Pokémon.
 * Gestiona la obtención de un Pokémon aleatorio desde la base de datos,
 * la mecánica de captura con Pokéballs, la validación del mote
 * y el guardado del Pokémon capturado en la base de datos.
 * Está vinculado a la vista Captura.fxml
 */

public class CapturaController {
	
	private boolean musica = false;
	private MediaPlayer mediaPlayer;
	private MediaPlayer gritoPlayer;
	
	@FXML private ImageView imgMusica;
	
	@FXML
    void activarDesactivarSonido(MouseEvent event) {
		musica();
    }
	


    @FXML private Label lblNombreSalvaje;
    @FXML private Label lblMensaje;
    @FXML private Label lblPokeballs;
    @FXML private ImageView imgPokemonSalvaje;
    @FXML private Button btnCapturar;
    @FXML private Button btnRandomizar;
    @FXML private TextField txtMote;
    @FXML private Button btnConfirmarMote;
    
    // Pokémon que aparece actualmente en pantalla
    private Pokemon pokemonActual = null;
    
    // Número de pokeballs disponibles para el jugador
    private int pokeballs = 5;
    
    // Indica si el Pokémon actual ha sido capturado
    private boolean capturado;
    
    // DAO para acceder a los datos de Pokemon en la BD
    private PokemonDAO pokemonDAO = new PokemonDAO();
    
    // Método initialize() que se ejecuta automáticamente al cargar la vista
    @FXML
    public void initialize() {
    	musica();
        lblMensaje.setText("Pulsa Randomizar para encontrar un Pokemon");
        btnCapturar.setDisable(true);
    }
    
    // Método que se ejecuta al pulsar el botón "Randomizar"
    @FXML
    public void handleRandomizar(ActionEvent event) {
        pokemonActual = pokemonDAO.generarPokemonAleatorio();

        if (pokemonActual == null) {
            lblMensaje.setText("Error al conectar con la base de datos.");
            return;
        }
        
        reproducirGrito(pokemonActual.getNumPokedex());

        lblNombreSalvaje.setText(pokemonActual.getNombre().toUpperCase() + "  Nv.1");

        // Buscar sprite por ruta de archivo
        String rutaSprite = "Media/Front/" + pokemonActual.getNumPokedex() + "f.png";
        File archivoSprite = new File(rutaSprite);
        
        if (archivoSprite.exists()) {
            Image sprite = new Image(archivoSprite.toURI().toString());
            imgPokemonSalvaje.setImage(sprite);
        } else {
            System.out.println("Sprite no encontrado: " + rutaSprite);
        }

        lblMensaje.setText("Un " + pokemonActual.getNombre().toUpperCase() + " salvaje aparecio!");
        btnCapturar.setDisable(false);
        capturado = false;
        txtMote.setVisible(false);
        btnConfirmarMote.setVisible(false);
    }
    
    // Método que se ejecuta al pulsar el botón "Capturar".
    @FXML
    public void handleCapturar(ActionEvent event) {
        if (pokemonActual == null) {
            lblMensaje.setText("Primero randomiza un Pokemon!");
            return;
        }
        if (pokeballs <= 0) {
            lblMensaje.setText("No te quedan Pokeballs!");
            return;
        }

        pokeballs--;
        lblPokeballs.setText("Pokeballs: " + pokeballs);

        double probabilidad = Math.random();
        if (probabilidad <= 0.66) {
            capturado = true;
            lblMensaje.setText(pokemonActual.getNombre().toUpperCase() + " capturado! Ponle un mote:");
            btnCapturar.setDisable(true);
            btnRandomizar.setDisable(true);
            txtMote.setVisible(true);
            btnConfirmarMote.setVisible(true);
        } else {
            lblMensaje.setText(pokemonActual.getNombre().toUpperCase() + " escapo! Intentalo de nuevo.");
        }
    }
    
    // Método que se ejecuta al pulsar el botón "Confirmar mote".
    @FXML
    public void handleConfirmarMote(ActionEvent event) {
        String mote = txtMote.getText().trim();

        if (!mote.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ]+")) {
            lblMensaje.setText("El mote solo puede tener letras, sin espacios ni numeros.");
            return;
        }

        String[] palabrasProhibidas = {"tonto", "idiota", "bobolon", "estupido"};
        for (String palabra : palabrasProhibidas) {
            if (mote.toLowerCase().matches(".*" + palabra + ".*")) {
                lblMensaje.setText("Ese mote contiene palabras no permitidas.");
                return;
            }
        }

        pokemonActual.setMote(mote);

        // guarda el pokemon capturado en la base de datos
        // pongo id 1 como default que seria ash porque no hay como una gestion de sesion
        int idEntrenadorActual = 1;
        boolean guardado = pokemonDAO.guardarPokemonCapturado(pokemonActual, idEntrenadorActual);

        if (guardado) {
            
            Entrenador.entrenadorLogueado.getEquipoSecundario().add(pokemonActual);

            lblMensaje.setText(pokemonActual.getNombre().toUpperCase() + " añadido a tu caja con el mote: " + mote);
        } else {
            lblMensaje.setText("Error al guardarlo en la BD");
        }

        txtMote.setVisible(false);
        btnConfirmarMote.setVisible(false);
        pokemonActual = null;
        btnRandomizar.setDisable(false);
    }
    
    // Método que navega de vuelta al menú principal.
    @FXML
    public void handleVolver(ActionEvent event) {
        navegarA("/view/MenuPrincipal.fxml", event);
    }

    private void navegarA(String ruta, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ruta));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void musica() {
    	
    	if(!this.musica) {
    		String musica = "./Media/Music/pokemon_salvaje.mp3";
        	Media sound = new Media(new File(musica).toURI().toString());
        	
        	mediaPlayer = new MediaPlayer(sound);
        	mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        	mediaPlayer.play();
        	imgMusica.setImage(new Image(new File("./Media/Img/unmuted.png").toURI().toString()));
        	
        	this.musica =true;
    	}else {
    		mediaPlayer.stop();
    		this.musica =false;
    		imgMusica.setImage(new Image(new File("./Media/Img/muted.png").toURI().toString()));
    	}
    	
    }
    
    public void reproducirGrito(int numPokedex) {
    	
    		String rutaGrito = "./Media/Audio/" + numPokedex + ".mp3";
        	Media grito = new Media(new File(rutaGrito).toURI().toString());
        	
        	gritoPlayer = new MediaPlayer(grito);
        	gritoPlayer.play();
    }
}