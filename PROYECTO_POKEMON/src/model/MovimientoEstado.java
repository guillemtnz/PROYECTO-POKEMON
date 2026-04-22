package model;

public class MovimientoEstado extends Movimiento {
		

	private Estado efecto; 
	private int probabilidadEfecto;
	
	
	public MovimientoEstado(int idMovimiento, String nombreMovimiento, Tipo tipoMovimiento, int nivel, 
			int precision, int pp, int prioridad, Blanco blanco, String efectoEspecial, 
			Estado efecto, int probabilidadEfecto) {
		
		super(idMovimiento, nombreMovimiento, tipoMovimiento, nivel, precision, pp, prioridad, blanco, efectoEspecial);
		
		this.efecto = efecto;
		this.probabilidadEfecto = probabilidadEfecto;
		
	}

	// GETTERS Y SETTERS

	public Estado getEfecto() {
		return efecto;
	}

	public void setEfecto(Estado efecto) {
		this.efecto = efecto;
	}

	public int getProbabilidadEfecto() {
		return probabilidadEfecto;
	}

	public void setProbabilidadEfecto(int probabilidadEfecto) {
		this.probabilidadEfecto = probabilidadEfecto;
	}



	@Override
	public void ejecutarMovimiento() {
		
	}
}