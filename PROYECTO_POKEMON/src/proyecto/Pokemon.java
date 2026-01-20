package proyecto;


public class Pokemon {
	
	private static final int MAXTIPOS = 2;
    private static final int MAXMOVIMIENTOS = 4;
	
	private int idPokemon;
	private int numPokedex;
	
	private String nombre;
	private String mote;
	
	private Tipo[] tipos;
	
	
	//ESTADÍSTICAS POKEMON (creo la que tendrá el pokemon en sí y las que tendrá el pokemon durante el combate, que pueden variar.)
	private int vitalidadBase;
	private int vitalidad;
	private int ataque;
	private int ataqueBase;
	private int defensa;
	private int defensaBase;
	private int ataqueEspecial;
	private int ataqueEspecialBase;
	private int defensaEspecial;
	private int defensaEspecialBase;
	private int velocidad;
	private int velocidadBase;
	private int estamina;
	private int estaminaBase;
	
	//PROGRESIÓN
	private int nivel;
	private int experiencia;
	
	//COLECCION DE MOVIMIENTOS
	private Movimiento[] movimientos;
	
	//Fertilidad (para la crianza)
	private int fertilidad;
	
	
	//defino el enumerado sexo y creo sexo como un atributo
	public enum Sexo{MACHO, HEMBRA};
	private Sexo sexo;
	
	
	//COLECCION DE TIPOS
	
	//ESTADOS
	private Estado estado;
	
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
	public Tipo[] getTipos() {
		return tipos;
	}
	public void setTipos(Tipo[] tipos) {
		this.tipos = tipos;
	}
	public int getVitalidadBase() {
		return vitalidadBase;
	}
	public void setVitalidadBase(int vitalidadBase) {
		this.vitalidadBase = vitalidadBase;
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
	public int getAtaqueBase() {
		return ataqueBase;
	}
	public void setAtaqueBase(int ataqueBase) {
		this.ataqueBase = ataqueBase;
	}
	public int getDefensa() {
		return defensa;
	}
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	public int getDefensaBase() {
		return defensaBase;
	}
	public void setDefensaBase(int defensaBase) {
		this.defensaBase = defensaBase;
	}
	public int getAtaqueEspecial() {
		return ataqueEspecial;
	}
	public void setAtaqueEspecial(int ataqueEspecial) {
		this.ataqueEspecial = ataqueEspecial;
	}
	public int getAtaqueEspecialBase() {
		return ataqueEspecialBase;
	}
	public void setAtaqueEspecialBase(int ataqueEspecialBase) {
		this.ataqueEspecialBase = ataqueEspecialBase;
	}
	public int getDefensaEspecial() {
		return defensaEspecial;
	}
	public void setDefensaEspecial(int defensaEspecial) {
		this.defensaEspecial = defensaEspecial;
	}
	public int getDefensaEspecialBase() {
		return defensaEspecialBase;
	}
	public void setDefensaEspecialBase(int defensaEspecialBase) {
		this.defensaEspecialBase = defensaEspecialBase;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getVelocidadBase() {
		return velocidadBase;
	}
	public void setVelocidadBase(int velocidadBase) {
		this.velocidadBase = velocidadBase;
	}
	public int getEstamina() {
		return estamina;
	}
	public void setEstamina(int estamina) {
		this.estamina = estamina;
	}
	public int getEstaminaBase() {
		return estaminaBase;
	}
	public void setEstaminaBase(int estaminaBase) {
		this.estaminaBase = estaminaBase;
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
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public static int getMaxtipos() {
		return MAXTIPOS;
	}
	public static int getMaxmovimientos() {
		return MAXMOVIMIENTOS;
	}
	
	//CONSTRUCTORES  (int)(Math.random()*10)+1;
	
	//todos los parámetros
	public Pokemon(int idPokemon, int numPokedex, String nombre, String mote, Tipo[] tipos, int vitalidadBase,
			int vitalidad, int ataque, int ataqueBase, int defensa, int defensaBase, int ataqueEspecial,
			int ataqueEspecialBase, int defensaEspecial, int defensaEspecialBase, int velocidad, int velocidadBase,
			int estamina, int estaminaBase, int nivel, int experiencia, Movimiento[] movimientos, int fertilidad,
			Sexo sexo, Estado estado) {
		super();
		this.idPokemon = idPokemon;
		this.numPokedex = numPokedex;
		this.nombre = nombre;
		this.mote = mote;
		this.tipos = tipos;
		this.vitalidadBase = vitalidadBase;
		this.vitalidad = vitalidad;
		this.ataque = ataque;
		this.ataqueBase = ataqueBase;
		this.defensa = defensa;
		this.defensaBase = defensaBase;
		this.ataqueEspecial = ataqueEspecial;
		this.ataqueEspecialBase = ataqueEspecialBase;
		this.defensaEspecial = defensaEspecial;
		this.defensaEspecialBase = defensaEspecialBase;
		this.velocidad = velocidad;
		this.velocidadBase = velocidadBase;
		this.estamina = estamina;
		this.estaminaBase = estaminaBase;
		this.nivel = nivel;
		this.experiencia = experiencia;
		this.movimientos = movimientos;
		this.fertilidad = fertilidad;
		this.sexo = sexo;
		this.estado = estado;
	}
	
	//por defecto
	public Pokemon() {
		super();
		this.idPokemon = 0;
		this.numPokedex = 0;
		this.nombre = "";
		this.mote = "";
		this.tipos = new Tipo[2];
		this.vitalidadBase = (int)(Math.random()*10)+1;
		this.vitalidad = vitalidadBase;
		this.ataque = ataqueBase;
		this.ataqueBase = (int)(Math.random()*10)+1;
		this.defensa = defensaBase;
		this.defensaBase = (int)(Math.random()*10)+1;
		this.ataqueEspecial = ataqueEspecialBase;
		this.ataqueEspecialBase = (int)(Math.random()*10)+1;
		this.defensaEspecial = defensaEspecialBase;
		this.defensaEspecialBase = (int)(Math.random()*10)+1;
		this.velocidad = velocidadBase;
		this.velocidadBase = (int)(Math.random()*10)+1;
		this.estamina = estaminaBase;
		this.estaminaBase = (int)(Math.random()*10)+1; //de momento lo voy a dejar así, falta comprobar
		this.nivel = 1;
		this.experiencia = 0;
		this.movimientos = new Movimiento[4];
		this.fertilidad = 5;
		this.sexo = Sexo.MACHO;
		this.estado = null;
	}
	//copia
	public Pokemon(Pokemon p) {
		super();
		this.idPokemon = p.idPokemon;
		this.numPokedex = p.numPokedex;
		this.nombre = p.nombre;
		this.mote = p.mote;
		this.tipos = p.tipos;
		this.vitalidadBase = p.vitalidadBase;
		this.vitalidad = p.vitalidad;
		this.ataque = p.ataque;
		this.ataqueBase = p.ataqueBase;
		this.defensa = p.defensa;
		this.defensaBase = p.defensaBase;
		this.ataqueEspecial = p.ataqueEspecial;
		this.ataqueEspecialBase = p.ataqueEspecialBase;
		this.defensaEspecial = p.defensaEspecial;
		this.defensaEspecialBase = p.defensaEspecialBase;
		this.velocidad = p.velocidad;
		this.velocidadBase = p.velocidadBase;
		this.estamina = p.estamina;
		this.estaminaBase = p.estaminaBase;
		this.nivel = p.nivel;
		this.experiencia = p.experiencia;
		this.movimientos = p.movimientos;
		this.fertilidad = p.fertilidad;
		this.sexo = p.sexo;
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
