package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Entrenador;
import model.Movimiento;
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
                
                p.setMovimientos(cargarMovimientos(p.getIdPokemon()));
                
                equipo.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener equipo: " + e.getMessage());
        }
        return equipo;
    }
    

    private Movimiento[] cargarMovimientos(int idPokemon) {
        Movimiento[] movs = new Movimiento[4];
        // Hacemos un JOIN entre pkmnmovimiento y movimiento
        String sql = "SELECT m.*, pm.PP AS PP_ACTUAL FROM pkmnmovimiento pm "
                   + "JOIN movimiento m ON pm.ID_MOVIMIENTO = m.ID_MOVIMIENTO "
                   + "WHERE pm.ID_POKEMON = ? AND pm.ACTIVO = 1 LIMIT 4";
        
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {
            
            pst.setInt(1, idPokemon);
            ResultSet rs = pst.executeQuery();
            
            int i = 0;
            while (rs.next() && i < 4) {
                int idMov = rs.getInt("ID_MOVIMIENTO");
                String nombre = rs.getString("NOMBRE");
                model.Tipo tipo = model.Tipo.valueOf(rs.getString("TIPO"));
                int nivel = rs.getInt("NIVEL");
                int precision = rs.getInt("PRECISION_MOV");
                int ppActual = rs.getInt("PP_ACTUAL"); 
                int prioridad = rs.getInt("PRIORIDAD");
                Movimiento.Blanco blanco = Movimiento.Blanco.valueOf(rs.getString("BLANCO"));
                String efectoEsp = rs.getString("EFECTO_ESPECIAL");
                
                String categoriaStr = rs.getString("CATEGORIA");
                
                // 1. Si es FÍSICO o ESPECIAL (MovimientoAtaque)
                if (categoriaStr.equals("FÍSICO") || categoriaStr.equals("ESPECIAL")) {
                    model.MovimientoAtaque.Categoria cat = categoriaStr.equals("FÍSICO") ? 
                            model.MovimientoAtaque.Categoria.FISICO : model.MovimientoAtaque.Categoria.ESPECIAL;
                    int potencia = rs.getInt("POTENCIA");
                    
                    model.Estado estado = null;
                    if (rs.getString("EFECTO") != null) {
                        estado = model.Estado.valueOf(rs.getString("EFECTO"));
                    }
                    int prob = rs.getInt("PROBABILIDAD_EFECTO");
                    
                    movs[i] = new model.MovimientoAtaque(idMov, nombre, tipo, nivel, precision, ppActual, prioridad, blanco, efectoEsp, cat, potencia, estado, prob, null, 0);
                } 
                // 2. Si es de ESTADO (Dormir, Paralizar...) (MovimientoEstado)
                else if (categoriaStr.equals("ESTADO")) {
                    model.Estado efecto = model.Estado.valueOf(rs.getString("EFECTO"));
                    int prob = rs.getInt("PROBABILIDAD_EFECTO");
                    movs[i] = new model.MovimientoEstado(idMov, nombre, tipo, nivel, precision, ppActual, prioridad, blanco, efectoEsp, efecto, prob);
                }
                // 3. Si es de STATS (Danza Espada, Látigo...) (MovimientoStat)
                else if (categoriaStr.equals("STAT")) {
                    String dbStat = rs.getString("STAT_MODIFICADO");
                    Movimiento.Stat stat = null;
                    if (dbStat != null) {
                        // Adaptamos los nombres de la BD al enum de Java
                        if (dbStat.equals("ATAQUE_ESP")) stat = Movimiento.Stat.ATAQUE_ESPECIAL;
                        else if (dbStat.equals("DEFENSA_ESP")) stat = Movimiento.Stat.DEFENSA_ESPECIAL;
                        else stat = Movimiento.Stat.valueOf(dbStat);
                    }
                    int cantidad = rs.getInt("CANTIDAD_MODIFICACION");
                    movs[i] = new model.MovimientoStat(idMov, nombre, tipo, nivel, precision, ppActual, prioridad, blanco, efectoEsp, stat, cantidad);
                }
                i++;
            }
        } catch (SQLException e) {
            System.out.println("Error al cargar movimientos: " + e.getMessage());
        }
        return movs;
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
    
    public void guardarProgreso(Pokemon p) {
        String sql = "UPDATE pokemon SET NIVEL = ?, EXPERIENCIA = ?, VITALIDAD = ?, ATAQUE = ?, DEFENSA = ?, AT_ESP = ?, DEF_ESP = ?, VELOCIDAD = ? WHERE ID_POKEMON = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, p.getNivel());
            pstmt.setInt(2, p.getExperiencia());
            pstmt.setInt(3, p.getVitalidad());
            pstmt.setInt(4, p.getAtaque());
            pstmt.setInt(5, p.getDefensa());
            pstmt.setInt(6, p.getAtaqueEspecial());
            pstmt.setInt(7, p.getDefensaEspecial());
            pstmt.setInt(8, p.getVelocidad());
            pstmt.setInt(9, p.getIdPokemon());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al guardar progreso: " + e.getMessage());
        }
    }
    
 // LOCALIZACIÓN: dao.PokemonDAO.java
    public void actualizarObjetoEquipado(Pokemon p) {
        String sql = "UPDATE pokemon SET ID_OBJETO = ? WHERE ID_POKEMON = ?";
        
        try (java.sql.Connection conn = Conexion.conectar();
             java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            
            if (p.getObjeto() != null) {
                
                pstmt.setInt(1, p.getObjeto().getIdObjeto()); 
            } else {
                pstmt.setNull(1, java.sql.Types.INTEGER);
            }
            
            pstmt.setInt(2, p.getIdPokemon());
            pstmt.executeUpdate();
            
        } catch (java.sql.SQLException e) {
            System.err.println("Error al actualizar el objeto del Pokémon en la BD: " + e.getMessage());
        }
    }
    
    
}