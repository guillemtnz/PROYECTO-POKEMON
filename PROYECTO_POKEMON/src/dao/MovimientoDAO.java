package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Estado;
import model.Movimiento;
import model.Movimiento.Blanco;
import model.Movimiento.Stat;
import model.MovimientoAtaque;
import model.MovimientoAtaque.Categoria;
import model.MovimientoEstado;
import model.MovimientoStat;
import model.Tipo;

public class MovimientoDAO {
	
	public Movimiento buscarMovimiento(String tipoPokemon, int nivelPokemon) {
	    Movimiento m = null;
	    
	    String sql = "SELECT * FROM movimiento WHERE TIPO = ? AND NIVEL = ?"; 

	    try (Connection cn = Conexion.conectar(); 
	         PreparedStatement pst = cn.prepareStatement(sql)) {
	        
	        pst.setString(1, tipoPokemon);
	        pst.setInt(2, nivelPokemon);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) { 
	            
	            
	            int idMov = rs.getInt("ID_MOVIMIENTO");
	            String nomMov = rs.getString("NOMBRE");
	            int nivel = rs.getInt("NIVEL");
	            Tipo tipoMov = Tipo.valueOf(rs.getString("TIPO").toUpperCase()); 
	            int precision = rs.getInt("PRECISION_MOV"); 
	            int pp = rs.getInt("PP");
	            int prioridad = rs.getInt("PRIORIDAD");
	            
	            Blanco blanco = Blanco.valueOf(rs.getString("BLANCO").toUpperCase());
	            String efectoEspecial = rs.getString("EFECTO_ESPECIAL");

	           
	            String categoria = rs.getString("CATEGORIA").toUpperCase();
	            
	            Estado efectoEnum = null;
	            String textoEfecto = rs.getString("EFECTO");
	            if (textoEfecto != null && !textoEfecto.trim().isEmpty()) {
	            	efectoEnum = Estado.valueOf(textoEfecto.toUpperCase());
	            }
	            
	            Stat statEnum = null;
	            String textoStat = rs.getString("STAT_MODIFICADO");
	            if (textoStat != null && !textoStat.trim().isEmpty()) {
	            	statEnum = Stat.valueOf(textoStat.toUpperCase());
	            }
	            
	            int probEfecto = rs.getInt("PROBABILIDAD_EFECTO");
	            int cantModificacion = rs.getInt("CANTIDAD_MODIFICACION");

	            
	            switch (categoria) {
	                case "FÍSICO":
	                case "ESPECIAL":
	                    int potencia = rs.getInt("POTENCIA");
	                    Categoria catAtaque = categoria.equals("FÍSICO") ? Categoria.FISICO : Categoria.ESPECIAL;
	                    
	                    m = new MovimientoAtaque(
	                        idMov, nomMov, tipoMov, nivel, precision, pp, prioridad, blanco, efectoEspecial, 
	                        catAtaque, potencia, efectoEnum, probEfecto, statEnum, cantModificacion
	                    );
	                    break;

	                case "ESTADO":
	                    m = new MovimientoEstado(
	                        idMov, nomMov, tipoMov, nivel, precision, pp, prioridad, blanco, efectoEspecial, 
	                        efectoEnum, probEfecto
	                    );
	                    break;

	                case "STAT":
	                    m = new MovimientoStat(
	                        idMov, nomMov, tipoMov, nivel, precision, pp, prioridad, blanco, efectoEspecial, 
	                        statEnum, cantModificacion
	                    );
	                    break;
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error en la base de datos al buscar movimiento: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return m; 
	}
}