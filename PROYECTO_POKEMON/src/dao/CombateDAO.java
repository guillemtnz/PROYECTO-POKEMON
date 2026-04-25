package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Entrenador;
import model.Pokemon;

public class CombateDAO {

    private Connection conexion;


    public CombateDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public Entrenador obtenerRivalAleatorio() {
        Entrenador rival = null;
        String sql = "SELECT * FROM entrenador WHERE TIPO_ENTRENADOR = 2 ORDER BY RAND() LIMIT 1";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int id = rs.getInt("ID_ENTRENADOR");
                String nombre = rs.getString("NOM_ENTRENADOR");
                int pokedollars = rs.getInt("POKEDOLLARS");
                
                rival = new Entrenador(nombre, pokedollars, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                rival.setIdEntrenador(id);
                
                //obtengo el equipo rival
                
                PokemonDAO pDao = new PokemonDAO();
                ArrayList<Pokemon> equipoRival = pDao.obtenerEquipo(id);
                
                // Se lo asignamos al rival
                rival.setEquipoPrincipal(equipoRival);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener rival aleatorio: " + e.getMessage());
        }

        return rival;
    }
}
