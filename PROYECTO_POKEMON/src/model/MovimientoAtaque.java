package model;

public class MovimientoAtaque extends Movimiento {
	
	public enum Objetivo{
		USUARIO, RIVAL
	}
		
		private int potencia;
		
		private Estado estado; //algunos movimientos causan cambio de estado tanto en el oponente como en el propio pokemon
		private int probabilidadEstado;
		private Objetivo objetivoEstado;
		
		private Stat stat;
		private int cantidadStat;
		private int probabilidadStat;
		private Objetivo objetivoStat;
		
		
		
		public MovimientoAtaque(int idMovimiento, String nombreMovimiento, Tipo tipoMovimiento, int nivel, 
				int prioridad, int precision, int pp, String desc, MecanicaEspecial mecanicaEspecial, 
				int valorMecanica, int potencia, Estado estado, int probabilidadEstado, Objetivo objetivoEstado,
				Stat stat, int cantidadStat, int probabilidadStat, Objetivo objetivoStat) {
			super(idMovimiento, nombreMovimiento, tipoMovimiento, nivel, prioridad, precision, pp, desc, mecanicaEspecial, valorMecanica);  //aqui tengo que meter atributos
			this.potencia = potencia;
			this.estado = estado;
			this.probabilidadEstado = probabilidadEstado;
			this.objetivoEstado = objetivoEstado;
			this.stat = stat;
			this.cantidadStat = cantidadStat;
			this.probabilidadStat = probabilidadStat;
			this.objetivoStat = objetivoStat;
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

		public Objetivo getObjetivoEstado() {
			return objetivoEstado;
		}

		public void setObjetivoEstado(Objetivo objetivoEstado) {
			this.objetivoEstado = objetivoEstado;
		}

		public Stat getStat() {
			return stat;
		}

		public void setStat(Stat stat) {
			this.stat = stat;
		}

		public int getCantidadStat() {
			return cantidadStat;
		}

		public void setCantidadStat(int cantidadStat) {
			this.cantidadStat = cantidadStat;
		}

		public int getProbabilidadStat() {
			return probabilidadStat;
		}

		public void setProbabilidadStat(int probabilidadStat) {
			this.probabilidadStat = probabilidadStat;
		}

		public Objetivo getObjetivoStat() {
			return objetivoStat;
		}

		public void setObjetivoStat(Objetivo objetivoStat) {
			this.objetivoStat = objetivoStat;
		}





		public void ejecutarMovimiento() {
			// TODO Auto-generated method stub
		}
	

}
