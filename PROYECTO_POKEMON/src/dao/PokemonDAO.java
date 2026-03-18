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
}