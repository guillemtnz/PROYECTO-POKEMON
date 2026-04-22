package model;

public class MovimientoStat extends Movimiento {
	
	private Stat statModificado; // stat a cambiar 
	private int cantidadModificacion; // Ej: 2 para subir dos niveles, -1 para bajar uno
	

	
	// CONSTRUCTOR CON TODOS LOS ATRIBUTOS
	public MovimientoStat(int idMovimiento, String nombreMovimiento, Tipo tipoMovimiento, int nivel, 
			Integer precision, int pp, int prioridad, Blanco blanco, String efectoEspecial, 
			Stat statModificado, int cantidadModificacion) {
		super(idMovimiento, nombreMovimiento, tipoMovimiento, nivel, precision, pp, prioridad, blanco, efectoEspecial);

		this.statModificado = statModificado;
		this.cantidadModificacion = cantidadModificacion;
	}
		
	// --- GETTERS Y SETTERS --- 

	public Stat getStatModificado() {
		return statModificado;
	}

	public void setStatModificado(Stat statModificado) {
		this.statModificado = statModificado;
	}

	public int getCantidadModificacion() {
		return cantidadModificacion;
	}

	public void setCantidadModificacion(int cantidadModificacion) {
		this.cantidadModificacion = cantidadModificacion;
	}


	@Override
	public void ejecutarMovimiento() {
	}
}