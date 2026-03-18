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
	    
	    String sql = "SELECT * FROM MOVIMIENTO"; //Arreglar select para que encuentre el movimiento del tipo y nivel correspondiente

	    try (Connection cn = Conexion.conectar();
	         PreparedStatement pst = cn.prepareStatement(sql)) {
	        
	        pst.setString(1, tipoPokemon);
	        pst.setInt(2, nivelPokemon);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) { // Usamos IF porque solo esperamos uno
	            m = new Movimiento(  //NO ME DEJA INSTANCIAR, IMAGINO QUE POR EL CONSTRUCTOR
	                rs.getInt("id_movimiento"),
	                rs.getString("nombre"),
	                rs.getInt("potencia"),
	                rs.getString("tipo")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return m;

	}
}
