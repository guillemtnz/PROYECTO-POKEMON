package dao;

import java.sql.*;

import model.Entrenador;
import model.Pokemon;
import model.Pokemon.Sexo; 

public class PokemonDAO {

    public Pokemon generarPokemonAleatorio() {
        Pokemon p = new Pokemon();

        String sql = "SELECT * FROM POKEDEX ORDER BY RAND() LIMIT 1";

        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                p.setNumPokedex(rs.getInt("NUM_POKEDEX")); 
                p.setNombre(rs.getString("NOM_POKEMON"));
                p.setNivel(1); 
                p.setSexo(Math.random() > 0.5 ? Sexo.MACHO : Sexo.HEMBRA);
                p.setMote(p.getNombre()); 
                return p;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pokemon: " + e.getMessage());
        }
        return null;
    }

    /* guarda un pokemon capturado en la base de datos  */
    
    public boolean guardarPokemonCapturado(Pokemon pokemon, int idEntrenador) {
        
    	int idLogueado = Entrenador.entrenadorLogueado.getIdEntrenador();
    	
        String sqlMaxId = "SELECT MAX(ID_POKEMON) FROM POKEMON"; //obtengo el máximo id_pokemon para generar el siguiente id
        
        
        String sqlInsert = "INSERT INTO POKEMON "
                   + "(ID_POKEMON, NUM_POKEDEX, ID_ENTRENADOR, MOTE, NIVEL, FERTILIDAD, SEXO, UBICACION) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar()) {
            
            
            int nuevoId = 1; // Por si la tabla está vacía, empezamos en 1
            try (PreparedStatement pstMax = cn.prepareStatement(sqlMaxId);
                 ResultSet rs = pstMax.executeQuery()) {
                if (rs.next()) {
                    // Tomamos el máximo y le sumamos 1
                    nuevoId = rs.getInt(1) + 1; 
                }
            }


            try (PreparedStatement pst = cn.prepareStatement(sqlInsert)) {
                pst.setInt(1, nuevoId); 
                pst.setInt(2, pokemon.getNumPokedex());
                pst.setInt(3, idLogueado);
                pst.setString(4, pokemon.getMote());
                pst.setInt(5, pokemon.getNivel());
                pst.setInt(6, 5);
                pst.setString(7, pokemon.getSexo().toString());
                pst.setString(8, "CAJA");

                int filasInsertadas = pst.executeUpdate();
                
                if (filasInsertadas > 0) {
                    pokemon.setIdPokemon(nuevoId); 
                    return true;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
        return false;
    }
}