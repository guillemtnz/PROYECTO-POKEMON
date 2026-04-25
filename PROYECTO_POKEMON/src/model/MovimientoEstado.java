package model;

public class MovimientoEstado extends Movimiento {
		
	private Estado efecto; 
	private int probabilidadEfecto;
	
	public MovimientoEstado(int idMovimiento, String nombreMovimiento, Tipo tipoMovimiento, int nivel, 
			int precision, int pp, int prioridad, Blanco blanco, String efectoEspecial, 
			Estado efecto, int probabilidadEfecto) {
		
		super(idMovimiento, nombreMovimiento, tipoMovimiento, nivel, precision, pp, prioridad, blanco, efectoEspecial);
		this.efecto = efecto; this.probabilidadEfecto = probabilidadEfecto;
	}

	public Estado getEfecto() { return efecto; }
	public void setEfecto(Estado efecto) { this.efecto = efecto; }
	public int getProbabilidadEfecto() { return probabilidadEfecto; }
	public void setProbabilidadEfecto(int probabilidadEfecto) { this.probabilidadEfecto = probabilidadEfecto; }

	@Override
	public void ejecutarMovimiento(Pokemon atacante, Pokemon defensor) {
		if (!this.consumirPP()) return;

		Pokemon objetivo = (this.blanco == Blanco.RIVAL) ? defensor : atacante;

		if (this.precision != ACIERTA_SIEMPRE && (Math.random() * 100) > this.precision) {
			System.out.println("¡" + atacante.getNombre() + " falló su movimiento!");
			return;
		}

		if (objetivo.getEstado() != null && this.efecto != Estado.CONFUSO && this.efecto != Estado.ENAMORADO) {
			System.out.println("¡" + objetivo.getNombre() + " ya tiene un problema de estado!");
			return;
		}

		if ((Math.random() * 100) <= this.probabilidadEfecto) {
			objetivo.setEstado(this.efecto);
			System.out.println("¡" + objetivo.getNombre() + " ahora está " + this.efecto + "!");
		} else {
			System.out.println("El movimiento no tuvo efecto esta vez.");
		}
	}
}