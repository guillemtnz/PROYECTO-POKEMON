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
	public String ejecutarMovimiento(Pokemon atacante, Pokemon defensor) {
	    StringBuilder log = new StringBuilder();

	    // 1. Comprobar PP
	    if (!this.consumirPP()) {
	        return "▶ A " + this.nombreMovimiento + " no le quedan PP...\n";
	    }

	    // Registramos en el log qué movimiento se está usando
	    log.append("▶ ¡").append(atacante.getNombre()).append(" usó ").append(this.nombreMovimiento).append("!\n");

	    Pokemon objetivo = (this.blanco == Blanco.RIVAL) ? defensor : atacante;

	    // 2. Comprobar Precisión
	    if (this.precision != ACIERTA_SIEMPRE && (Math.random() * 100) > this.precision) {
	        log.append("   ¡Pero falló!\n");
	        return log.toString();
	    }
	    
	    // 3. Modificar el Stat correspondiente
	    int mod = this.cantidadModificacion;
	    switch (this.statModificado) {
	        case ATAQUE: objetivo.setModAtaque(objetivo.getModAtaque() + mod); break;
	        case DEFENSA: objetivo.setModDefensa(objetivo.getModDefensa() + mod); break;
	        case ATAQUE_ESPECIAL: objetivo.setModAtaqueEspecial(objetivo.getModAtaqueEspecial() + mod); break;
	        case DEFENSA_ESPECIAL: objetivo.setModDefensaEspecial(objetivo.getModDefensaEspecial() + mod); break;
	        case VELOCIDAD: objetivo.setModVelocidad(objetivo.getModVelocidad() + mod); break;
	        default: break; 
	    }

	    // 4. Registrar en el log el cambio de característica
	    // Añadimos una pequeña distinción visual (subió/bajó) para que quede más "Pokémon"
	    String accion = this.cantidadModificacion > 0 ? "aumentado" : "disminuido";
	    
	    log.append("   ¡El stat ").append(this.statModificado)
	       .append(" de ").append(objetivo.getNombre())
	       .append(" ha ").append(accion).append("!\n");

	    return log.toString();
	}
}