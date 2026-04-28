package model;

/**
 * Esta es la clase Pokemon.
 * Aqui guardamos todos los datos de un pokemon como su nombre, sus estadisticas y sus movimientos.
 *
 * @author Julio Cesar Cachaca Valdez
 * @version 1.0
 */
public class Pokemon {
	
	// id del pokemon
	private int idPokemon;
	// numero de la pokedex
	private int numPokedex;
	// id del entrenador que tiene este pokemon
	private int idEntrenador;
	
	// nombre del pokemon
	private String nombre;
	// mote que le pone el entrenador
	private String mote;

	// estas son las estadisticas del pokemon
	private int vitalidad; // los puntos de vida
	private int vitalidadActual; // los puntos de vida que tiene ahora mismo
	private int ataque; // stats para atacar
	private int defensa; // stats para defenderse
	private int ataqueEspecial; // ataque pero especial
	private int defensaEspecial; // defensa pero especial
	private int velocidad; // la velocidad

	// estos son los modificadores que se usan en combate
	private int modAtaque = 0;
	private int modDefensa = 0;
	private int modAtaqueEspecial = 0;
	private int modDefensaEspecial = 0;
	private int modVelocidad = 0;

	// nivel y experiencia del pokemon
	private int nivel;
	private int experiencia;
	
	// array de movimientos, maximo 4 movimientos como en el juego
	private Movimiento[] movimientos = new Movimiento[4];
	
	// fertilidad para la crianza
	private int fertilidad;
	
	/**
	 * Enum del sexo del pokemon, puede ser macho o hembra
	 * lo necesitamos para la crianza (viva el amor)
	 */
	public enum Sexo{MACHO, HEMBRA};
	private Sexo sexo;
	
	// los tipos del pokemon, tipo1 es el principal y tipo2 puede ser null si solo tiene un tipo
	private Tipo tipo1;
	private Tipo tipo2;
	
	// el estado del pokemon 
	private Estado estado;
	
	// donde esta el pokemon, puede ser EQUIPO o CAJA
	private String ubicacion;
	
	// el objeto que lleva equipado el pokemon
	private Objeto objeto;

	/**
	 * Constructor vacio del pokemon.
	 * Cuando creamos un pokemon sin poner nada le ponemos estadisticas aleatorias
	 * entre 1 y 10 y empieza en nivel 1.
	 * La ubicacion por defecto es CAJA.
	 */
	public Pokemon() {
		super();
		this.idPokemon = 0;
		this.numPokedex = 0;
		this.idEntrenador = 0;
		this.nombre = "";
		this.mote = "";
		// las estadisticas son aleatorias entre 1 y 10
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
		this.sexo = Sexo.MACHO; // por defecto macho
		this.tipo1 = null;
		this.tipo2 = null;
		this.estado = null; // null significa que esta bien
		this.ubicacion = "CAJA";
		this.objeto = null; // sin objeto
	}
	
	/**
	 * Constructor con todos los parametros del pokemon.
	 * Este constructor lo usamos cuando queremos crear un pokemon con todos sus datos ya definidos
	 * por ejemplo cuando lo cargamos de la base de datos o cuando nace de la crianza.
	 *
	 * @param idPokemon el id del pokemon en la base de datos
	 * @param numPokedex el numero que tiene en la pokedex
	 * @param idEntrenador el id del entrenador al que pertenece
	 * @param nombre el nombre de la especie del pokemon
	 * @param mote el apodo que le pone el entrenador
	 * @param vitalidad los puntos de vida maximos
	 * @param vitalidadActual los puntos de vida que tiene en el momento
	 * @param ataque la estadistica de ataque
	 * @param defensa la estadistica de defensa
	 * @param ataqueEspecial la estadistica de ataque especial
	 * @param defensaEspecial la estadistica de defensa especial
	 * @param velocidad la estadistica de velocidad
	 * @param nivel el nivel del pokemon
	 * @param experiencia la experiencia que tiene
	 * @param movimientos array con sus 4 movimientos
	 * @param fertilidad las veces que puede criar 
	 * @param sexo si es macho o hembra
	 * @param tipo1 el tipo principal
	 * @param tipo2 el tipo secundario, puede ser null
	 * @param estado el estado que tiene, puede ser null si esta bien
	 * @param ubicacion donde esta el pokemon, EQUIPO o CAJA
	 * @param objeto el objeto que lleva, puede ser null
	 */
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

	// ---- GETTERS Y SETTERS ----
	// los getters y setters los genero con eclipse con click derecho -> source -> getters and setters
	
	/**
	 * Devuelve el id del pokemon
	 * @return el id del pokemon
	 */
	public int getIdPokemon() { return idPokemon; }
	
	/**
	 * Cambia el id del pokemon
	 * @param idPokemon el nuevo id
	 */
	public void setIdPokemon(int idPokemon) { this.idPokemon = idPokemon; }
	
	/**
	 * Devuelve el numero de la pokedex
	 * @return numero de pokedex
	 */
	public int getNumPokedex() { return numPokedex; }
	
	/**
	 * Cambia el numero de la pokedex
	 * @param numPokedex el nuevo numero
	 */
	public void setNumPokedex(int numPokedex) { this.numPokedex = numPokedex; }
	
	/**
	 * Devuelve el id del entrenador
	 * @return id del entrenador
	 */
	public int getIdEntrenador() { return idEntrenador; }
	
	/**
	 * Cambia el id del entrenador
	 * @param idEntrenador el nuevo id del entrenador
	 */
	public void setIdEntrenador(int idEntrenador) { this.idEntrenador = idEntrenador; }
	
	/**
	 * Devuelve el nombre del pokemon
	 * @return el nombre
	 */
	public String getNombre() { return nombre; }
	
	/**
	 * Cambia el nombre del pokemon
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(String nombre) { this.nombre = nombre; }
	
	/**
	 * Devuelve el mote del pokemon
	 * @return el mote
	 */
	public String getMote() { return mote; }
	
	/**
	 * Cambia el mote del pokemon
	 * @param mote el nuevo mote
	 */
	public void setMote(String mote) { this.mote = mote; }
	
	/**
	 * Devuelve la vitalidad maxima del pokemon (sus PS maximos)
	 * @return la vitalidad maxima
	 */
	public int getVitalidad() { return vitalidad; }
	
	/**
	 * Cambia la vitalidad maxima
	 * @param vitalidad la nueva vitalidad maxima
	 */
	public void setVitalidad(int vitalidad) { this.vitalidad = vitalidad; }
	
	/**
	 * Devuelve los PS que tiene ahora mismo el pokemon
	 * @return vitalidad actual
	 */
	public int getVitalidadActual() { return vitalidadActual; }
	
	/**
	 * Cambia los PS actuales del pokemon
	 * @param vitalidadActual los PS actuales
	 */
	public void setVitalidadActual(int vitalidadActual) { this.vitalidadActual = vitalidadActual; }
	
	/**
	 * Devuelve el ataque del pokemon
	 * @return ataque
	 */
	public int getAtaque() { return ataque; }
	
	/**
	 * Cambia el ataque del pokemon
	 * @param ataque el nuevo ataque
	 */
	public void setAtaque(int ataque) { this.ataque = ataque; }
	
	/**
	 * Devuelve la defensa del pokemon
	 * @return defensa
	 */
	public int getDefensa() { return defensa; }
	
	/**
	 * Cambia la defensa del pokemon
	 * @param defensa la nueva defensa
	 */
	public void setDefensa(int defensa) { this.defensa = defensa; }
	
	/**
	 * Devuelve el ataque especial del pokemon
	 * @return ataque especial
	 */
	public int getAtaqueEspecial() { return ataqueEspecial; }
	
	/**
	 * Cambia el ataque especial del pokemon
	 * @param ataqueEspecial el nuevo ataque especial
	 */
	public void setAtaqueEspecial(int ataqueEspecial) { this.ataqueEspecial = ataqueEspecial; }
	
	/**
	 * Devuelve la defensa especial del pokemon
	 * @return defensa especial
	 */
	public int getDefensaEspecial() { return defensaEspecial; }
	
	/**
	 * Cambia la defensa especial del pokemon
	 * @param defensaEspecial la nueva defensa especial
	 */
	public void setDefensaEspecial(int defensaEspecial) { this.defensaEspecial = defensaEspecial; }
	
	/**
	 * Devuelve la velocidad del pokemon
	 * @return velocidad
	 */
	public int getVelocidad() { return velocidad; }
	
	/**
	 * Cambia la velocidad del pokemon
	 * @param velocidad la nueva velocidad
	 */
	public void setVelocidad(int velocidad) { this.velocidad = velocidad; }
	
	/**
	 * Devuelve el nivel del pokemon
	 * @return nivel
	 */
	public int getNivel() { return nivel; }
	
	/**
	 * Cambia el nivel del pokemon
	 * @param nivel el nuevo nivel
	 */
	public void setNivel(int nivel) { this.nivel = nivel; }
	
	/**
	 * Devuelve la experiencia del pokemon
	 * @return experiencia
	 */
	public int getExperiencia() { return experiencia; }
	
	/**
	 * Cambia la experiencia del pokemon
	 * @param experiencia la nueva experiencia
	 */
	public void setExperiencia(int experiencia) { this.experiencia = experiencia; }
	
	/**
	 * Devuelve el array de movimientos del pokemon
	 * @return movimientos
	 */
	public Movimiento[] getMovimientos() { return movimientos; }
	
	/**
	 * Cambia los movimientos del pokemon
	 * @param movimientos el nuevo array de movimientos
	 */
	public void setMovimientos(Movimiento[] movimientos) { this.movimientos = movimientos; }
	
	/**
	 * Devuelve la fertilidad del pokemon
	 * @return fertilidad
	 */
	public int getFertilidad() { return fertilidad; }
	
	/**
	 * Cambia la fertilidad del pokemon
	 * @param fertilidad la nueva fertilidad
	 */
	public void setFertilidad(int fertilidad) { this.fertilidad = fertilidad; }
	
	/**
	 * Devuelve el sexo del pokemon
	 * @return sexo (MACHO o HEMBRA)
	 */
	public Sexo getSexo() { return sexo; }
	
	/**
	 * Cambia el sexo del pokemon
	 * @param sexo el nuevo sexo
	 */
	public void setSexo(Sexo sexo) { this.sexo = sexo; }
	
	/**
	 * Devuelve el tipo 1 del pokemon
	 * @return tipo1
	 */
	public Tipo getTipo1() { return tipo1; }
	
	/**
	 * Cambia el tipo 1 del pokemon
	 * @param tipo1 el nuevo tipo 1
	 */
	public void setTipo1(Tipo tipo1) { this.tipo1 = tipo1; }
	
	/**
	 * Devuelve el tipo 2 del pokemon, puede ser null si no tiene segundo tipo
	 * @return tipo2
	 */
	public Tipo getTipo2() { return tipo2; }
	
	/**
	 * Cambia el tipo 2 del pokemon
	 * @param tipo2 el nuevo tipo 2
	 */
	public void setTipo2(Tipo tipo2) { this.tipo2 = tipo2; }
	
	/**
	 * Devuelve el estado del pokemon
	 * @return estado (puede ser null si esta bien)
	 */
	public Estado getEstado() { return estado; }
	
	/**
	 * Cambia el estado del pokemon
	 * @param estado el nuevo estado
	 */
	public void setEstado(Estado estado) { this.estado = estado; }
	
	/**
	 * Devuelve donde esta el pokemon
	 * @return ubicacion, puede ser EQUIPO o CAJA
	 */
	public String getUbicacion() { return ubicacion; }
	
	/**
	 * Cambia la ubicacion del pokemon
	 * @param ubicacion la nueva ubicacion
	 */
	public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }
	
	/**
	 * Devuelve el objeto que lleva el pokemon equipado
	 * @return objeto (puede ser null si no lleva nada)
	 */
	public Objeto getObjeto() { return objeto; }
	
	/**
	 * Cambia el objeto equipado del pokemon
	 * @param objeto el nuevo objeto
	 */
	public void setObjeto(Objeto objeto) { this.objeto = objeto; }

	// ---- METODOS DE MODIFICADORES DE COMBATE ----
	
	/**
	 * Este metodo resetea todos los modificadores a 0.
	 * Lo llamamos cuando termina el combate para que el pokemon vuelva a sus stats normales.
	 */
	public void resetearModificadores() {
		this.modAtaque = 0; this.modDefensa = 0;
		this.modAtaqueEspecial = 0; this.modDefensaEspecial = 0;
		this.modVelocidad = 0;
	}

	/**
	 * Cambia el modificador de ataque, no puede salirse de -6 a 6
	 * @param m el nuevo modificador
	 */
	public void setModAtaque(int m) { this.modAtaque = Math.max(-6, Math.min(6, m)); }
	
	/**
	 * Cambia el modificador de defensa, no puede salirse de -6 a 6
	 * @param m el nuevo modificador
	 */
	public void setModDefensa(int m) { this.modDefensa = Math.max(-6, Math.min(6, m)); }
	
	/**
	 * Cambia el modificador de ataque especial, no puede salirse de -6 a 6
	 * @param m el nuevo modificador
	 */
	public void setModAtaqueEspecial(int m) { this.modAtaqueEspecial = Math.max(-6, Math.min(6, m)); }
	
	/**
	 * Cambia el modificador de defensa especial, no puede salirse de -6 a 6
	 * @param m el nuevo modificador
	 */
	public void setModDefensaEspecial(int m) { this.modDefensaEspecial = Math.max(-6, Math.min(6, m)); }
	
	/**
	 * Cambia el modificador de velocidad, no puede salirse de -6 a 6
	 * @param m el nuevo modificador
	 */
	public void setModVelocidad(int m) { this.modVelocidad = Math.max(-6, Math.min(6, m)); }
	
	/**
	 * Devuelve el modificador de ataque actual
	 * @return modAtaque
	 */
	public int getModAtaque() { return this.modAtaque; }
	
	/**
	 * Devuelve el modificador de defensa actual
	 * @return modDefensa
	 */
	public int getModDefensa() { return this.modDefensa; }
	
	/**
	 * Devuelve el modificador de ataque especial actual
	 * @return modAtaqueEspecial
	 */
	public int getModAtaqueEspecial() { return this.modAtaqueEspecial; }
	
	/**
	 * Devuelve el modificador de defensa especial actual
	 * @return modDefensaEspecial
	 */
	public int getModDefensaEspecial() { return this.modDefensaEspecial; }
	
	/**
	 * Devuelve el modificador de velocidad actual
	 * @return modVelocidad
	 */
	public int getModVelocidad() { return this.modVelocidad; }

	// METODOS PARA OBTENER LAS STATS REALES EN COMBATE
	// estos metodos calculan la statS reales sumando el bonus del objeto y aplicando el modificador
	
	/**
	 * Calcula el ataque real en combate.
	 * Suma el bonus del objeto si tiene uno y luego aplica el modificador de combate.
	 * @return el ataque real que se usa en el combate
	 */
	public int getAtaqueEnCombate() { 
        int baseConObjeto = this.ataque + (objeto != null ? objeto.getAtaque() : 0);
        return calcularStat(Math.max(1, baseConObjeto), this.modAtaque); 
    }

	/**
	 * Calcula la defensa real en combate.
	 * Suma el bonus del objeto si tiene uno y luego aplica el modificador de combate.
	 * @return la defensa real que se usa en el combate
	 */
    public int getDefensaEnCombate() { 
        int baseConObjeto = this.defensa + (objeto != null ? objeto.getDefensa() : 0);
        return calcularStat(Math.max(1, baseConObjeto), this.modDefensa); 
    }

    /**
	 * Calcula el ataque especial real en combate.
	 * Suma el bonus del objeto si tiene uno y luego aplica el modificador de combate.
	 * @return el ataque especial real que se usa en el combate
	 */
    public int getAtaqueEspecialEnCombate() { 
        int baseConObjeto = this.ataqueEspecial + (objeto != null ? objeto.getAtaEsp() : 0);
        return calcularStat(Math.max(1, baseConObjeto), this.modAtaqueEspecial); 
    }

    /**
	 * Calcula la defensa especial real en combate.
	 * Suma el bonus del objeto si tiene uno y luego aplica el modificador de combate.
	 * @return la defensa especial real que se usa en el combate
	 */
    public int getDefensaEspecialEnCombate() { 
        int baseConObjeto = this.defensaEspecial + (objeto != null ? objeto.getDefensa() : 0);
        return calcularStat(Math.max(1, baseConObjeto), this.modDefensaEspecial); 
    }

    /**
	 * Calcula la velocidad real en combate.
	 * Suma el bonus del objeto si tiene uno y luego aplica el modificador de combate.
	 * @return la velocidad real que se usa en el combate
	 */
    public int getVelocidadEnCombate() { 
        int baseConObjeto = this.velocidad + (objeto != null ? objeto.getVelocidad() : 0);
        return calcularStat(Math.max(1, baseConObjeto), this.modVelocidad); 
    }

	/**
	 * Metodo privado que aplica el modificador a una estadistica.
	 * Si el modificador es positivo la stat sube y si es negativo baja.
	 * Lo busque en internet como funciona la formula de pokemon y la pusimos asi.
	 *
	 * @param base el valor base de la estadistica (ya con el bonus del objeto)
	 * @param modificador el modificador entre -6 y 6
	 * @return la estadistica final con el modificador aplicado
	 */
	private int calcularStat(int base, int modificador) {
		if (modificador == 0) return base; // si no hay modificador devolvemos la base
		double multiplicador = (modificador > 0) ? ((2.0 + modificador) / 2.0) : (2.0 / (2.0 + Math.abs(modificador)));
		return (int) (base * multiplicador);
	}

	// ---- METODOS DE JUEGO ----
	
	/**
	 * Metodo para que el pokemon gane experiencia despues de un combate.
	 * Cuando tiene suficiente experiencia sube de nivel automaticamente con un while.
	 * El maximo de nivel es 100.
	 *
	 * @param expGanada la cantidad de experiencia que gana
	 */
	public void ganarExperiencia(int expGanada) {
		this.experiencia += expGanada;
		System.out.println(this.getMoteOCualquierNombre() + " ha ganado " + expGanada + " puntos de experiencia.");
		// mientras tenga suficiente experiencia y no sea nivel 100 sube de nivel
		while (this.experiencia >= (10 * this.nivel) && this.nivel < 100) {
			subirNivel();
		}
	}
	
	/**
	 * Metodo que sube un nivel al pokemon.
	 * Al subir de nivel todas las estadisticas suben un numero aleatorio entre 1 y 5.
	 * Tambien se restauran los ps al maximo.
	 * Cada 5 niveles sale un mensaje diciendo que puede aprender un movimiento nuevo.
	 */
	public void subirNivel(){	
		this.experiencia -= 10 * this.nivel; // le quitamos la experiencia que ha gastado
		// subimos todas las stats con numeros aleatorios entre 1 y 5
		this.vitalidad += (int)(Math.random() * 5) + 1;
		this.ataque += (int)(Math.random() * 5) + 1;
		this.defensa += (int)(Math.random() * 5) + 1;
		this.ataqueEspecial += (int)(Math.random() * 5) + 1;
		this.defensaEspecial += (int)(Math.random() * 5) + 1;
		this.velocidad += (int)(Math.random() * 5) + 1;
		this.nivel++;
		this.vitalidadActual = this.vitalidad; // curamos al pokemon al subir de nivel
		
		System.out.println("!" + this.getMoteOCualquierNombre() + " ha subido al nivel " + this.nivel + "!");
		// cada 5 niveles puede aprender un movimiento nuevo
		if (this.nivel % 5 == 0) {
			System.out.println("!A " + this.getMoteOCualquierNombre() + " le gustaria aprender un nuevo movimiento!");
		}
	}

	/**
	 * Hace que este pokemon ataque a otro pokemon con un movimiento.
	 * Primero comprueba que los dos esten vivos antes de atacar.
	 *
	 * @param objetivo el pokemon al que vamos a atacar
	 * @param mov el movimiento que vamos a usar
	 */
	public void atacar(Pokemon objetivo, Movimiento mov) {
		// comprobamos que el que ataca no este debilitado
		if (this.vitalidadActual <= 0) {
			System.out.println(this.getMoteOCualquierNombre() + " esta debilitado y no puede atacar.");
			return;
		}
		// comprobamos que el objetivo tampoco este ya debilitado
		if (objetivo.getVitalidadActual() <= 0) {
			System.out.println("Pero " + objetivo.getMoteOCualquierNombre() + " ya esta debilitado!");
			return;
		}
		mov.ejecutarMovimiento(this, objetivo);
	}
	
	/**
	 * Comprueba si un tipo de ataque es efectivo contra este pokemon.
	 * Mira los dos tipos del pokemon y calcula si hay ventaja o desventaja.
	 *
	 * @param tipoAtaque el tipo del movimiento que nos van a usar
	 * @return un String que dice DOBLE_VENTAJA, VENTAJA, DESVENTAJA o NEUTRO
	 */
	public String comprobarEfectividad(Tipo tipoAtaque) {
		double modificador = 1.0;
		// multiplicamos por la efectividad de cada tipo
		if (this.tipo1 != null) modificador *= tipoAtaque.calcularEfectividad(this.tipo1);
		if (this.tipo2 != null) modificador *= tipoAtaque.calcularEfectividad(this.tipo2);

		// dependiendo del resultado devolvemos el String correspondiente
		if (modificador > 1.5) return "DOBLE_VENTAJA";
		else if (modificador > 1.0) return "VENTAJA";
		else if (modificador < 1.0) return "DESVENTAJA"; 
		else return "NEUTRO"; 
	}
	
	/**
	 * Intenta que el pokemon aprenda un movimiento nuevo.
	 * Busca el primer hueco libre en el array de movimientos y lo pone ahi.
	 * Si ya tiene 4 movimientos devuelve false y no aprende nada.
	 *
	 * @param nuevoMovimiento el movimiento que queremos que aprenda
	 * @return true si ha podido aprenderlo, false si ya tenia 4 movimientos
	 */
	public boolean aprenderMovimiento(Movimiento nuevoMovimiento) {
		// recorremos el array buscando un hueco libre (null)
		for (int i = 0; i < movimientos.length; i++) {
			if (movimientos[i] == null) {
				movimientos[i] = nuevoMovimiento;
				System.out.println(nombre + " ha aprendido " + nuevoMovimiento.getNombreMovimiento() + "!");
				return true; // lo aprendio correctamente
			}
		}
		return false; // no habia hueco libre Sadge
	}
	
	/**
	 * Devuelve el mote del pokemon si tiene uno, o el nombre si no tiene mote.
	 * Lo usamos para mostrar el nombre correcto en los mensajes del combate.
	 * @return el mote del pokemon o su nombre si no tiene mote
	 */
	public String getMoteOCualquierNombre() {
		return (this.mote != null && !this.mote.isEmpty()) ? this.mote : this.nombre;
	}
	
	
}