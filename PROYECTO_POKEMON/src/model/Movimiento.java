package model;

public abstract class Movimiento {
	
	public static final int ACIERTA_SIEMPRE = 999;

	public enum Stat {
		ATAQUE, ATAQUE_ESPECIAL, DEFENSA, DEFENSA_ESPECIAL, VELOCIDAD, PRECISION, EVASION
	}

	public enum Blanco {
		RIVAL, USUARIO
	}

	protected int idMovimiento;
	protected String nombreMovimiento;
	protected Tipo tipoMovimiento; 
	protected int nivel;
	protected int precision; 
	protected int pp;
	protected int prioridad;
	protected Blanco blanco;
	protected String efectoEspecial; 
	
	public Movimiento() {
		this.idMovimiento = 0; this.nombreMovimiento = ""; this.tipoMovimiento = null;
		this.nivel = 1; this.precision = 100; this.pp = 40;
		this.prioridad = 0; this.blanco = Blanco.RIVAL; this.efectoEspecial = null;
	}

	public Movimiento(int idMovimiento, String nombreMovimiento, Tipo tipoMovimiento, int nivel, 
			int precision, int pp, int prioridad, Blanco blanco, String efectoEspecial) {
		this.idMovimiento = idMovimiento; this.nombreMovimiento = nombreMovimiento; this.tipoMovimiento = tipoMovimiento;
		this.nivel = nivel; this.precision = precision; this.pp = pp;
		this.prioridad = prioridad; this.blanco = blanco; this.efectoEspecial = efectoEspecial;
	}
	
	public Movimiento(Movimiento m) {
		this.idMovimiento = m.idMovimiento; this.nombreMovimiento = m.nombreMovimiento; this.tipoMovimiento = m.tipoMovimiento;
		this.nivel = m.nivel; this.precision = m.precision; this.pp = m.pp;
		this.prioridad = m.prioridad; this.blanco = m.blanco; this.efectoEspecial = m.efectoEspecial;
	}

	// GETTERS Y SETTERS ORIGINALES
	public int getIdMovimiento() { return idMovimiento; }
	public void setIdMovimiento(int idMovimiento) { this.idMovimiento = idMovimiento; }
	public String getNombreMovimiento() { return nombreMovimiento; }
	public void setNombreMovimiento(String nombreMovimiento) { this.nombreMovimiento = nombreMovimiento; }
	public Tipo getTipoMovimiento() { return tipoMovimiento; }
	public void setTipoMovimiento(Tipo tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }
	public int getNivel() { return nivel; }
	public void setNivel(int nivel) { this.nivel = nivel; }
	public int getPrecision() { return precision; }
	public void setPrecision(int precision) { this.precision = precision; }
	public int getPp() { return pp; }
	public void setPp(int pp) { this.pp = pp; }
	public int getPrioridad() { return prioridad; }
	public void setPrioridad(int prioridad) { this.prioridad = prioridad; }
	public Blanco getBlanco() { return blanco; }
	public void setBlanco(Blanco blanco) { this.blanco = blanco; }
	public String getEfectoEspecial() { return efectoEspecial; }
	public void setEfectoEspecial(String efectoEspecial) { this.efectoEspecial = efectoEspecial; }
	
	// --- CONTROL DE PP ---
	public boolean consumirPP() {
		if (this.pp > 0) {
			this.pp--;
			return true;
		}
		System.out.println("¡A " + this.nombreMovimiento + " no le quedan PP!");
		return false;
	}

	public abstract void ejecutarMovimiento(Pokemon atacante, Pokemon defensor);
}