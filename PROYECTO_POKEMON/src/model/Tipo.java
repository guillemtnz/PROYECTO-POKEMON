package model;

public enum Tipo {
	ACERO,
	AGUA,
	BICHO,
	DRAGON,
	ELECTRICO,
	FANTASMA,
	FUEGO,
	HADA,
	HIELO,
	LUCHA,
	NORMAL,
	PLANTA,
	PSIQUICO,
	ROCA,
	SINIESTRO, 
	TIERRA,
	VENENO,
	VOLADOR;
	
	//Tabla de efectividades ATACANTE X DEFENSOR (en el orden que se ha enumerado) 
	//Static porque es la misma para TODOS los pokemon y Final porque no se modifica
	private static final double[][] EFECTIVIDAD = {
		//En el mismo orden que el enumerado	
		// ACE  AGU  BIC  DRA  ELE  FAN  FUE  HAD  HIE  LUC  NOR  PLA  PSI  ROC  SIN  TIE  VEN  VOL
	      {0.5, 0.5,  1,   1,  0.5,  1,  0.5,  1,  1.5,  1,   1,   1,   1,  1.5,  1,   1,   1,   1},  // ACERO
	      { 1,  0.5,  1,  0.5,  1,   1,  1.5,  1,   1,   1,   1,  0.5,  1,  1.5,  1,  1.5,  1,   1},  // AGUA
	      {0.5,   1,  1,   1,   1,  0.5, 0.5, 0.5,  1,  0.5,  1,  1.5, 1.5,  1,  1.5,  1,  0.5, 0.5}, // BICHO
	      {0.5,   1,  1,  1.5,  1,   1,   1,   0,   1,   1,   1,   1,   1,   1,   1,   1,   1,   1},  // DRAGON
	      { 1,  1.5,  1,  0.5, 0.5,  1,   1,   1,   1,   1,   1,  0.5,  1,   1,   1,   0,   1,  1.5}, // ELECTRICO
	      { 1,    1,  1,   1,   1,  1.5,  1,   1,   1,   1,   0,   1,  1.5,  1,  0.5,  1,   1,   1},  // FANTASMA
	      {1.5, 0.5, 1.5, 0.5,  1,   1,  0.5,  1,  1.5,  1,   1,  1.5,  1,  0.5,  1,   1,   1,   1},  // FUEGO
	      {0.5,   1,  1,  1.5,  1,   1,  0.5,  1,   1,  0.5,  1,   1,   1,   1,  1.5,  1,  0.5,  1},  // HADA
	      {0.5, 0.5,  1,  1.5,  1,   1,  0.5,  1,  0.5, 1.5,  1,  1.5,  1,   1,   1,  1.5,  1,  1.5}, // HIELO
	      {1.5,   1, 0.5,  1,   1,   0,   1,  0.5, 1.5,  1,  1.5,  1,  0.5, 1.5, 1.5,  1,  0.5, 0.5}, // LUCHA
	      {0.5,   1,  1,   1,   1,   0,   1,   1,   1,   1,   1,   1,   1,  0.5,  1,   1,   1,   1},  // NORMAL
	      {0.5, 1.5, 0.5, 0.5,  1,   1,  0.5,  1,   1,   1,   1,  0.5,  1,  1.5,  1,  1.5, 0.5, 0.5}, // PLANTA
          {0.5,   1,  1,   1,   1,   1,   1,   1,   1,  1.5,  1,   1,  0.5,  1,   0,   1,  1.5,  1},  // PSIQUICO
	      {0.5,   1, 1.5,  1,   1,   1,  1.5,  1,  1.5, 0.5,  1,   1,   1,   1,   1,  0.5,  1,  1.5}, // ROCA
	      { 1,    1,  1,   1,   1,  1.5,  1,  0.5,  1,  0.5,  1,   1,  1.5,  1,  0.5,  1,   1,   1},  // SINIESTRO
	      { 1,    1, 0.5,  1,  1.5,  1,  1.5,  1,   1,   1,   1,  0.5,  1,  1.5,  1,   1,  1.5,  0},  // TIERRA
	      { 0,    1,  1,   1,   1,  0.5,  1,  1.5,  1,   1,   1,  1.5,  1,  0.5,  1,  0.5, 0.5,  1},  // VENENO
	      {0.5,   1, 1.5,  1,  0.5,  1,   1,   1,   1,  1.5,  1,  1.5,  1,  0.5,  1,   1,   1,   1},   // VOLADOR			
	};
	
	

	public double calcularEfectividad(Tipo defensor) {
	    return EFECTIVIDAD[this.ordinal()][defensor.ordinal()];
	}
	
		//Clase interna ataque
		public static class Ataque {
			
			//Atributos privados(te miro y te encapsulo)
			private String nombre;
			private Tipo tipoPokemon;
			private int potencia;
			
			public Ataque(String nombre, Tipo tipoP, int potencia) {
				this.nombre = nombre;
				this.tipoPokemon = tipoP;
				this.potencia = potencia;
			}
			
			public String getNombre() {
				return nombre;
			}
			
			public Tipo getTipo() {
				return tipoPokemon;
			}
			
			public int getPotencia() {
				return potencia;
			}
		}
}

