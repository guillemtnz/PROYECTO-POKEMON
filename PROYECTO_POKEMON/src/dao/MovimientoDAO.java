package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Estado;
import model.Movimiento;
import model.Movimiento.MecanicaEspecial;
import model.Movimiento.Stat;
import model.MovimientoAtaque;
import model.MovimientoAtaque.Objetivo; 
import model.MovimientoEstado;
import model.MovimientoStat;
import model.Tipo;



public class MovimientoDAO {
	
	public Movimiento buscarMovimiento(String tipoPokemon, int nivelPokemon) {
	    Movimiento m = null;
	    
	    String sql = "SELECT * FROM MOVIMIENTO WHERE TIPO = ? AND NIVEL_APRENDIZAJE = ?"; 

	    
	    try (Connection cn = Conexion.conectar(); 
	         PreparedStatement pst = cn.prepareStatement(sql)) {
	        
	        pst.setString(1, tipoPokemon);
	        pst.setInt(2, nivelPokemon);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) { 
	            
	            int idMov = rs.getInt("ID_MOVIMIENTO");
	            String nomMov = rs.getString("NOM_MOVIMIENTO");
	            Tipo tipoMov = Tipo.valueOf(rs.getString("TIPO").toUpperCase()); 
	            int nivel = rs.getInt("NIVEL_APRENDIZAJE");
	            int prioridad = rs.getInt("PRIORIDAD");
	            int precision = rs.getInt("PRECISION");
	            int pp = rs.getInt("PP");
	            String desc = rs.getString("DESCRIPCION");
	            
	            
	            MecanicaEspecial mecanica = null;
	            String txtMec = rs.getString("MECANICA_ESP");
	            if (txtMec != null && !txtMec.trim().isEmpty()) {
	                mecanica = MecanicaEspecial.valueOf(txtMec.toUpperCase());
	            }
	            int valorMecanica = rs.getInt("VALOR_MECANICA");

	            String claseDeMovimiento = rs.getString("CLASE"); 

	            if ("ATAQUE".equals(claseDeMovimiento)) {
	                
	                int potencia = rs.getInt("POTENCIA");
	                
	                Estado estSec = (rs.getString("ESTADO_SEC") != null) ? Estado.valueOf(rs.getString("ESTADO_SEC")) : null;
	                int probEst = rs.getInt("PROB_ESTADO");
	                Objetivo objEst = (rs.getString("OBJ_ESTADO") != null) ? Objetivo.valueOf(rs.getString("OBJ_ESTADO")) : null;
	                
	                Stat statSec = (rs.getString("STAT_SEC") != null) ? Stat.valueOf(rs.getString("STAT_SEC")) : null;
	                int cantStat = rs.getInt("CANTIDAD_STAT");
	                int probStat = rs.getInt("PROB_STAT");
	                Objetivo objStat = (rs.getString("OBJ_STAT") != null) ? Objetivo.valueOf(rs.getString("OBJ_STAT")) : null;

	              
	                m = new MovimientoAtaque(idMov, nomMov, tipoMov, nivel, prioridad, precision, pp, desc, 
	                                         mecanica, valorMecanica, potencia, estSec, probEst, objEst, 
	                                         statSec, cantStat, probStat, objStat);

	            } else if ("ESTADO".equals(claseDeMovimiento)) {
	                
	                Estado estPrincipal = (rs.getString("ESTADO_EFECTO") != null) ? Estado.valueOf(rs.getString("ESTADO_EFECTO")) : null;
	                int numTurnos = rs.getInt("NUM_TURNOS");

	                m = new MovimientoEstado(idMov, nomMov, tipoMov, nivel, prioridad, precision, pp, desc, 
	                                         estPrincipal, numTurnos, mecanica, valorMecanica);
	                                         
	            } else if ("STAT".equals(claseDeMovimiento)) {
	                
	                Stat statPrincipal = (rs.getString("STAT_SEC") != null) ? Stat.valueOf(rs.getString("STAT_SEC")) : null;
	                int cantStat = rs.getInt("CANTIDAD_STAT");
	                int probStat = rs.getInt("PROB_STAT"); 
	                Objetivo objStat = (rs.getString("OBJ_STAT") != null) ? Objetivo.valueOf(rs.getString("OBJ_STAT")) : null;


	                m = new MovimientoStat(idMov, nomMov, tipoMov, nivel, prioridad, precision, pp, desc, 
	                                       mecanica, valorMecanica, statPrincipal, cantStat, probStat, objStat);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Error en la base de datos al buscar movimiento: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return m; 
	}
}