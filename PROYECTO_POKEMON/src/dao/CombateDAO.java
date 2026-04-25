package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    
    
    // ------------------------------- LIGA PKMN -----------------------------
    

 // Método para obtener un entrenador específico por su nombre (para el Alto Mando)
 public Entrenador obtenerEntrenadorPorNombre(String nombre) {
     Entrenador e = null;
     String sql = "SELECT * FROM ENTRENADOR WHERE NOM_ENTRENADOR = ?";
     try (Connection conn = Conexion.conectar();
          PreparedStatement ps = conn.prepareStatement(sql)) {
         ps.setString(1, nombre);
         try (ResultSet rs = ps.executeQuery()) {
             if (rs.next()) {
                 e = new Entrenador();
                 e.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
                 e.setNombre(rs.getString("NOM_ENTRENADOR"));
                 e.setPokedollars(rs.getInt("POKEDOLLARS"));
                 
                 e.setEquipoPrincipal(new dao.PokemonDAO().obtenerEquipo(e.getIdEntrenador()));
             }
         }
     } catch (SQLException ex) { ex.printStackTrace(); }
     return e;
 }

 // Obtener al actual Campeón (Tipo 3, Campeon 1)
 public Entrenador obtenerCampeonActual() {
     Entrenador campeon = null;
     String sql = "SELECT * FROM ENTRENADOR WHERE TIPO = 3 AND CAMPEON = 1";
     try (Connection conn = Conexion.conectar();
          PreparedStatement ps = conn.prepareStatement(sql);
          ResultSet rs = ps.executeQuery()) {
         if (rs.next()) {
             campeon = new Entrenador();
             campeon.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
             campeon.setNombre(rs.getString("NOM_ENTRENADOR"));
             campeon.setPokedollars(rs.getInt("POKEDOLLARS"));
             campeon.setEquipoPrincipal(new dao.PokemonDAO().obtenerEquipo(campeon.getIdEntrenador()));
         }
     } catch (SQLException ex) { ex.printStackTrace(); }
     return campeon;
 }


 public void actualizarCampeon(int idNuevoCampeon) {
     try (Connection conn = Conexion.conectar()) {
         conn.setAutoCommit(false); 
         String quitarCorona = "UPDATE ENTRENADOR SET CAMPEON = 0 WHERE TIPO = 3 AND CAMPEON = 1";
         String ponerCorona = "UPDATE ENTRENADOR SET TIPO = 3, CAMPEON = 1 WHERE ID_ENTRENADOR = ?";
         try (PreparedStatement ps1 = conn.prepareStatement(quitarCorona);
              PreparedStatement ps2 = conn.prepareStatement(ponerCorona)) {
             ps1.executeUpdate();
             ps2.setInt(1, idNuevoCampeon);
             ps2.executeUpdate();
             conn.commit();
         } catch (SQLException ex) { conn.rollback(); throw ex; }
     } catch (SQLException ex) { ex.printStackTrace(); }
 }
    
    
    
}
