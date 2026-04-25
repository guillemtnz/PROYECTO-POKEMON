package model;

public class MovimientoAtaque extends Movimiento {
	
	public enum Categoria { FISICO, ESPECIAL }
	
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
		this.categoria = categoria; this.potencia = potencia; this.estado = estado;
		this.probabilidadEstado = probabilidadEstado; this.statModificado = statModificado; this.cantidadStat = cantidadStat;
	}

	public Categoria getCategoria() { return categoria; }
	public void setCategoria(Categoria categoria) { this.categoria = categoria; }
	public int getPotencia() { return potencia; }
	public void setPotencia(int potencia) { this.potencia = potencia; }
	public Estado getEstado() { return estado; }
	public void setEstado(Estado estado) { this.estado = estado; }
	public int getProbabilidadEstado() { return probabilidadEstado; }
	public void setProbabilidadEstado(int probabilidadEstado) { this.probabilidadEstado = probabilidadEstado; }
	public Stat getStatModificado() { return statModificado; }
	public void setStatModificado(Stat statModificado) { this.statModificado = statModificado; }
	public int getCantidadStat() { return cantidadStat; }
	public void setCantidadStat(int cantidadStat) { this.cantidadStat = cantidadStat; }

	@Override
	public void ejecutarMovimiento(Pokemon atacante, Pokemon defensor) {
		if (!this.consumirPP()) return;

		if (this.precision != ACIERTA_SIEMPRE && (Math.random() * 100) > this.precision) {
			System.out.println("¡" + atacante.getNombre() + " falló su ataque!");
			return;
		}

		int statAtaque = (this.getCategoria() == Categoria.FISICO) ? atacante.getAtaqueEnCombate() : atacante.getAtaqueEspecialEnCombate();
		int statDefensa = (this.getCategoria() == Categoria.FISICO) ? defensor.getDefensaEnCombate() : defensor.getDefensaEspecialEnCombate();

		double stab = 1.0;
		if (atacante.getTipo1() == this.tipoMovimiento || atacante.getTipo2() == this.tipoMovimiento) stab = 1.5;

		double efectividad = 1.0;
		if (defensor.getTipo1() != null) efectividad *= this.tipoMovimiento.calcularEfectividad(defensor.getTipo1());
		if (defensor.getTipo2() != null) efectividad *= this.tipoMovimiento.calcularEfectividad(defensor.getTipo2());

		double dañoBase = ((((2.0 * atacante.getNivel() / 5.0) + 2.0) * this.potencia * ((double) statAtaque / statDefensa)) / 50.0) + 2.0;
		double variacion = (Math.random() * (1.0 - 0.85)) + 0.85;
		int dañoFinal = (int) (dañoBase * stab * efectividad * variacion);

		defensor.setVitalidadActual(Math.max(0, defensor.getVitalidadActual() - dañoFinal));
		System.out.println("¡" + atacante.getNombre() + " usó " + this.nombreMovimiento + " y causó " + dañoFinal + " PS de daño!");

		if (efectividad > 1.0) System.out.println("¡Es muy eficaz!");
		else if (efectividad < 1.0 && efectividad > 0) System.out.println("No es muy eficaz...");
		else if (efectividad == 0) System.out.println("No afecta a " + defensor.getNombre());
	}
}