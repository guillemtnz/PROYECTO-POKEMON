package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.util.LinkedList;
import java.util.Random;

import model.Combate;
import model.Entrenador;
import model.Movimiento;
import model.Pokemon;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class CombateController {

    // --- ELEMENTOS DEL FXML ---
    @FXML private ImageView imgRival, imgPropio;
    @FXML private Label lblNombreRival, lblNombrePropio, lblMensajeHuir;
    
    @FXML private AnchorPane barraVidaRival, barraExpRival;
    @FXML private AnchorPane barraVidaPropia, barraExpPropia;
    
    @FXML private TextArea txtLog;
    @FXML private AnchorPane panelAtaques;
    @FXML private Button btnAtaque1, btnAtaque2, btnAtaque3, btnAtaque4;
    @FXML private Button btnLuchar, btnObjeto, btnCambiar, btnHuir;

    // --- ATRIBUTOS DEL CONTROLADOR ---
    private Combate combateActivo;
    private Entrenador jugador;
    private Entrenador rival;

    // Anchura máxima de las barras según tu FXML
    private final double WIDTH_BARRA_RIVAL = 160.0;
    private final double WIDTH_BARRA_PROPIA = 200.0;
    
    private boolean cambioGratis = false; // Para saber si el cambio es por muerte
    
    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        
        jugador = Entrenador.entrenadorLogueado;
        
        java.sql.Connection conexionReal = dao.Conexion.conectar();
        
        if (conexionReal == null) {
            escribirEnLog("Error: No se ha podido conectar a la base de datos.");
            desactivarBotones();
            return;
        }

        dao.CombateDAO cDao = new dao.CombateDAO(conexionReal); 
        rival = cDao.obtenerRivalAleatorio();

        // Inicializar el combate si ambos tienen equipo
        if (jugador != null && rival != null && !jugador.getEquipoPrincipal().isEmpty() && !rival.getEquipoPrincipal().isEmpty()) {
            
            Pokemon pokeJugador = jugador.getEquipoPrincipal().get(0);
            Pokemon pokeRival = rival.getEquipoPrincipal().get(0);

            combateActivo = new Combate(
                1, jugador, rival, 0, 1, 0, 0, 
                pokeJugador, pokeRival, new java.util.LinkedList<>()
            );

            actualizarUI();
            escribirEnLog("¡Un " + rival.getNombre() + " salvaje ha aparecido!");
            escribirEnLog("¡Ve, " + pokeJugador.getNombre() + "!");
        } else {
            System.err.println("❌ ERROR: El jugador o el rival no tienen Pokémon en su equipo.");
            escribirEnLog("Error: Faltan entrenadores o equipos.");
            desactivarBotones();
        }
        iniciarMusica();
    }

    // --- ACTUALIZACIÓN DE LA VISTA ---
    private void actualizarUI() {
        Pokemon pPropio = combateActivo.getPokemonActivoJugador();
        Pokemon pRival = combateActivo.getPokemonActivoRival();

        //TEXTOS 
        lblNombrePropio.setText(pPropio.getMoteOCualquierNombre() + " Nv." + pPropio.getNivel());
        lblNombreRival.setText(pRival.getMoteOCualquierNombre() + " Nv." + pRival.getNivel());

        //BARRAS DE VIDA 
        double vitP = pPropio.getVitalidadActual();
        double vitTotalP = pPropio.getVitalidad() > 0 ? pPropio.getVitalidad() : 1; 
        double pctP = vitP / vitTotalP;
        barraVidaPropia.setPrefWidth(WIDTH_BARRA_PROPIA * Math.max(0, Math.min(1, pctP)));
        cambiarColorBarra(barraVidaPropia, pctP);

        double vitR = pRival.getVitalidadActual();
        double vitTotalR = pRival.getVitalidad() > 0 ? pRival.getVitalidad() : 1;
        double pctR = vitR / vitTotalR;
        barraVidaRival.setPrefWidth(WIDTH_BARRA_RIVAL * Math.max(0, Math.min(1, pctR)));
        cambiarColorBarra(barraVidaRival, pctR);

        //IMÁGENES 
        String imgP = "/Back/" + pPropio.getNumPokedex() + "b.png";
        String imgR = "/Front/" + pRival.getNumPokedex() + "f.png";

        try {
            java.net.URL urlP = getClass().getResource(imgP);
            java.net.URL urlR = getClass().getResource(imgR);

            if (urlP != null) imgPropio.setImage(new javafx.scene.image.Image(urlP.toExternalForm()));
            else System.out.println("No se encuentra imagen en: " + imgP);

            if (urlR != null) imgRival.setImage(new javafx.scene.image.Image(urlR.toExternalForm()));
            else System.out.println("No se encuentra imagen en: " + imgR);
            
        } catch (Exception e) {
            System.out.println("Error al cargar PNGs: " + e.getMessage());
        }
    }

    private void cambiarColorBarra(AnchorPane barra, double porcentaje) {
        if (porcentaje > 0.5) barra.setStyle("-fx-background-color: #44dd44; -fx-background-radius: 5;");
        else if (porcentaje > 0.2) barra.setStyle("-fx-background-color: #ffaa00; -fx-background-radius: 5;");
        else barra.setStyle("-fx-background-color: #dd4444; -fx-background-radius: 5;");
    }

    private void escribirEnLog(String mensaje) {
        txtLog.appendText(mensaje + "\n");
    }

    // MANEJO DE BOTONES

    @FXML
    private void handleLuchar() {
        panelAtaques.setVisible(true);
        Movimiento[] movs = combateActivo.getPokemonActivoJugador().getMovimientos();
        
        configurarBotonAtaque(btnAtaque1, movs[0]);
        configurarBotonAtaque(btnAtaque2, movs[1]);
        configurarBotonAtaque(btnAtaque3, movs[2]);
        configurarBotonAtaque(btnAtaque4, movs[3]);
    }

    private void configurarBotonAtaque(Button btn, Movimiento mov) {
        if (mov != null) {
            btn.setText(mov.getNombreMovimiento() + " (" + mov.getPp() + ")");
            btn.setDisable(mov.getPp() <= 0); 
        } else {
            btn.setText("-");
            btn.setDisable(true);
        }
    }

    @FXML private void handleVolverAcciones() { panelAtaques.setVisible(false); }

    @FXML private void handleAtaque1() { ejecutarTurnoAtaque(0); }
    @FXML private void handleAtaque2() { ejecutarTurnoAtaque(1); }
    @FXML private void handleAtaque3() { ejecutarTurnoAtaque(2); }
    @FXML private void handleAtaque4() { ejecutarTurnoAtaque(3); }

    private void ejecutarTurnoAtaque(int indiceMovimiento) {
        panelAtaques.setVisible(false);
        Movimiento movJugador = combateActivo.getPokemonActivoJugador().getMovimientos()[indiceMovimiento];
        
        escribirEnLog("\n--- TURNO " + combateActivo.getTurno() + " ---");
        
        Movimiento movRival = elegirMovimientoRival(combateActivo.getPokemonActivoRival());

        
        combateActivo.resolverTurno(movJugador, movRival);
        
        actualizarUI();
        comprobarEstadoPostTurno();
    }

    // BOTÓN DE USAR OBJETOS
    @FXML
    private void handleObjeto() { 
        Pokemon pPropio = combateActivo.getPokemonActivoJugador();
        
        if (pPropio.getVitalidadActual() == pPropio.getVitalidad()) {
            escribirEnLog(pPropio.getNombre() + " ya tiene la salud al máximo.");
            return;
        }

        escribirEnLog("\n--- TURNO " + combateActivo.getTurno() + " ---");
        
        // Usar objeto 
        combateActivo.usarObjeto(jugador, pPropio);
        
       
        Movimiento movRival = elegirMovimientoRival(combateActivo.getPokemonActivoRival());
        Pokemon pRival = combateActivo.getPokemonActivoRival();
        
        if (movRival != null) {
            escribirEnLog("¡" + pRival.getNombre() + " usó " + movRival.getNombreMovimiento() + "!");
            movRival.ejecutarMovimiento(pRival, pPropio);
            
            // Comprobamos si nos ha debilitado tras usar el objeto
            if (pPropio.getVitalidadActual() <= 0) {
                combateActivo.procesarDebilitamiento(pPropio);
            }
        }


        combateActivo.registrarTurno("Usó Objeto", movRival != null ? movRival.getNombreMovimiento() : "Ninguno");
        
        actualizarUI();
        comprobarEstadoPostTurno();
    }

    private Movimiento elegirMovimientoRival(Pokemon pRival) {
        Movimiento[] movs = pRival.getMovimientos();
        Random rnd = new Random();
        Movimiento elegido = null;
        
        int intentos = 0;
        while (elegido == null && intentos < 10) {
            Movimiento candidato = movs[rnd.nextInt(4)];
            if (candidato != null && candidato.getPp() > 0) {
                elegido = candidato;
            }
            intentos++;
        }
        return elegido;
    }

    private void comprobarEstadoPostTurno() {
        Pokemon pRival = combateActivo.getPokemonActivoRival();
        Pokemon pPropio = combateActivo.getPokemonActivoJugador();

        // Si rival debilitado
        if (pRival.getVitalidadActual() <= 0) {
            escribirEnLog("¡El " + pRival.getNombre() + " enemigo se ha debilitado!");
            
            //para que el rival saque el siguiente
            Pokemon proximoRival = null;
            for (Pokemon p : rival.getEquipoPrincipal()) {
                if (p.getVitalidadActual() > 0) {
                    proximoRival = p;
                    break;
                }
            }

            if (proximoRival != null) {
                combateActivo.setPokemonActivoRival(proximoRival);
                escribirEnLog(rival.getNombre() + " envía a " + proximoRival.getNombre() + ".");
                actualizarUI();
            } else {
                escribirEnLog("¡" + rival.getNombre() + " no tiene más Pokémon! ¡HAS GANADO!");
                desactivarBotones();
            }
        } 
        
        // Se debilita mi pokemon
        else if (pPropio.getVitalidadActual() <= 0) {
            escribirEnLog("¡Tu " + pPropio.getNombre() + " se ha debilitado! ¡Cambia!");
            // NO llamamos a desactivarBotones() porque quieres que sigan activos
            cambioGratis = true; // El siguiente cambio no recibirá ataque del rival
        }
    }

    @FXML
    private void handleCambiar() {
        //Obtener la lista de nombres de los Pokémon del equipo que no estén debilitados
        java.util.List<String> nombresPokemon = jugador.getEquipoPrincipal().stream()
                .filter(p -> p.getVitalidadActual() > 0)
                .map(p -> p.getNombre())
                .collect(java.util.stream.Collectors.toList());

        if (nombresPokemon.size() <= 1) {
            escribirEnLog("No tienes más Pokémon disponibles para cambiar.");
            return;
        }

        //Crear el "menú desplegable"
        javafx.scene.control.ChoiceDialog<String> dialog = new javafx.scene.control.ChoiceDialog<>(nombresPokemon.get(0), nombresPokemon);
        dialog.setTitle("Cambiar Pokémon");
        dialog.setHeaderText("¿A quién quieres sacar a combatir?");
        dialog.setContentText("Selecciona un Pokémon:");

       
        java.util.Optional<String> resultado = dialog.showAndWait();

        resultado.ifPresent(nombreElegido -> {
            
            Pokemon nuevoActivo = jugador.getEquipoPrincipal().stream()
                    .filter(p -> p.getNombre().equals(nombreElegido))
                    .findFirst().orElse(null);

            // Si es distinto al que ya está peleando, hacemos el cambio
            if (nuevoActivo != null && nuevoActivo != combateActivo.getPokemonActivoJugador()) {
                
                escribirEnLog("\n--- CAMBIO DE POKÉMON ---");
                escribirEnLog("¡" + combateActivo.getPokemonActivoJugador().getNombre() + ", vuelve!");
                
                combateActivo.setPokemonActivoJugador(nuevoActivo);
                escribirEnLog("¡Adelante, " + nuevoActivo.getNombre() + "!");

                // El rival ataca porque cambiar consume el turno del jugador
                Movimiento movRival = elegirMovimientoRival(combateActivo.getPokemonActivoRival());
                if (movRival != null) {
                    escribirEnLog("¡El rival aprovecha el cambio y usa " + movRival.getNombreMovimiento() + "!");
                    movRival.ejecutarMovimiento(combateActivo.getPokemonActivoRival(), nuevoActivo);
                }

                actualizarUI(); 
                comprobarEstadoPostTurno();
            } else {
                escribirEnLog(nombreElegido + " ya está en el campo de batalla.");
            }
        });
    }

    @FXML
    private void handleHuir() {
        combateActivo.retirarse();
        lblMensajeHuir.setText("Has huido cobardemente...");
        lblMensajeHuir.setVisible(true);
        escribirEnLog("¡Has huido del combate!");
        desactivarBotones();
    }

    @FXML 
    private void handleGritoRival() { 
        int numPokedex = combateActivo.getPokemonActivoRival().getNumPokedex();
        reproducirSonido(numPokedex); 
    }
    
    @FXML 
    private void handleGritoPropio() { 
        int numPokedex = combateActivo.getPokemonActivoJugador().getNumPokedex();
        reproducirSonido(numPokedex); 
    }

    // --- MÉTODO PARA REPRODUCIR EL AUDIO ---
    private void reproducirSonido(int numPokedex) {
        try {
            
            String rutaArchivo = "/Audio/" + numPokedex + ".mp3"; 
            
            java.net.URL url = getClass().getResource(rutaArchivo);
            
            if (url != null) {
                Media media = new Media(url.toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                System.out.println("♪ Reproduciendo: " + rutaArchivo);
            } else {
                System.out.println("⚠️ No se encontró el archivo de audio: " + rutaArchivo);
            }
        } catch (Exception e) {
            System.out.println("Error al reproducir audio: " + e.getMessage());
        }
    }

    private void desactivarBotones() {
        btnLuchar.setDisable(true);
        btnObjeto.setDisable(true);
        btnCambiar.setDisable(true);
        btnHuir.setDisable(true);
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