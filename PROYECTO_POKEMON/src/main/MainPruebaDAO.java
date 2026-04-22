package main; 

import dao.MovimientoDAO;
import model.Movimiento;
import model.MovimientoAtaque;
import model.MovimientoEstado;
import model.MovimientoStat;

public class MainPruebaDAO {

    public static void main(String[] args) {
        
        // Instanciamos nuestro puente a la base de datos
        MovimientoDAO dao = new MovimientoDAO();


        // TEST 1: Buscar un ataque de daño (Placaje - Tipo Normal, Nivel 1)
        System.out.println("PRUEBA 1: Buscando ataque de DAÑO (Normal, Nivel 1)...");
        Movimiento mov1 = dao.buscarMovimiento("NORMAL", 1);
        analizarMovimiento(mov1);

        // TEST 2: Buscar un movimiento de estadística (Gruñido - Tipo Normal, Nivel 3)
        System.out.println("PRUEBA 2: Buscando ataque de STAT (Normal, Nivel 3)...");
        Movimiento mov2 = dao.buscarMovimiento("NORMAL", 3);
        analizarMovimiento(mov2);

        // TEST 3: Buscar un movimiento de estado (Canto - Tipo Normal, Nivel 24)
        System.out.println("PRUEBA 3: Buscando ataque de ESTADO (Normal, Nivel 24)...");
        Movimiento mov3 = dao.buscarMovimiento("NORMAL", 24);
        analizarMovimiento(mov3);

        // TEST 4: Buscar en un nivel vacío (Fuego, Nivel 2 - No existe en tu BD)
        System.out.println("PRUEBA 4: Buscando movimiento INEXISTENTE (Fuego, Nivel 2)...");
        Movimiento mov4 = dao.buscarMovimiento("FUEGO", 2);
        analizarMovimiento(mov4);
    }

    /**
     * Función auxiliar para imprimir por consola lo que nos devuelve la Base de Datos.
     * Aquí usamos el operador 'instanceof' para comprobar qué Hija ha creado el DAO.
     */
    private static void analizarMovimiento(Movimiento m) {
        if (m == null) {
            System.out.println(" -> RESULTADO: null (El Pokémon no aprende nada a este nivel o el tipo no coincide).\n");
            return;
        }

        System.out.println(" -> ¡Encontrado!: " + m.getNombreMovimiento() + " (Tipo: " + m.getTipoMovimiento() + ")");

        // Comprobamos la clase real del objeto gracias al Polimorfismo
        if (m instanceof MovimientoAtaque) {
            System.out.println(" -> INSTANCIA CREADA: MovimientoAtaque (Daño directo)");
        } 
        else if (m instanceof MovimientoEstado) {
            System.out.println(" -> INSTANCIA CREADA: MovimientoEstado (Alteración táctica)");
        } 
        else if (m instanceof MovimientoStat) {
            System.out.println(" -> INSTANCIA CREADA: MovimientoStat (Alteración de atributos)");
        }
        
        System.out.println(); 
    }
}