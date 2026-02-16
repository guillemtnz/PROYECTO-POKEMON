package proyecto;


public class Pokemon {
	
	
	private int idPokemon;
	private int numPokedex;
	
	//idEntrenador va aqui
	
	private String nombre;
	private String mote;

	//ESTADÍSTICAS POKEMON 
	
	private int vitalidad;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int velocidad;

	
	//PROGRESIÓN
	private int nivel;
	private int experiencia;
	
	//COLECCION DE MOVIMIENTOS
	private Movimiento[] movimientos = new Movimiento[4]; //4 movimientos como máximo
	
	//FERTILIDAD
	private int fertilidad;
	
	
	//SEXO
	public enum Sexo{MACHO, HEMBRA};
	private Sexo sexo;
	
	
	//COLECCION DE TIPOS
	private Tipo tipo1;
	private Tipo tipo2;
	
	//ESTADOS
	private Estado estado;
	
	//UBICACION
	
	//OBJETO
	
	// SETTERS Y GETTERS
	
	public int getIdPokemon() {
		return idPokemon;
	}


	public void setIdPokemon(int idPokemon) {
		this.idPokemon = idPokemon;
	}


	public int getNumPokedex() {
		return numPokedex;
	}


	public void setNumPokedex(int numPokedex) {
		this.numPokedex = numPokedex;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getMote() {
		return mote;
	}


	public void setMote(String mote) {
		this.mote = mote;
	}


	public int getVitalidad() {
		return vitalidad;
	}


	public void setVitalidad(int vitalidad) {
		this.vitalidad = vitalidad;
	}


	public int getAtaque() {
		return ataque;
	}


	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}


	public int getDefensa() {
		return defensa;
	}


	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}


	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}


	public void setAtaqueEspecial(int ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}


	public int getDefensaEspecial() {
		return defensaEspecial;
	}


	public void setDefensaEspecial(int defensaEspecial) {
		this.defensaEspecial = defensaEspecial;
	}


	public int getVelocidad() {
		return velocidad;
	}


	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}


	public int getNivel() {
		return nivel;
	}


	public void setNivel(int nivel) {
		this.nivel = nivel;
	}


	public int getExperiencia() {
		return experiencia;
	}


	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}


	public Movimiento[] getMovimientos() {
		return movimientos;
	}


	public void setMovimientos(Movimiento[] movimientos) {
		this.movimientos = movimientos;
	}


	public int getFertilidad() {
		return fertilidad;
	}


	public void setFertilidad(int fertilidad) {
		this.fertilidad = fertilidad;
	}


	public Sexo getSexo() {
		return sexo;
	}


	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}


	public Tipo getTipo1() {
		return tipo1;
	}


	public void setTipo1(Tipo tipo1) {
		this.tipo1 = tipo1;
	}


	public Tipo getTipo2() {
		return tipo2;
	}


	public void setTipo2(Tipo tipo2) {
		this.tipo2 = tipo2;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}


	
	//CONSTRUCTORES  (int)(Math.random()*10)+1;
	
	//todos los parámetros
	
	public Pokemon(int idPokemon, int numPokedex, String nombre, String mote, int vitalidad, int ataque, int defensa,
			int ataqueEspecial, int defensaEspecial, int velocidad, int nivel, int experiencia,
			Movimiento[] movimientos, int fertilidad, Sexo sexo, Tipo tipo1, Tipo tipo2, Estado estado) {
		super();
		this.idPokemon = idPokemon;
		this.numPokedex = numPokedex;
		this.nombre = nombre;
		this.mote = mote;
		this.vitalidad = vitalidad;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.nivel = nivel;
		this.experiencia = experiencia;
		this.movimientos = movimientos;
		this.fertilidad = fertilidad;
		this.sexo = sexo;
		this.tipo1 = tipo1;
		this.tipo2 = tipo2;
		this.estado = estado;
	}

	
	//por defecto
	public Pokemon() {
		super();
		this.idPokemon = 0;
		this.numPokedex = 0;
		this.nombre = "";
		this.mote = "";
		this.vitalidad = (int)(Math.random()*10)+1;
		this.ataque = (int)(Math.random()*10)+1;
		this.defensa = (int)(Math.random()*10)+1;
		this.ataqueEspecial = (int)(Math.random()*10)+1;
		this.defensaEspecial = (int)(Math.random()*10)+1;
		this.velocidad = (int)(Math.random()*10)+1;
		this.nivel = 1;
		this.experiencia = 0;
		this.movimientos = null;  //esto no sé si está bien
		this.fertilidad = 5;
		this.sexo = Sexo.MACHO;
		this.tipo1 = null;
		this.tipo2 = null;
		this.estado = null;
	}

	//copia
	
	public Pokemon(Pokemon p) {
		super();
		this.idPokemon = p.idPokemon;
		this.numPokedex = p.numPokedex;
		this.nombre = p.nombre;
		this.mote = p.mote;
		this.vitalidad = p.vitalidad;
		this.ataque = p.ataque;
		this.defensa = p.defensa;
		this.ataqueEspecial = p.ataqueEspecial;
		this.defensaEspecial = p.defensaEspecial;
		this.velocidad = p.velocidad;
		this.nivel = p.nivel;
		this.experiencia = p.experiencia;
		this.movimientos = p.movimientos;
		this.fertilidad = p.fertilidad;
		this.sexo = p.sexo;
		this.tipo1 = p.tipo1;
		this.tipo2 = p.tipo2;
		this.estado = p.estado;
	}

	//el que más se va a usar (AÚN POR HACER)
	
	
	

	// Método que mejora las stats al subir de nivel
	public void subirNivel(){	
		
		this.experiencia -= 10 * this.nivel;
		
		this.vitalidad += (int)(Math.random() * 5) + 1;
		this.ataque += (int)(Math.random() * 5) + 1;
		this.defensa += (int)(Math.random() * 5) + 1;
		this.ataqueEspecial += (int)(Math.random() * 5) + 1;
		this.defensaEspecial += (int)(Math.random() * 5) + 1;
		this.velocidad += (int)(Math.random() * 5) + 1;
		
		this.nivel++;
	}
	
	
	


	public void atacar(Pokemon objetivo, Movimiento movimiento) {
		
	}
	
	public String comprobarEfectividad() {
		String efectividad = "NEUTRO";
		
		return efectividad;
	}
	
	public void descansar() {
		
	}
	
	public void aprenderMovimiento() {
		
	}

}
