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
                   + "(ID_POKEMON, NUM_POKEDEX, ID_ENTRENADOR, MOTE, NIVEL, FERTILIDAD, SEXO, UBICACION, VITALIDAD, ATAQUE, DEFENSA, AT_ESP, DEF_ESP, VELOCIDAD) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
                pst.setInt(6, 5); //fertilidad 5 por defecto
                pst.setString(7, pokemon.getSexo().toString());
                pst.setString(8, "CAJA");  //los pokemon capturados van a la caja
                
                for (int i = 9; i <= 14; i++) {
                    int stat = (int) (Math.random() * 5) + 1;
                    pst.setInt(i, stat);
                }

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
    /* obtiene los pokemon de un entrenador desde la base de datos */
    public java.util.ArrayList<Pokemon> getPokemonDeEntrenador(int idEntrenador) {
        java.util.ArrayList<Pokemon> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM POKEMON WHERE ID_ENTRENADOR = ?";
        
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, idEntrenador);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pokemon p = new Pokemon();
                p.setIdPokemon(rs.getInt("ID_POKEMON"));
                p.setNumPokedex(rs.getInt("NUM_POKEDEX"));
                p.setNombre(rs.getString("MOTE")); // usamos el mote como nombre visible
                p.setMote(rs.getString("MOTE"));
                p.setNivel(rs.getInt("NIVEL"));
                p.setSexo(Pokemon.Sexo.valueOf(rs.getString("SEXO")));
                p.setVitalidad(rs.getInt("VITALIDAD"));
                p.setAtaque(rs.getInt("ATAQUE"));
                p.setDefensa(rs.getInt("DEFENSA"));
                p.setAtaqueEspecial(rs.getInt("AT_ESP"));
                p.setDefensaEspecial(rs.getInt("DEF_ESP"));
                p.setVelocidad(rs.getInt("VELOCIDAD"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar pokemon del entrenador: " + e.getMessage());
        }
        return lista;
    }
}