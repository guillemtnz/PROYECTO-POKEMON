package dao;

import java.sql.*;
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
        String sql = "INSERT INTO pokemon "
                   + "(NUM_POKEDEX, ID_ENTRENADOR, MOTE, NIVEL, FERTILIDAD, SEXO, UBICACION) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setInt(1, pokemon.getNumPokedex());
            pst.setInt(2, idEntrenador);
            pst.setString(3, pokemon.getMote());
            pst.setInt(4, pokemon.getNivel());
            pst.setInt(5, 5);                              // fertilidad 5 por defecto
            pst.setString(6, pokemon.getSexo().toString()); // macho o hembra
            pst.setString(7, "CAJA");                      // las capturas van a la caja directamente

            int filasInsertadas = pst.executeUpdate();
            return filasInsertadas > 0;

        } catch (SQLException e) {
            System.out.println("Error al guardar el Pokémon capturado: " + e.getMessage());
            return false;
        }
    }
}