package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Log {

    private String nombre;
    private ArrayList<Turno> turnos;
    private ArrayList<String> lineas;

    public Log() {
        this.turnos = new ArrayList<>();
        this.lineas = new ArrayList<>();
        // Generamos el nombre del fichero con la fecha actual
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        this.nombre = LocalDateTime.now().format(fmt) + ".log";
        crearCarpetaLogs();
    }

    // Registra el inicio del combate
    public void registrarInicioCombate(Pokemon pokemon, Pokemon pokemonRival) {
        int numTurno = turnos.size() + 1;
        turnos.add(new Turno(numTurno, "inicioCombate", "inicioCombate"));
        lineas.add(generarLinea("inicioCombate", pokemon, pokemonRival, numTurno));
        guardar();
    }

    // Registra que el pokemon del entrenador1 ha sido debilitado
    public void registrarDebilitado1(Pokemon pokemon, Pokemon pokemonRival) {
        int numTurno = turnos.size() + 1;
        turnos.add(new Turno(numTurno, "debilitado1", "-"));
        lineas.add(generarLinea("debilitado1", pokemon, pokemonRival, numTurno));
        guardar();
    }

    // Registra que el pokemon del entrenador2 ha sido debilitado
    public void registrarDebilitado2(Pokemon pokemon, Pokemon pokemonRival) {
        int numTurno = turnos.size() + 1;
        turnos.add(new Turno(numTurno, "-", "debilitado2"));
        lineas.add(generarLinea("debilitado2", pokemon, pokemonRival, numTurno));
        guardar();
    }

    // Registra que el entrenador1 ha cambiado su pokemon
    public void registrarCambio1(Pokemon pokemon, Pokemon pokemonRival) {
        int numTurno = turnos.size() + 1;
        turnos.add(new Turno(numTurno, "cambio1", "-"));
        lineas.add(generarLinea("cambio1", pokemon, pokemonRival, numTurno));
        guardar();
    }

    // Registra que el entrenador2 ha cambiado su pokemon
    public void registrarCambio2(Pokemon pokemon, Pokemon pokemonRival) {
        int numTurno = turnos.size() + 1;
        turnos.add(new Turno(numTurno, "-", "cambio2"));
        lineas.add(generarLinea("cambio2", pokemon, pokemonRival, numTurno));
        guardar();
    }

    // Registra que el entrenador ha ganado el combate
    public void registrarFinGana(Pokemon pokemon, Pokemon pokemonRival) {
        int numTurno = turnos.size() + 1;
        turnos.add(new Turno(numTurno, "finGanaCombate", "-"));
        lineas.add(generarLinea("finGanaCombate", pokemon, pokemonRival, numTurno));
        guardar();
    }

    // Registra que el entrenador ha perdido el combate
    public void registrarFinPierde(Pokemon pokemon, Pokemon pokemonRival) {
        int numTurno = turnos.size() + 1;
        turnos.add(new Turno(numTurno, "-", "finPierdeCombate"));
        lineas.add(generarLinea("finPierdeCombate", pokemon, pokemonRival, numTurno));
        guardar();
    }

    // Genera una linea con el formato pedido
    private String generarLinea(String evento, Pokemon pokemon, Pokemon pokemonRival, int numTurno) {

        // Formato de fecha para la linea
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        String fecha = LocalDateTime.now().format(fmt);

        // Comprobamos el estado de cada pokemon (OK o KO)
        String estadoPokemon = "OK";
        if (pokemon != null && pokemon.getEstado() == Estado.DEBILITADO) {
            estadoPokemon = "KO";
        }

        String estadoRival = "OK";
        if (pokemonRival != null && pokemonRival.getEstado() == Estado.DEBILITADO) {
            estadoRival = "KO";
        }

        // Recogemos los datos de cada pokemon
        String nombrePokemon = pokemon != null ? pokemon.getNombre() : "?";
        int nivelPokemon = pokemon != null ? pokemon.getNivel() : 0;
        String entrenador1 = pokemon != null ? "entrenador" + pokemon.getIdEntrenador() : "?";

        String nombreRival = pokemonRival != null ? pokemonRival.getNombre() : "?";
        int nivelRival = pokemonRival != null ? pokemonRival.getNivel() : 0;
        String entrenador2 = pokemonRival != null ? "entrenador" + pokemonRival.getIdEntrenador() : "?";

        // Construimos la linea
        String linea = fecha + " INFO " + evento
                + " pokemon={\"" + nombrePokemon + "\", " + nivelPokemon + ", " + entrenador1 + ", " + estadoPokemon + "}"
                + ", pokemonRival={\"" + nombreRival + "\", " + nivelRival + ", " + entrenador2 + ", " + estadoRival + "}"
                + ", turno=" + numTurno;

        return linea;
    }

    // Escribe todas las lineas en el fichero
    private void guardar() {
        File fichero = new File("logs" + File.separator + nombre);
        try {
            FileWriter fw = new FileWriter(fichero, false);
            for (String linea : lineas) {
                fw.write(linea + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el log: " + e.getMessage());
        }
    }

    // Crea la carpeta logs si no existe
    private void crearCarpetaLogs() {
        File carpeta = new File("logs");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }

    // Devuelve todo el log como String para mostrarlo en pantalla
    public String getLogCompleto() {
        String resultado = "";
        for (String linea : lineas) {
            resultado += linea + "\n";
        }
        return resultado;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Turno> getTurnos() {
        return turnos;
    }
}