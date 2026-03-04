package model;

public abstract class Movimiento {
	
	public enum TipoMovimiento {
		ATAQUE, ESTADO, MEJORA
	}
	
	public enum TipoPokemon {
		ACERO, AGUA, BICHO, DRAGON, ELECTRICO, FANTASMA, FUEGO, HADA,
		HIELO, LUCHA, NORMAL, PLANTA, PSIQUICO, ROCA, SINIESTRO, 
		TIERRA, VENENO, VOLADOR
	}
	
	public enum Estado {
		PARALIZADO, QUEMADO, ENVENENADO, GRAVEMENTE_ENVENENADO, DORMIDO, SOMNOLIENTO,
		CONGELADO, HELADO,POKERUS, CONFUSO, ENAMORADO, ATRAPADO, MALDITO, DRENADORAS,
		CANTO_MORTAL, CENTRO_DE_ATENCION, AMEDRENTADO, DEBILITADO
	}
	
	public enum Mejora {
		ATAQUE, ATAQUE_ESPECIAL, DEFENSA, DEFENSA_ESPECIAL
	}


	protected String nombre;
	protected TipoMovimiento tipoMovimiento;
	protected int costePP;
	
	public Movimiento(String nombre, TipoMovimiento tipoMovimiento) {
		this.nombre = nombre;
		this.tipoMovimiento = tipoMovimiento;
		this.costePP = 1; //Todos los movimientos, ya sean de ataque mejora o estado cuestan un punto de poder
	}

	public String getNombre() {
		return nombre;
	}

	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}

	public int getCostePP() {
		return costePP;
	}
	
	public abstract void ejecutarMovimiento();

	
	//Clase interna Ataque
	public class MovimientoAtaque extends Movimiento {
		
		private int potencia;
		private TipoPokemon tipo;
		
		public MovimientoAtaque(String nombre, int potencia, TipoPokemon tipo) {
			super(nombre, TipoMovimiento.ATAQUE);
			this.potencia = potencia;
			this.tipo = tipo;
		}
		
		public int getPotencia() {
			return potencia;
		}

		public TipoPokemon getTipo() {
			return tipo;
		}

		@Override
		public void ejecutarMovimiento() {
			// TODO Auto-generated method stub
		}
	}
	
	//Clase interna Estado
	public class MovimientoEstado extends Movimiento {
		
		private Estado estado;
		private int turnos;
		
		public MovimientoEstado(String nombre, Estado estado, int turnos) {
			super(nombre, TipoMovimiento.ESTADO);
			this.estado = estado;
			this.turnos = turnos;
		}

		public Estado getEstado() {
			return estado;
		}

		public int getTurnos() {
			return turnos;
		}

		@Override
		public void ejecutarMovimiento() {
			// TODO Auto-generated method stub	
		}
	}
	
	//Clase interna Mejora
	public class MovimientoMejora extends Movimiento {
		
		private Mejora mejora;
		private int turnos;
		
		public MovimientoMejora(String nombre, Mejora mejora, int turnos) {
			super(nombre, TipoMovimiento.MEJORA);
			this.mejora = mejora;
			this.turnos = turnos;
		}
		
		public Mejora getMejora() {
			return mejora;
		}

		public int getTurnos() {
			return turnos;
		}

		@Override
		public void ejecutarMovimiento() {
			// TODO Auto-generated method stub
		}
		
	}
}