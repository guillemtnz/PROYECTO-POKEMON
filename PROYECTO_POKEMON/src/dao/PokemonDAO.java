package dao;

import java.sql.*;
import java.util.ArrayList;
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

    public boolean guardarPokemonCapturado(Pokemon pokemon, int idEntrenador) {
        int idLogueado = Entrenador.entrenadorLogueado.getIdEntrenador();
        String sqlMaxId = "SELECT MAX(ID_POKEMON) FROM POKEMON";
        String sqlContarEquipo = "SELECT COUNT(*) FROM POKEMON WHERE ID_ENTRENADOR = ? AND UBICACION = 'EQUIPO'";
        String sqlInsert = "INSERT INTO POKEMON "
                + "(ID_POKEMON, NUM_POKEDEX, ID_ENTRENADOR, MOTE, NIVEL, FERTILIDAD, SEXO, UBICACION, VITALIDAD, ATAQUE, DEFENSA, AT_ESP, DEF_ESP, VELOCIDAD) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = Conexion.conectar()) {
            int nuevoId = 1;
            try (PreparedStatement pstMax = cn.prepareStatement(sqlMaxId);
                 ResultSet rs = pstMax.executeQuery()) {
                if (rs.next()) nuevoId = rs.getInt(1) + 1;
            }

            int pokemonesEnEquipo = 0;
            try (PreparedStatement pstContar = cn.prepareStatement(sqlContarEquipo)) {
                pstContar.setInt(1, idLogueado);
                ResultSet rsContar = pstContar.executeQuery();
                if (rsContar.next()) pokemonesEnEquipo = rsContar.getInt(1);
            }

            String ubicacion = pokemonesEnEquipo < 6 ? "EQUIPO" : "CAJA";

            try (PreparedStatement pst = cn.prepareStatement(sqlInsert)) {
                pst.setInt(1, nuevoId);
                pst.setInt(2, pokemon.getNumPokedex());
                pst.setInt(3, idLogueado);
                pst.setString(4, pokemon.getMote());
                pst.setInt(5, pokemon.getNivel());
                pst.setInt(6, 5);
                pst.setString(7, pokemon.getSexo().toString());
                pst.setString(8, ubicacion);
                for (int i = 9; i <= 14; i++) {
                    pst.setInt(i, (int)(Math.random() * 5) + 1);
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

    public ArrayList<Pokemon> getPokemonDeEntrenador(int idEntrenador) {
        ArrayList<Pokemon> lista = new ArrayList<>();
        String sql = "SELECT * FROM POKEMON WHERE ID_ENTRENADOR = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, idEntrenador);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pokemon p = new Pokemon();
                p.setIdPokemon(rs.getInt("ID_POKEMON"));
                p.setNumPokedex(rs.getInt("NUM_POKEDEX"));
                p.setNombre(rs.getString("MOTE"));
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

    public ArrayList<Pokemon> obtenerEquipo(int idEntrenador) {
        ArrayList<Pokemon> equipo = new ArrayList<>();
        String sql = "SELECT * FROM POKEMON WHERE ID_ENTRENADOR = ? AND UBICACION = 'EQUIPO'";
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, idEntrenador);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pokemon p = new Pokemon();
                p.setIdPokemon(rs.getInt("ID_POKEMON"));
                p.setNumPokedex(rs.getInt("NUM_POKEDEX"));
                p.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
                p.setNombre(rs.getString("MOTE"));
                p.setMote(rs.getString("MOTE"));
                p.setNivel(rs.getInt("NIVEL"));
                p.setFertilidad(rs.getInt("FERTILIDAD"));
                p.setSexo(rs.getString("SEXO").equals("MACHO") ? Pokemon.Sexo.MACHO : Pokemon.Sexo.HEMBRA);
                p.setVitalidad(rs.getInt("VITALIDAD"));
                p.setAtaque(rs.getInt("ATAQUE"));
                p.setDefensa(rs.getInt("DEFENSA"));
                p.setAtaqueEspecial(rs.getInt("AT_ESP"));
                p.setDefensaEspecial(rs.getInt("DEF_ESP"));
                p.setVelocidad(rs.getInt("VELOCIDAD"));
                equipo.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener equipo: " + e.getMessage());
        }
        return equipo;
    }

    public ArrayList<Pokemon> obtenerCaja(int idEntrenador) {
        ArrayList<Pokemon> caja = new ArrayList<>();
        String sql = "SELECT * FROM POKEMON WHERE ID_ENTRENADOR = ? AND UBICACION = 'CAJA'";
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setInt(1, idEntrenador);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Pokemon p = new Pokemon();
                p.setIdPokemon(rs.getInt("ID_POKEMON"));
                p.setNumPokedex(rs.getInt("NUM_POKEDEX"));
                p.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
                p.setNombre(rs.getString("MOTE"));
                p.setMote(rs.getString("MOTE"));
                p.setNivel(rs.getInt("NIVEL"));
                p.setFertilidad(rs.getInt("FERTILIDAD"));
                p.setSexo(rs.getString("SEXO").equals("MACHO") ? Pokemon.Sexo.MACHO : Pokemon.Sexo.HEMBRA);
                p.setVitalidad(rs.getInt("VITALIDAD"));
                p.setAtaque(rs.getInt("ATAQUE"));
                p.setDefensa(rs.getInt("DEFENSA"));
                p.setAtaqueEspecial(rs.getInt("AT_ESP"));
                p.setDefensaEspecial(rs.getInt("DEF_ESP"));
                p.setVelocidad(rs.getInt("VELOCIDAD"));
                caja.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener caja: " + e.getMessage());
        }
        return caja;
    }

    public boolean cambiarUbicacion(int idPokemon, String nuevaUbicacion) {
        String sql = "UPDATE POKEMON SET UBICACION = ? WHERE ID_POKEMON = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, nuevaUbicacion);
            pst.setInt(2, idPokemon);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al cambiar ubicacion: " + e.getMessage());
            return false;
        }
    }
}