package model;

public class MovimientoStat extends Movimiento {
	
	public enum Stat {
		ATAQUE, ATAQUE_ESPECIAL, DEFENSA, DEFENSA_ESPECIAL, VELOCIDAD
	}
		
	public enum Objetivo { USUARIO, RIVAL }
		
	private Stat stat; //stat a cambiar
	private int cantidad;
	private Objetivo objetivo;
		
	public MovimientoStat(int idMovimiento, String nombreMovimiento, Tipo tipoMovimiento, int nivel, 
									int prioridad, int precision, int pp, String desc, Stat stat, int cantidad, Objetivo objetivo, MecanicaEspecial mecanicaEspecial, int valorMecanica) {
		super(idMovimiento, nombreMovimiento, tipoMovimiento, nivel, prioridad, precision, pp, desc, mecanicaEspecial, valorMecanica);
		this.stat = stat;
		this.cantidad = cantidad;
		this.objetivo = objetivo;
	}
		
		

		
	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat mejora) {
		this.stat = mejora;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
		

	public Objetivo getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(Objetivo objetivo) {
		this.objetivo = objetivo;
	}




	public void ejecutarMovimiento() {
		// TODO Auto-generated method stub
	}
		
	
}
