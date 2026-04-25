package main;

import dao.CombateDAO;
import dao.Conexion;
import dao.PokemonDAO;
import model.Combate;
import model.Entrenador;
import model.Movimiento;
import model.Pokemon;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;

public class MainPruebaCombate {

    public static void main(String[] args) {
        
        System.out.println("Iniciando prueba de combate...");
        
        // 1. Probamos la conexión a la Base de Datos
        Connection conn = Conexion.conectar();
        if (conn == null) {
            System.err.println("❌ Error: No se pudo conectar a la base de datos. Asegúrate de que MySQL/XAMPP está encendido.");
            return;
        }

        // 2. Simulamos que el jugador "Guille" (ID 11) ha hecho login
        Entrenador jugador = new Entrenador("Guille", 915, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        jugador.setIdEntrenador(11);
        Entrenador.entrenadorLogueado = jugador;

        // Cargamos su equipo principal desde la BD
        PokemonDAO pDao = new PokemonDAO();
        jugador.setEquipoPrincipal(pDao.obtenerEquipo(11));

        if (jugador.getEquipoPrincipal().isEmpty()) {
            System.err.println("❌ Error: Guille no tiene Pokémon en su equipo en la base de datos.");
            return;
        }

        // 3. Generamos al rival aleatorio
        CombateDAO cDao = new CombateDAO(conn);
        Entrenador rival = cDao.obtenerRivalAleatorio();

        if (rival == null || rival.getEquipoPrincipal().isEmpty()) {
            System.err.println("❌ Error: No se pudo cargar el rival o su equipo desde la BD.");
            return;
        }

        // 4. Preparamos a los Pokémon que van a luchar
        Pokemon pokeJugador = jugador.getEquipoPrincipal().get(0);
        Pokemon pokeRival = rival.getEquipoPrincipal().get(0);

        Combate combate = new Combate(1, jugador, rival, 0, 1, 0, 0, pokeJugador, pokeRival, new LinkedList<>());

        System.out.println("\n==================================================");
        System.out.println("⚔️ ¡COMIENZA EL COMBATE!");
        System.out.println("Tú: " + jugador.getNombre() + " saca a " + pokeJugador.getNombre() + " (Nv." + pokeJugador.getNivel() + " | PS: " + pokeJugador.getVitalidadActual() + ")");
        System.out.println("Rival: " + rival.getNombre() + " saca a " + pokeRival.getNombre() + " (Nv." + pokeRival.getNivel() + " | PS: " + pokeRival.getVitalidadActual() + ")");
        System.out.println("==================================================\n");

        // 5. Buscamos el primer ataque válido del jugador
        Movimiento movJ = null;
        for (Movimiento m : pokeJugador.getMovimientos()) {
            if (m != null && m.getPp() > 0) {
                movJ = m;
                break;
            }
        }

        // Buscamos el primer ataque válido del rival
        Movimiento movR = null;
        for (Movimiento m : pokeRival.getMovimientos()) {
            if (m != null && m.getPp() > 0) {
                movR = m;
                break;
            }
        }

        if (movJ == null || movR == null) {
            System.err.println("❌ Error: Uno de los Pokémon no tiene ataques válidos o cargados correctamente. ¡Revisa tu PokemonDAO!");
            return;
        }

        // 6. ¡Lanzamos el turno!
        combate.resolverTurno(movJ, movR);

        // 7. Mostramos el resultado del choque
        System.out.println("\n==================================================");
        System.out.println("📊 RESULTADO TRAS EL TURNO 1");
        System.out.println(pokeJugador.getNombre() + " -> PS Restantes: " + pokeJugador.getVitalidadActual() + "/" + pokeJugador.getVitalidad() + " | PP de " + movJ.getNombreMovimiento() + " restantes: " + movJ.getPp());
        System.out.println(pokeRival.getNombre() + " -> PS Restantes: " + pokeRival.getVitalidadActual() + "/" + pokeRival.getVitalidad() + " | PP de " + movR.getNombreMovimiento() + " restantes: " + movR.getPp());
        System.out.println("==================================================");
    }
}
