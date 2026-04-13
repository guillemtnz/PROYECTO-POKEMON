package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Movimiento;

//HAY QUE MODIFICAR TANTO LA TABLA MOVIMIENTO COMO SU IMPLEMENTACIÓN EN EL PROGRAMA

public class MovimientoDAO {
	
	
	public Movimiento buscarMovimiento(String tipoPokemon, int nivelPokemon) {
	    Movimiento m = null;
	    
	    String sql = "SELECT * FROM MOVIMIENTO WHERE TIPO = ? AND NIVEL_APRENDIZAJE = ?"; 

	    try (Connection cn = Conexion.conectar();
	         PreparedStatement pst = cn.prepareStatement(sql)) {
	        
	        pst.setString(1, tipoPokemon);
	        pst.setInt(2, nivelPokemon);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) { // Usamos IF porque solo esperamos uno
	            m = new Movimiento(  //NO ME DEJA INSTANCIAR, IMAGINO QUE POR EL CONSTRUCTOR
	            		
	                rs.getInt("ID_MOVIMIENTO"),
	                rs.getString("NOM_MOVIMIENTO"),
	                rs.getInt("POTENCIA"),
	                rs.getString("TIPO")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return m;  //si no coincide ningun movimiento devuelve null

	}
	
}
