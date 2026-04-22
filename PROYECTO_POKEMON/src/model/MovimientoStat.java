package model;

import model.MovimientoAtaque.Objetivo;

public class MovimientoStat extends Movimiento {
	
		
	private Stat stat; // stat a cambiar
	private int cantidad;
	private int probabilidadStat; 
	private Objetivo objetivo;
		
	
	public MovimientoStat(
			// de Movimiento
			int idMovimiento, String nombreMovimiento, Tipo tipoMovimiento, int nivel, 
			int prioridad, int precision, int pp, String desc, 
			MecanicaEspecial mecanicaEspecial, int valorMecanica, 
			
			// hijo
			Stat stat, int cantidad, int probabilidadStat, Objetivo objetivo) {
		
		super(idMovimiento, nombreMovimiento, tipoMovimiento, nivel, prioridad, precision, pp, desc, mecanicaEspecial, valorMecanica);
		this.stat = stat;
		this.cantidad = cantidad;
		this.probabilidadStat = probabilidadStat;
		this.objetivo = objetivo;
	}
		
	//  GETTERS Y SETTERS 
	

	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getProbabilidadStat() {
		return probabilidadStat;
	}

	public void setProbabilidadStat(int probabilidadStat) {
		this.probabilidadStat = probabilidadStat;
	}

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}

	@Override
	public void ejecutarMovimiento() {
		
	}
}