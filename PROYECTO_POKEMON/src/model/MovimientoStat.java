package model;

public class MovimientoStat extends Movimiento {
	
	private Stat statModificado; 
	private int cantidadModificacion; 
	
	public MovimientoStat(int idMovimiento, String nombreMovimiento, Tipo tipoMovimiento, int nivel, 
			Integer precision, int pp, int prioridad, Blanco blanco, String efectoEspecial, 
			Stat statModificado, int cantidadModificacion) {
		super(idMovimiento, nombreMovimiento, tipoMovimiento, nivel, precision, pp, prioridad, blanco, efectoEspecial);
		this.statModificado = statModificado; this.cantidadModificacion = cantidadModificacion;
	}
		
	public Stat getStatModificado() { return statModificado; }
	public void setStatModificado(Stat statModificado) { this.statModificado = statModificado; }
	public int getCantidadModificacion() { return cantidadModificacion; }
	public void setCantidadModificacion(int cantidadModificacion) { this.cantidadModificacion = cantidadModificacion; }

	@Override
	public void ejecutarMovimiento(Pokemon atacante, Pokemon defensor) {
		if (!this.consumirPP()) return;

		Pokemon objetivo = (this.blanco == Blanco.RIVAL) ? defensor : atacante;

		if (this.precision != ACIERTA_SIEMPRE && (Math.random() * 100) > this.precision) {
			System.out.println("¡" + atacante.getNombre() + " falló!");
			return;
		}
		
		int mod = this.cantidadModificacion;
		switch (this.statModificado) {
			case ATAQUE: objetivo.setModAtaque(objetivo.getModAtaque() + mod); break;
			case DEFENSA: objetivo.setModDefensa(objetivo.getModDefensa() + mod); break;
			case ATAQUE_ESPECIAL: objetivo.setModAtaqueEspecial(objetivo.getModAtaqueEspecial() + mod); break;
			case DEFENSA_ESPECIAL: objetivo.setModDefensaEspecial(objetivo.getModDefensaEspecial() + mod); break;
			case VELOCIDAD: objetivo.setModVelocidad(objetivo.getModVelocidad() + mod); break;
			default: break; 
		}

		String accion = this.cantidadModificacion > 0 ? "aumentado" : "disminuido";
		System.out.println("¡El stat " + this.statModificado + " de " + objetivo.getNombre() + " ha " + accion + "!");
	}
}