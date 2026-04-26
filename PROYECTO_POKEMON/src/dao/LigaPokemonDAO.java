package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Entrenador;

public class LigaPokemonDAO {

    private Connection conexion;

    public LigaPokemonDAO(Connection conexion) {
        this.conexion = conexion;
    }


    public Entrenador obtenerEntrenadorPorNombre(String nombre) {
        Entrenador rival = null;
        String sql = "SELECT * FROM entrenador WHERE NOM_ENTRENADOR = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID_ENTRENADOR");
                    String nom = rs.getString("NOM_ENTRENADOR");
                    int pokedollars = rs.getInt("POKEDOLLARS");
                    
                    rival = new Entrenador(nom, pokedollars, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                    rival.setIdEntrenador(id);
                    
                    // Cargar su equipo
                    PokemonDAO pDao = new PokemonDAO();
                    rival.setEquipoPrincipal(pDao.obtenerEquipo(id));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rival;
    }

    // 
    public Entrenador obtenerCampeonActual() {
        Entrenador campeon = null;
       
        String sql = "SELECT * FROM entrenador WHERE TIPO_ENTRENADOR = 3 AND CAMPEON = 1";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int id = rs.getInt("ID_ENTRENADOR");
                String nom = rs.getString("NOM_ENTRENADOR");
                int pokedollars = rs.getInt("POKEDOLLARS");
                
                campeon = new Entrenador(nom, pokedollars, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                campeon.setIdEntrenador(id);
                
                PokemonDAO pDao = new PokemonDAO();
                campeon.setEquipoPrincipal(pDao.obtenerEquipo(id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campeon;
    }

    //
    public void actualizarCampeon(int idNuevoCampeon) {
        String sql1 = "UPDATE entrenador SET CAMPEON = 0 WHERE TIPO_ENTRENADOR = 3 AND CAMPEON = 1";
        String sql2 = "UPDATE entrenador SET TIPO_ENTRENADOR = 3, CAMPEON = 1 WHERE ID_ENTRENADOR = ?";
        
        try {
            conexion.setAutoCommit(false);
            try (PreparedStatement ps1 = conexion.prepareStatement(sql1);
                 PreparedStatement ps2 = conexion.prepareStatement(sql2)) {
                
                ps1.executeUpdate(); // Quitar corona al antiguo
                
                ps2.setInt(1, idNuevoCampeon);
                ps2.executeUpdate(); // Poner corona al nuevo
                
                conexion.commit();
            } catch (SQLException e) {
                conexion.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
