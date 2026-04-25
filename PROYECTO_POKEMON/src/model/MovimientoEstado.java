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
	public String ejecutarMovimiento(Pokemon atacante, Pokemon defensor) {
	    StringBuilder log = new StringBuilder();

	    // 1. Comprobar PP
	    if (!this.consumirPP()) {
	        return "▶ A " + this.nombreMovimiento + " no le quedan PP...\n";
	    }

	    // Registramos que se ha usado el movimiento
	    log.append("▶ ¡").append(atacante.getNombre()).append(" usó ").append(this.nombreMovimiento).append("!\n");

	    Pokemon objetivo = (this.blanco == Blanco.RIVAL) ? defensor : atacante;

	    // 2. Comprobar Precisión
	    if (this.precision != ACIERTA_SIEMPRE && (Math.random() * 100) > this.precision) {
	        log.append("   ¡Pero falló!\n");
	        return log.toString();
	    }

	    // 3. Comprobar si ya tiene un estado (y no es ni confuso ni enamorado)
	    if (objetivo.getEstado() != null && this.efecto != Estado.CONFUSO && this.efecto != Estado.ENAMORADO) {
	        log.append("   ¡Pero ").append(objetivo.getNombre()).append(" ya tiene un problema de estado!\n");
	        return log.toString();
	    }

	    // 4. Aplicar el estado si entra en la probabilidad
	    if ((Math.random() * 100) <= this.probabilidadEfecto) {
	        objetivo.setEstado(this.efecto);
	        log.append("   ¡").append(objetivo.getNombre()).append(" ahora está ").append(this.efecto).append("!\n");
	    } else {
	        log.append("   Pero no tuvo ningún efecto...\n");
	    }

	    return log.toString();
	}
}