package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ObjetoDAO {

    // Actualiza la cantidad de un objeto específico en la mochila de un entrenador
    public void actualizarCantidadMochila(int idEntrenador, int idObjeto, int nuevaCantidad) {
        String sql = "UPDATE mochila SET CANTIDAD = ? WHERE ID_ENTRENADOR = ? AND ID_OBJETO = ?";
        
        try (Connection conn = Conexion.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, nuevaCantidad);
            pstmt.setInt(2, idEntrenador);
            pstmt.setInt(3, idObjeto); 
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error al actualizar la mochila en BD: " + e.getMessage());
        }
    }
}