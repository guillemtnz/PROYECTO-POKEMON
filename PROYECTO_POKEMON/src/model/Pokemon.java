package model;

public class Pokemon {
	
	private int idPokemon;
	private int numPokedex;
	private int idEntrenador;
	
	private String nombre;
	private String mote;

	// ESTADÍSTICAS POKEMON 
	private int vitalidad;
	private int vitalidadActual;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int velocidad;

	// --- MODIFICADORES TEMPORALES DE COMBATE (FASES DE -6 A +6) ---
	private int modAtaque = 0;
	private int modDefensa = 0;
	private int modAtaqueEspecial = 0;
	private int modDefensaEspecial = 0;
	private int modVelocidad = 0;

	// PROGRESIÓN
	private int nivel;
	private int experiencia;
	
	// COLECCION DE MOVIMIENTOS
	private Movimiento[] movimientos = new Movimiento[4];
	
	private int fertilidad;
	public enum Sexo{MACHO, HEMBRA};
	private Sexo sexo;
	
	private Tipo tipo1;
	private Tipo tipo2;
	private Estado estado;
	private String ubicacion;
	private Objeto objeto;

	public Pokemon() {
		super();
		this.idPokemon = 0;
		this.numPokedex = 0;
		this.idEntrenador = 0;
		this.nombre = "";
		this.mote = "";
		this.vitalidad = (int)(Math.random() * 10) + 1;
		this.vitalidadActual = this.vitalidad;
		this.ataque = (int)(Math.random() * 10) + 1;
		this.defensa = (int)(Math.random() * 10) + 1;
		this.ataqueEspecial = (int)(Math.random() * 10) + 1;
		this.defensaEspecial = (int)(Math.random() * 10) + 1;
		this.velocidad = (int)(Math.random() * 10) + 1;
		this.nivel = 1;
		this.experiencia = 0;
		this.movimientos = new Movimiento[4];
		this.fertilidad = 5;
		this.sexo = Sexo.MACHO; 
		this.tipo1 = null;
		this.tipo2 = null;
		this.estado = null;
		this.ubicacion = "CAJA";
		this.objeto = null;
	}
	
	public Pokemon(int idPokemon, int numPokedex, int idEntrenador, String nombre, String mote, int vitalidad,
			int vitalidadActual, int ataque, int defensa, int ataqueEspecial, int defensaEspecial, int velocidad,
			int nivel, int experiencia, Movimiento[] movimientos, int fertilidad, Sexo sexo, Tipo tipo1, Tipo tipo2,
			Estado estado, String ubicacion, Objeto objeto) {
		super();
		this.idPokemon = idPokemon; this.numPokedex = numPokedex; this.idEntrenador = idEntrenador;
		this.nombre = nombre; this.mote = mote;
		this.vitalidad = vitalidad; this.vitalidadActual = vitalidadActual;
		this.ataque = ataque; this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial; this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad; this.nivel = nivel; this.experiencia = experiencia;
		this.movimientos = movimientos; this.fertilidad = fertilidad; this.sexo = sexo;
		this.tipo1 = tipo1; this.tipo2 = tipo2; this.estado = estado;
		this.ubicacion = ubicacion; this.objeto = objeto;
	}

	// GETTERS Y SETTERS BASE
	public int getIdPokemon() { return idPokemon; }
	public void setIdPokemon(int idPokemon) { this.idPokemon = idPokemon; }
	public int getNumPokedex() { return numPokedex; }
	public void setNumPokedex(int numPokedex) { this.numPokedex = numPokedex; }
	public int getIdEntrenador() { return idEntrenador; }
	public void setIdEntrenador(int idEntrenador) { this.idEntrenador = idEntrenador; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public String getMote() { return mote; }
	public void setMote(String mote) { this.mote = mote; }
	public int getVitalidad() { return vitalidad; }
	public void setVitalidad(int vitalidad) { this.vitalidad = vitalidad; }
	public int getVitalidadActual() { return vitalidadActual; }
	public void setVitalidadActual(int vitalidadActual) { this.vitalidadActual = vitalidadActual; }
	public int getAtaque() { return ataque; }
	public void setAtaque(int ataque) { this.ataque = ataque; }
	public int getDefensa() { return defensa; }
	public void setDefensa(int defensa) { this.defensa = defensa; }
	public int getAtaqueEspecial() { return ataqueEspecial; }
	public void setAtaqueEspecial(int ataqueEspecial) { this.ataqueEspecial = ataqueEspecial; }
	public int getDefensaEspecial() { return defensaEspecial; }
	public void setDefensaEspecial(int defensaEspecial) { this.defensaEspecial = defensaEspecial; }
	public int getVelocidad() { return velocidad; }
	public void setVelocidad(int velocidad) { this.velocidad = velocidad; }
	public int getNivel() { return nivel; }
	public void setNivel(int nivel) { this.nivel = nivel; }
	public int getExperiencia() { return experiencia; }
	public void setExperiencia(int experiencia) { this.experiencia = experiencia; }
	public Movimiento[] getMovimientos() { return movimientos; }
	public void setMovimientos(Movimiento[] movimientos) { this.movimientos = movimientos; }
	public int getFertilidad() { return fertilidad; }
	public void setFertilidad(int fertilidad) { this.fertilidad = fertilidad; }
	public Sexo getSexo() { return sexo; }
	public void setSexo(Sexo sexo) { this.sexo = sexo; }
	public Tipo getTipo1() { return tipo1; }
	public void setTipo1(Tipo tipo1) { this.tipo1 = tipo1; }
	public Tipo getTipo2() { return tipo2; }
	public void setTipo2(Tipo tipo2) { this.tipo2 = tipo2; }
	public Estado getEstado() { return estado; }
	public void setEstado(Estado estado) { this.estado = estado; }
	public String getUbicacion() { return ubicacion; }
	public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
	public Objeto getObjeto() { return objeto; }
	public void setObjeto(Objeto objeto) { this.objeto = objeto; }

	// --- MÉTODOS DE MODIFICADORES TEMPORALES ---
	public void resetearModificadores() {
		this.modAtaque = 0; this.modDefensa = 0;
		this.modAtaqueEspecial = 0; this.modDefensaEspecial = 0;
		this.modVelocidad = 0;
	}

	public void setModAtaque(int m) { this.modAtaque = Math.max(-6, Math.min(6, m)); }
	public void setModDefensa(int m) { this.modDefensa = Math.max(-6, Math.min(6, m)); }
	public void setModAtaqueEspecial(int m) { this.modAtaqueEspecial = Math.max(-6, Math.min(6, m)); }
	public void setModDefensaEspecial(int m) { this.modDefensaEspecial = Math.max(-6, Math.min(6, m)); }
	public void setModVelocidad(int m) { this.modVelocidad = Math.max(-6, Math.min(6, m)); }
	
	public int getModAtaque() { return this.modAtaque; }
	public int getModDefensa() { return this.modDefensa; }
	public int getModAtaqueEspecial() { return this.modAtaqueEspecial; }
	public int getModDefensaEspecial() { return this.modDefensaEspecial; }
	public int getModVelocidad() { return this.modVelocidad; }

	public int getAtaqueEnCombate() { 
        int baseConObjeto = this.ataque + (objeto != null ? objeto.getAtaque() : 0);
        return calcularStat(Math.max(1, baseConObjeto), this.modAtaque); 
    }

    public int getDefensaEnCombate() { 
        int baseConObjeto = this.defensa + (objeto != null ? objeto.getDefensa() : 0);
        return calcularStat(Math.max(1, baseConObjeto), this.modDefensa); 
    }

    public int getAtaqueEspecialEnCombate() { 
        int baseConObjeto = this.ataqueEspecial + (objeto != null ? objeto.getAtaEsp() : 0);
        return calcularStat(Math.max(1, baseConObjeto), this.modAtaqueEspecial); 
    }

    public int getDefensaEspecialEnCombate() { 
        int baseConObjeto = this.defensaEspecial + (objeto != null ? objeto.getDefensa() : 0);
        return calcularStat(Math.max(1, baseConObjeto), this.modDefensaEspecial); 
    }

    public int getVelocidadEnCombate() { 
        int baseConObjeto = this.velocidad + (objeto != null ? objeto.getVelocidad() : 0);
        return calcularStat(Math.max(1, baseConObjeto), this.modVelocidad); 
    }

	private int calcularStat(int base, int modificador) {
		if (modificador == 0) return base;
		double multiplicador = (modificador > 0) ? ((2.0 + modificador) / 2.0) : (2.0 / (2.0 + Math.abs(modificador)));
		return (int) (base * multiplicador);
	}

	// --- MÉTODOS DE JUEGO ---
	public void ganarExperiencia(int expGanada) {
		this.experiencia += expGanada;
		System.out.println(this.getMoteOCualquierNombre() + " ha ganado " + expGanada + " puntos de experiencia.");
		while (this.experiencia >= (10 * this.nivel) && this.nivel < 100) {
			subirNivel();
		}
	}
	
	public void subirNivel(){	
		this.experiencia -= 10 * this.nivel;
		this.vitalidad += (int)(Math.random() * 5) + 1;
		this.ataque += (int)(Math.random() * 5) + 1;
		this.defensa += (int)(Math.random() * 5) + 1;
		this.ataqueEspecial += (int)(Math.random() * 5) + 1;
		this.defensaEspecial += (int)(Math.random() * 5) + 1;
		this.velocidad += (int)(Math.random() * 5) + 1;
		this.nivel++;
		this.vitalidadActual = this.vitalidad; 
		
		System.out.println("¡" + this.getMoteOCualquierNombre() + " ha subido al nivel " + this.nivel + "!");
		if (this.nivel % 5 == 0) {
			System.out.println("¡A " + this.getMoteOCualquierNombre() + " le gustaría aprender un nuevo movimiento!");
		}
	}

	public void atacar(Pokemon objetivo, Movimiento mov) {
		if (this.vitalidadActual <= 0) {
			System.out.println(this.getMoteOCualquierNombre() + " está debilitado y no puede atacar.");
			return;
		}
		if (objetivo.getVitalidadActual() <= 0) {
			System.out.println("¡Pero " + objetivo.getMoteOCualquierNombre() + " ya está debilitado!");
			return;
		}
		mov.ejecutarMovimiento(this, objetivo);
	}
	
	public String comprobarEfectividad(Tipo tipoAtaque) {
		double modificador = 1.0;
		if (this.tipo1 != null) modificador *= tipoAtaque.calcularEfectividad(this.tipo1);
		if (this.tipo2 != null) modificador *= tipoAtaque.calcularEfectividad(this.tipo2);

		if (modificador > 1.5) return "DOBLE_VENTAJA";
		else if (modificador > 1.0) return "VENTAJA";
		else if (modificador < 1.0) return "DESVENTAJA"; 
		else return "NEUTRO"; 
	}
	
	public boolean aprenderMovimiento(Movimiento nuevoMovimiento) {
		for (int i = 0; i < movimientos.length; i++) {
			if (movimientos[i] == null) {
				movimientos[i] = nuevoMovimiento;
				System.out.println("¡" + nombre + " ha aprendido " + nuevoMovimiento.getNombreMovimiento() + "!");
				return true; 
			}
		}
		return false;
	}
	
	public String getMoteOCualquierNombre() {
		return (this.mote != null && !this.mote.isEmpty()) ? this.mote : this.nombre;
	}
	
	
}