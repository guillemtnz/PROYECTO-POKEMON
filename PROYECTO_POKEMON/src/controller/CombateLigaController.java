package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;

import model.Combate;
import model.Entrenador;
import model.Log;
import model.Movimiento;
import model.Pokemon;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class CombateLigaController {

    //ELEMENTOS DEL FXML 
    @FXML private ImageView imgRival, imgPropio;
    @FXML private Label lblNombreRival, lblNombrePropio, lblMensajeHuir;
    
    @FXML private AnchorPane barraVidaRival, barraExpRival;
    @FXML private AnchorPane barraVidaPropia, barraExpPropia;
    
    @FXML private TextArea txtLog;
    @FXML private AnchorPane panelAtaques;
    @FXML private Button btnAtaque1, btnAtaque2, btnAtaque3, btnAtaque4;
    @FXML private Button btnLuchar, btnCuracion, btnCambiar, btnHuir;

    // ATRIBUTOS DEL CONTROLADOR
    private Combate combateActivo;
    private Entrenador jugador;
    private Entrenador rival;

    private final double WIDTH_BARRA_RIVAL = 160.0;
    private final double WIDTH_BARRA_PROPIA = 200.0;
    
    private Log historialTecnico = new Log();
    private MediaPlayer mediaPlayer;

    // ATRIBUTOS DE LA LIGA
    private LinkedList<Entrenador> colaLiga = new LinkedList<>();
    private int numeroCombate = 1;

    @FXML
    public void initialize() {
        txtLog.setStyle("-fx-text-fill: black; -fx-opacity: 1.0;");
        
        jugador = Entrenador.entrenadorLogueado;
        
        // Curamos 
        if (jugador != null && jugador.getEquipoPrincipal() != null) {
            for (Pokemon p : jugador.getEquipoPrincipal()) {
                p.setVitalidadActual(p.getVitalidad());
                p.setEstado(null);
            }
        }
        
        java.sql.Connection conexionReal = dao.Conexion.conectar();
        if (conexionReal == null) {
            escribirEnLog("Error: No se ha podido conectar a la base de datos.");
            desactivarBotones();
            return;
        }

        //Cargamos alto mando y campeón
        dao.LigaPokemonDAO ligaDao = new dao.LigaPokemonDAO(conexionReal); 
        
        colaLiga.add(ligaDao.obtenerEntrenadorPorNombre("GUILLERMO"));
        colaLiga.add(ligaDao.obtenerEntrenadorPorNombre("LUISRE"));
        colaLiga.add(ligaDao.obtenerEntrenadorPorNombre("ALVARO"));
        colaLiga.add(ligaDao.obtenerEntrenadorPorNombre("JULIO"));
        colaLiga.add(ligaDao.obtenerCampeonActual());
        
        colaLiga.removeIf(Objects::isNull); // Por si alguno no se encontrara
        
        iniciarMusica();

        if (!colaLiga.isEmpty() && jugador != null && !jugador.getEquipoPrincipal().isEmpty()) {
            iniciarSiguienteCombateLiga();
        } else {
            escribirEnLog("Error: Faltan entrenadores de la liga o tu equipo está vacío.");
            desactivarBotones();
        }
    }

    private void iniciarSiguienteCombateLiga() {
        this.rival = colaLiga.pollFirst();
        
        //Curación
        for (Pokemon p : jugador.getEquipoPrincipal()) {
            // Restaurar Salud
            p.setVitalidadActual(p.getVitalidad());
            p.setEstado(null); 
            
            // Restaurar PP de todos los movimientos
            for (Movimiento m : p.getMovimientos()) {
                if (m != null) {
                	m.setPp(m.getMaxPp());
                }
            }
        }

        // Curamos también al rival que entra
        for (Pokemon p : rival.getEquipoPrincipal()) {
            p.setVitalidadActual(p.getVitalidad());
            p.setEstado(null);
        }

        //LOG 
        txtLog.clear(); // Limpiamos la pantalla para el nuevo rival
        escribirEnLog("========================================");
        escribirEnLog("       ¡SALA DEL ALTO MANDO: " + numeroCombate + "!");
        escribirEnLog("       RIVAL: " + rival.getNombre());
        escribirEnLog("========================================\n");
        escribirEnLog("▶ Tu equipo ha sido restaurado por completo (PS y PP).");

        // Iniciamos el objeto combate
        Pokemon pokeJugador = jugador.getEquipoPrincipal().get(0);
        Pokemon pokeRival = rival.getEquipoPrincipal().get(0);

        combateActivo = new Combate(1, jugador, rival, 0, 1, 0, 0, pokeJugador, pokeRival, new LinkedList<>());
        
        actualizarUI();
    }

    
    private void actualizarUI() {
        Pokemon pPropio = combateActivo.getPokemonActivoJugador();
        Pokemon pRival = combateActivo.getPokemonActivoRival();

        lblNombrePropio.setText(pPropio.getMoteOCualquierNombre() + " Nv." + pPropio.getNivel());
        lblNombreRival.setText(pRival.getMoteOCualquierNombre() + " Nv." + pRival.getNivel());

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
        
        double expMaxP = pPropio.getNivel() * 50.0; 
        if (expMaxP <= 0) expMaxP = 1; 
        double pctExpP = (double) pPropio.getExperiencia() / expMaxP;
        pctExpP = Math.max(0.0, Math.min(1.0, pctExpP)); 
        barraExpPropia.setPrefWidth(WIDTH_BARRA_PROPIA * pctExpP);
        barraExpPropia.setStyle("-fx-background-color: #33ccff; -fx-background-radius: 5;");

        double expMaxR = pRival.getNivel() * 50.0;
        if (expMaxR <= 0) expMaxR = 1;
        double pctExpR = (double) pRival.getExperiencia() / expMaxR;
        pctExpR = Math.max(0.0, Math.min(1.0, pctExpR));
        barraExpRival.setPrefWidth(WIDTH_BARRA_RIVAL * pctExpR);
        barraExpRival.setStyle("-fx-background-color: #33ccff; -fx-background-radius: 5;");

        String imgP = "/Back/" + pPropio.getNumPokedex() + "b.png";
        String imgR = "/Front/" + pRival.getNumPokedex() + "f.png";

        try {
            java.net.URL urlP = getClass().getResource(imgP);
            java.net.URL urlR = getClass().getResource(imgR);
            if (urlP != null) imgPropio.setImage(new javafx.scene.image.Image(urlP.toExternalForm()));
            if (urlR != null) imgRival.setImage(new javafx.scene.image.Image(urlR.toExternalForm()));
        } catch (Exception e) {}
    }

    private void cambiarColorBarra(AnchorPane barra, double porcentaje) {
        if (porcentaje > 0.5) barra.setStyle("-fx-background-color: #44dd44; -fx-background-radius: 5;");
        else if (porcentaje > 0.2) barra.setStyle("-fx-background-color: #ffaa00; -fx-background-radius: 5;");
        else barra.setStyle("-fx-background-color: #dd4444; -fx-background-radius: 5;");
    }

    private void escribirEnLog(String mensaje) {
        txtLog.appendText(mensaje + "\n");
        txtLog.setScrollTop(Double.MAX_VALUE); 
        txtLog.selectPositionCaret(txtLog.getLength()); 
    }

    //MANEJO DE ATAQUES Y BOTONES
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
        
        
        Movimiento movJ = combateActivo.getPokemonActivoJugador().getMovimientos()[indiceMovimiento];
        Movimiento movR = elegirMovimientoRival(combateActivo.getPokemonActivoRival());

        
        String resultadoDelTurno = combateActivo.resolverTurno(movJ, movR);
        
        
        escribirEnLog(resultadoDelTurno);

        
        actualizarUI();
        comprobarEstadoPostTurno();
    }

    @FXML
    private void handleCuracion() { 
        Pokemon pPropio = combateActivo.getPokemonActivoJugador();
        Pokemon pRival = combateActivo.getPokemonActivoRival();
        
        if (pPropio.getVitalidadActual() == pPropio.getVitalidad()) {
            escribirEnLog(pPropio.getNombre() + " ya tiene la salud al máximo.");
            return;
        }

        escribirEnLog("\n--- TURNO " + combateActivo.getTurno() + " ---");
        combateActivo.curacion(jugador, pPropio);
        escribirEnLog("¡Has usado una curación! " + pPropio.getNombre() + " recupera PS.");
        historialTecnico.registrarTurnoGeneral(pPropio, pRival, "Curacion");
        
        Movimiento movRival = elegirMovimientoRival(pRival);
        if (movRival != null) {
            escribirEnLog("¡" + pRival.getNombre() + " usó " + movRival.getNombreMovimiento() + "!");
            movRival.ejecutarMovimiento(pRival, pPropio);
        }
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
            if (candidato != null && candidato.getPp() > 0) elegido = candidato;
            intentos++;
        }
        return elegido;
    }


    private void comprobarEstadoPostTurno() {
        Pokemon pRival = combateActivo.getPokemonActivoRival();
        Pokemon pPropio = combateActivo.getPokemonActivoJugador();

        // CASO 1: EL POKÉMON RIVAL SE DEBILITA
        if (pRival.getVitalidadActual() <= 0) {
            escribirEnLog("¡El " + pRival.getNombre() + " enemigo se ha debilitado!");
            
            Pokemon proximoRival = null;
            for (Pokemon p : rival.getEquipoPrincipal()) {
                if (p.getVitalidadActual() > 0) {
                    proximoRival = p;
                    break;
                }
            }

            if (proximoRival != null) {
                // El rival saca a otro Pokémon
                combateActivo.setPokemonActivoRival(proximoRival);
                escribirEnLog(rival.getNombre() + " envía a " + proximoRival.getNombre() + ".");
                actualizarUI();
            } else {
                
                if (!colaLiga.isEmpty()) {
                    // Popup para ver si queremos seguir
                    mostrarPopupIntermedio();
                } else {
                    
                    escribirEnLog("\n==================================");
                    escribirEnLog("¡" + rival.getNombre() + " no tiene más Pokémon!");
                    escribirEnLog("¡¡¡HAS GANADO LA LIGA POKÉMON!!!");
                    escribirEnLog("==================================\n");
                    
                    // Actualizar la base de datos 
                    dao.LigaPokemonDAO lpDao = new dao.LigaPokemonDAO(dao.Conexion.conectar());
                    lpDao.actualizarCampeon(jugador.getIdEntrenador());
                    
                    finalizarYGuardar();
                    desactivarBotones();

                    javafx.animation.Timeline timeline = new javafx.animation.Timeline(
                        new javafx.animation.KeyFrame(javafx.util.Duration.seconds(4), e -> volverAlMenu())
                    );
                    timeline.play();
                }
            }
        } 
        
       //Mi pokemon se debilita
        else if (pPropio.getVitalidadActual() <= 0) {
            escribirEnLog("¡Tu " + pPropio.getNombre() + " se ha debilitado!");
            boolean tieneMas = false;
            for (Pokemon p : jugador.getEquipoPrincipal()) {
                if (p.getVitalidadActual() > 0) { tieneMas = true; break; }
            }

            if (tieneMas) {
                escribirEnLog("¡Cambia a otro Pokémon para continuar!");
                btnLuchar.setDisable(true);
                btnCuracion.setDisable(true);
                btnHuir.setDisable(true);
                btnCambiar.setDisable(false);
            } else {
                escribirEnLog("\n==================================");
                escribirEnLog("¡No te quedan Pokémon! Has perdido la Liga...");
                escribirEnLog("==================================\n");
                desactivarBotones();

                javafx.animation.Timeline timeline = new javafx.animation.Timeline(
                    new javafx.animation.KeyFrame(javafx.util.Duration.seconds(3), e -> volverAlMenu())
                );
                timeline.play();
            }
        }
    }

    
    @FXML
    private void handleCambiar() {
        java.util.List<String> nombresPokemon = jugador.getEquipoPrincipal().stream()
                .filter(p -> p.getVitalidadActual() > 0)
                .map(p -> p.getNombre())
                .collect(java.util.stream.Collectors.toList());

        if (nombresPokemon.isEmpty() || (nombresPokemon.size() == 1 && nombresPokemon.get(0).equals(combateActivo.getPokemonActivoJugador().getNombre()))) {
            escribirEnLog("No te quedan otros Pokémon sanos para salir a luchar.");
            return;
        }

        javafx.scene.control.ChoiceDialog<String> dialog = new javafx.scene.control.ChoiceDialog<>(nombresPokemon.get(0), nombresPokemon);
        dialog.setTitle("Cambiar Pokémon");
        dialog.setHeaderText("¿A quién quieres sacar a combatir?");
        dialog.setContentText("Selecciona un Pokémon:");

        java.util.Optional<String> resultado = dialog.showAndWait();

        resultado.ifPresent(nombreElegido -> {
            Pokemon nuevoActivo = jugador.getEquipoPrincipal().stream()
                    .filter(p -> p.getNombre().equals(nombreElegido))
                    .findFirst().orElse(null);

            Pokemon pokemonActual = combateActivo.getPokemonActivoJugador();

            if (nuevoActivo != null && nuevoActivo != pokemonActual) {
                boolean esCambioPorMuerte = (pokemonActual.getVitalidadActual() <= 0);

                escribirEnLog("\n--- CAMBIO DE POKÉMON ---");
                escribirEnLog("¡" + pokemonActual.getNombre() + ", vuelve!");
                
                combateActivo.setPokemonActivoJugador(nuevoActivo);
                escribirEnLog("¡Adelante, " + nuevoActivo.getNombre() + "!");

                if (!esCambioPorMuerte) {
                    Movimiento movRival = elegirMovimientoRival(combateActivo.getPokemonActivoRival());
                    if (movRival != null) {
                        escribirEnLog("¡El rival aprovecha el cambio y usa " + movRival.getNombreMovimiento() + "!");
                        movRival.ejecutarMovimiento(combateActivo.getPokemonActivoRival(), nuevoActivo);
                    }
                } else {
                    escribirEnLog("Cambio seguro. Ahora es tu turno.");
                }

                btnLuchar.setDisable(false);
                btnCuracion.setDisable(false);
                btnHuir.setDisable(false);

                actualizarUI(); 
                comprobarEstadoPostTurno();
            }
        });
    }

    @FXML
    private void handleHuir() {
        combateActivo.retirarse();
        lblMensajeHuir.setText("Has huido cobardemente de la Liga...");
        lblMensajeHuir.setVisible(true);
        escribirEnLog("¡Has huido de la Liga Pokémon!");
        desactivarBotones();
        volverAlMenu();
    }

    @FXML private void handleGritoRival() { reproducirSonido(combateActivo.getPokemonActivoRival().getNumPokedex()); }
    @FXML private void handleGritoPropio() { reproducirSonido(combateActivo.getPokemonActivoJugador().getNumPokedex()); }

    private void reproducirSonido(int numPokedex) {
        try {
            String rutaArchivo = "/Audio/" + numPokedex + ".mp3"; 
            java.net.URL url = getClass().getResource(rutaArchivo);
            if (url != null) {
                Media media = new Media(url.toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
        } catch (Exception e) {}
    }

    private void desactivarBotones() {
        btnLuchar.setDisable(true);
        btnCuracion.setDisable(true);
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
        } catch (Exception e) {}
    }
    
    private void finalizarYGuardar() {
        dao.PokemonDAO pDao = new dao.PokemonDAO();
        for (Pokemon p : jugador.getEquipoPrincipal()) {
            pDao.guardarProgreso(p);
        }
        escribirEnLog("¡Los datos de tu equipo han sido guardados!");
    }
    
    private void volverAlMenu() {
        try {
            if (mediaPlayer != null) mediaPlayer.stop();
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/view/MenuPrincipal.fxml"));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = (javafx.stage.Stage) btnLuchar.getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    

    private void mostrarPopupIntermedio() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("¡Victoria en el Alto Mando!");
        alerta.setHeaderText("¡Has derrotado a " + rival.getNombre() + "!");
        alerta.setContentText("Tu equipo mantiene su estado actual. ¿Qué deseas hacer?");

        ButtonType btnContinuar = new ButtonType("Continuar al siguiente combate");
        ButtonType btnAbandonar = new ButtonType("Abandonar la Liga");

        alerta.getButtonTypes().setAll(btnContinuar, btnAbandonar);

        // showAndWait() pausa el código hasta que el jugador hace clic en un botón
        Optional<ButtonType> resultado = alerta.showAndWait();

        if (resultado.isPresent() && resultado.get() == btnContinuar) {
            numeroCombate++;
            // Limpiamos el Log 
            txtLog.clear(); 
            escribirEnLog("==================================");
            escribirEnLog(" AVANZANDO A LA SIGUIENTE SALA... ");
            escribirEnLog("==================================");
            
            iniciarSiguienteCombateLiga();
        } else {
            // Si le da a abandonar o cierra la ventana
            escribirEnLog("\nHas decidido retirarte del Alto Mando. ¡Vuelve cuando seas más fuerte!");
            desactivarBotones();
            volverAlMenu();
        }
    }
}