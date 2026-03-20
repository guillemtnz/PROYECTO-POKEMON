package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Entrenador;

public class EntrenadorDAO {

    public Entrenador login(String nombre, String password) {
        String sql = "SELECT * FROM ENTRENADOR WHERE NOM_ENTRENADOR = ? AND PASSWORD = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setString(1, nombre);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Entrenador e = new Entrenador(
                    rs.getString("NOM_ENTRENADOR"),
                    rs.getInt("POKEDOLLARS"),
                    new java.util.ArrayList<>(),
                    new java.util.ArrayList<>(),
                    new java.util.ArrayList<>()
                );
                
                e.setIdEntrenador(rs.getInt("ID_ENTRENADOR"));
                return e;
            }
        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
        }
        return null;
    }

    public boolean registrar(String nombre, String password) {
    	String sqlMaxId = "SELECT MAX(ID_ENTRENADOR) FROM ENTRENADOR"; //obtengo el máximo id_pokemon para generar el siguiente id
    	
        String sql = "INSERT INTO ENTRENADOR (ID_ENTRENADOR, NOM_ENTRENADOR, PASSWORD, POKEDOLLARS, TIPO_ENTRENADOR) VALUES (?, ?, ?, ?, 1)";
        try (Connection cn = Conexion.conectar();
            PreparedStatement pst = cn.prepareStatement(sql)) {

        	int nuevoId = 1; // Por si la tabla está vacía, empezamos en 1
            try (PreparedStatement pstMax = cn.prepareStatement(sqlMaxId);
                 ResultSet rs = pstMax.executeQuery()) {
                if (rs.next()) {
                    // Tomamos el máximo y le sumamos 1
                    nuevoId = rs.getInt(1) + 1; 
                }
            }
        	pst.setInt(1, nuevoId);
            pst.setString(2, nombre);
            pst.setString(3, password);
            pst.setInt(4, (int)(Math.random() * 201) + 800); // entre 800 y 1000 por ejemplo, nose cuanto poner
            int filas = pst.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public boolean existeEntrenador(String nombre) {
        String sql = "SELECT * FROM ENTRENADOR WHERE NOM_ENTRENADOR = ?";
        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setString(1, nombre);
            ResultSet rs = pst.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error al comprobar entrenador: " + e.getMessage());
            return false;
        }
    }
}