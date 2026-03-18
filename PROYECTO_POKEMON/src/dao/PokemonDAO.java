package dao;

import java.sql.*;
import model.Pokemon;
import model.Pokemon.Sexo; // Importamos el Enum que creaste

public class PokemonDAO {

    public Pokemon generarPokemonAleatorio() {
        Pokemon p = new Pokemon();
        // SQL para traer un pokemon con sus stats base de la pokedex
        String sql = "SELECT * FROM POKEDEX ORDER BY RAND() LIMIT 1";

        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                // Mapeamos los datos de la DB a tu objeto
                p.setNumPokedex(rs.getInt("NUM_POKEDEX")); // Asegúrate que el nombre coincida con la DB
                p.setNombre(rs.getString("NOM_POKEMON"));
                
                
              
                p.setNivel(1); 
                p.setSexo(Math.random() > 0.5 ? Sexo.MACHO : Sexo.HEMBRA);
                p.setMote(p.getNombre()); // Por defecto el mote es el nombre
                
                return p;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener pokemon: " + e.getMessage());
        }
        return null;
    }
}