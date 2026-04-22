package model;

public class MovimientoAtaque extends Movimiento {
	
	
	public enum Categoria {
		FISICO, ESPECIAL
	}
	
	private Categoria categoria;
	private int potencia;
	private Estado estado; 
	private int probabilidadEstado;
	private Stat statModificado; 
	private int cantidadStat;

	
	public MovimientoAtaque(int idMovimiento, String nombreMovimiento, Tipo tipoMovimiento, int nivel, 
			int precision, int pp, int prioridad, Blanco blanco, String efectoEspecial, 
			Categoria categoria, int potencia, Estado estado, int probabilidadEstado, 
			Stat statModificado, int cantidadStat) {
		
		super(idMovimiento, nombreMovimiento, tipoMovimiento, nivel, precision, pp, prioridad, blanco, efectoEspecial);
		
		this.categoria = categoria;
		this.potencia = potencia;
		this.estado = estado;
		this.probabilidadEstado = probabilidadEstado;
		this.statModificado = statModificado;
		this.cantidadStat = cantidadStat;
	}

	// GETTERS Y SETTERS

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getProbabilidadEstado() {
		return probabilidadEstado;
	}

	public void setProbabilidadEstado(int probabilidadEstado) {
		this.probabilidadEstado = probabilidadEstado;
	}

	public Stat getStatModificado() {
		return statModificado;
	}

	public void setStatModificado(Stat statModificado) {
		this.statModificado = statModificado;
	}

	public int getCantidadStat() {
		return cantidadStat;
	}

	public void setCantidadStat(int cantidadStat) {
		this.cantidadStat = cantidadStat;
	}

	@Override
	public void ejecutarMovimiento() {
		
	}
}