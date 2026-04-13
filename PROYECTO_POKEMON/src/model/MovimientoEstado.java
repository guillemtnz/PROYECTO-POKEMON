package model;

public class MovimientoEstado extends Movimiento {
		
		private Estado estado;
		private int numTurnos;
		
		public MovimientoEstado(int idMovimiento, String nombreMovimiento, Tipo tipoMovimiento, int nivel, 
									int prioridad, int precision, int pp, String desc, Estado estado, int numTurnos, MecanicaEspecial mecanicaEspecial, int valorMecanica) {
			super(idMovimiento, nombreMovimiento, tipoMovimiento, nivel, prioridad, precision, pp, desc, mecanicaEspecial, valorMecanica);
			this.estado = estado;
			this.numTurnos = numTurnos;
		}

		public Estado getEstado() {
			return estado;
		}

		public void setEstado(Estado estado) {
			this.estado = estado;
		}

		public int getNumTurnos() {
			return numTurnos;
		}


		public void setNumTurnos(int numTurnos) {
			this.numTurnos = numTurnos;
		}

		public void ejecutarMovimiento() {
			// TODO Auto-generated method stub	
		}
	
	
}
