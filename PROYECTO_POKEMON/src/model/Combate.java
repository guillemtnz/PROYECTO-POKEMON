package model;

import java.util.LinkedList;

/**
 * Representa un combate entre dos entrenadores Pokémon.
 * Gestiona el estado del combate, los turnos, los ataques, las alteraciones de estado
 * y las condiciones de victoria o derrota.
 */

public class Combate {
	
	private int idCombate;
	private Entrenador jugador;
	private Entrenador rival;
	private int idGanador;  
	
	private int turno; 
	
	private int pokemonKOJugador;
	private int pokemonKORival;
	
	private Pokemon pokemonActivoJugador;
	private Pokemon pokemonActivoRival;
	
	private LinkedList<Turno> listadoTurnos;
	
	/**
	 * Constructor de la clase Combate.
	 * * @param idCombate Identificador del combate. (Es único)
	 * @param jugador Es el jugador. El que se ha logueado.
	 * @param rival Entrenador controlado por el PC.
	 * @param idGanador Ganador. 0 si el combate está en curso, 1 si gana jugador, 2 si gana el rival.
	 * @param turno Número de turno actual.
	 * @param pokemonKOJugador Contador de Pokémon debilitados del jugador.
	 * @param pokemonKORival Contador de Pokémon debilitados del rival.
	 * @param pokemonActivoJugador Pokémon que el jugador tiene en el campo.
	 * @param pokemonActivoRival Pokémon que el rival tiene en el campo.
	 * @param listadoTurnos Historial de los turnos jugados en el combate.
	 */
	
	public Combate(int idCombate, Entrenador jugador, Entrenador rival, int idGanador, int turno, int pokemonKOJugador,
			int pokemonKORival, Pokemon pokemonActivoJugador, Pokemon pokemonActivoRival,
			LinkedList<Turno> listadoTurnos) {
		super();
		this.idCombate = idCombate; this.jugador = jugador; this.rival = rival;
		this.idGanador = idGanador; this.turno = turno;
		this.pokemonKOJugador = pokemonKOJugador; this.pokemonKORival = pokemonKORival;
		this.pokemonActivoJugador = pokemonActivoJugador; this.pokemonActivoRival = pokemonActivoRival;
		this.listadoTurnos = listadoTurnos;
	}
	
	public int getIdCombate() { return idCombate; }
	public void setIdCombate(int idCombate) { this.idCombate = idCombate; }
	public Entrenador getJugador() { return jugador; }
	public void setJugador(Entrenador jugador) { this.jugador = jugador; }
	public Entrenador getRival() { return rival; }
	public void setRival(Entrenador rival) { this.rival = rival; }
	public int getIdGanador() { return idGanador; }
	public void setIdGanador(int idGanador) { this.idGanador = idGanador; }
	public int getTurno() { return turno; }
	public void setTurno(int turno) { this.turno = turno; }
	public int getPokemonKOJugador() { return pokemonKOJugador; }
	public void setPokemonKOJugador(int pokemonKOJugador) { this.pokemonKOJugador = pokemonKOJugador; }
	public int getPokemonKORival() { return pokemonKORival; }
	public void setPokemonKORival(int pokemonKORival) { this.pokemonKORival = pokemonKORival; }
	public Pokemon getPokemonActivoJugador() { return pokemonActivoJugador; }
	public void setPokemonActivoJugador(Pokemon pokemonActivoJugador) { this.pokemonActivoJugador = pokemonActivoJugador; }
	public Pokemon getPokemonActivoRival() { return pokemonActivoRival; }
	public void setPokemonActivoRival(Pokemon pokemonActivoRival) { this.pokemonActivoRival = pokemonActivoRival; }
	public LinkedList<Turno> getListadoTurnos() { return listadoTurnos; }
	public void setListadoTurnos(LinkedList<Turno> listadoTurnos) { this.listadoTurnos = listadoTurnos; }

	// LÓGICA DEL TURNO
	
	/**
	 * Resuelve las acciones de un turno completo, determinando el orden de ataque
	 * en base a la prioridad de los movimientos y la velocidad de los Pokémon.
	 * Además, aplica daño de ataques, gestiona estados alterados y registra el turno.
	 * * @param movJugador Movimiento seleccionado por el jugador.
	 * @param movRival Movimiento seleccionado por el rival.
	 * @return Un String (log) con lo que ha ocurrido en el turno.
	 */
	
	public String resolverTurno(Movimiento movJugador, Movimiento movRival) {
	    // Creamos el constructor de texto para el Log
	    StringBuilder logTurno = new StringBuilder();

	    Pokemon primero, segundo;
	    Movimiento movPrimero, movSegundo;

	    // CÁLCULO DE QUIÉN ATACA PRIMERO
	    if (movJugador.getPrioridad() > movRival.getPrioridad()) {
	        primero = pokemonActivoJugador; segundo = pokemonActivoRival;
	        movPrimero = movJugador; movSegundo = movRival;
	    } else if (movRival.getPrioridad() > movJugador.getPrioridad()) {
	        primero = pokemonActivoRival; segundo = pokemonActivoJugador;
	        movPrimero = movRival; movSegundo = movJugador;
	    } else {
	        if (pokemonActivoJugador.getVelocidadEnCombate() >= pokemonActivoRival.getVelocidadEnCombate()) {
	            primero = pokemonActivoJugador; segundo = pokemonActivoRival;
	            movPrimero = movJugador; movSegundo = movRival;
	        } else {
	            primero = pokemonActivoRival; segundo = pokemonActivoJugador;
	            movPrimero = movRival; movSegundo = movJugador;
	        }
	    }

	    //ATAQUE DEL PRIMER POKÉMON 
	    if (puedeAtacar(primero)) {
	        
	        logTurno.append(movPrimero.ejecutarMovimiento(primero, segundo));
	        
	        if (segundo.getVitalidadActual() <= 0) {
	            procesarDebilitamiento(segundo);
	            
	        }
	    }

	    //  ATAQUE DEL SEGUNDO POKÉMON (Si sigue vivo) 
	    if (segundo.getVitalidadActual() > 0 && this.idGanador == 0) {
	        if (puedeAtacar(segundo)) {
	            // Capturamos el texto del segundo ataque
	            logTurno.append(movSegundo.ejecutarMovimiento(segundo, primero));
	            
	            if (primero.getVitalidadActual() <= 0) {
	                procesarDebilitamiento(primero);
	            }
	        }
	    }

	    // APLICAR EFECTOS DE ESTADO 
	    if (pokemonActivoJugador.getVitalidadActual() > 0 && this.idGanador == 0) {
	        aplicarEfectosDeEstado(pokemonActivoJugador);
	       
	    }
	    if (pokemonActivoRival.getVitalidadActual() > 0 && this.idGanador == 0) {
	        aplicarEfectosDeEstado(pokemonActivoRival);
	    }

	    // Registramos 
	    registrarTurno(movJugador.getNombreMovimiento(), movRival.getNombreMovimiento());
	    
	    // Aumentamos el contador de turno
	    this.turno++; 

	    // 
	    return logTurno.toString();
	}

	/**
	 * Cura 20 puntos de salud (PS) a un Pokémon objetivo.
	 * * @param entrenador Entrenador que curará al pokemon.
	 * @param objetivo Pokemon al que se curará.
	 */
	
	public void curacion(Entrenador entrenador, Pokemon objetivo) {
		int curacion = 20; 
		if (objetivo.getVitalidadActual() > 0 && objetivo.getVitalidadActual() < objetivo.getVitalidad()) {
			objetivo.setVitalidadActual(Math.min(objetivo.getVitalidad(), objetivo.getVitalidadActual() + curacion));
			System.out.println("¡" + entrenador.getNombre() + " usó una poción! " + objetivo.getNombre() + " recuperó " + curacion + " PS.");
		} else {
			System.out.println("No se puede usar el objeto en " + objetivo.getNombre() + ".");
		}
	}

	/**
	 * Comprueba si un Pokémon es capaz de efectuar su ataque en el turno actual,
	 * evaluando las restricciones causadas por sus alteraciones de estado (parálisis, sueño, etc.).
	 * * @param p El Pokémon que intenta atacar.
	 * @return true si el Pokémon puede atacar, false si su estado se lo impide.
	 */
	
	private boolean puedeAtacar(Pokemon p) {
		if (p.getEstado() == null) return true;

		switch (p.getEstado()) {
			case PARALIZADO:
				if (Math.random() < 0.25) { 
					System.out.println("¡" + p.getNombre() + " está paralizado y no se puede mover!");
					return false;
				}
				break;
			case DORMIDO:
				if (Math.random() < 0.33) { 
					p.setEstado(null);
					System.out.println("¡" + p.getNombre() + " se ha despertado!");
					return true;
				}
				System.out.println(p.getNombre() + " está profundamente dormido...");
				return false;
			case CONGELADO:
				if (Math.random() < 0.20) { 
					p.setEstado(null);
					System.out.println("¡" + p.getNombre() + " se ha descongelado!");
					return true;
				}
				System.out.println("¡" + p.getNombre() + " está congelado y no puede moverse!");
				return false;
			case CONFUSO:
				if (Math.random() < 0.25) { 
					p.setEstado(null);
					System.out.println("¡" + p.getNombre() + " ya no está confuso!");
					return true;
				}
				System.out.println("¡" + p.getNombre() + " está tan confuso que se hirió a sí mismo!");
				int danioConfusion = p.getVitalidad() / 8; 
				p.setVitalidadActual(Math.max(0, p.getVitalidadActual() - danioConfusion));
				if (p.getVitalidadActual() == 0) procesarDebilitamiento(p);
				return false; 
			default:
				break;
		}
		return true;
	}
	
	/**
	 * Aplica el daño al final del turno causado por alteraciones de estado
	 * como veneno o quemaduras.
	 * * @param p El Pokémon que sufre el estado alterado.
	 */

	private void aplicarEfectosDeEstado(Pokemon p) {
		if (p.getEstado() == null) return;

		int danioResidual = 0;
		switch (p.getEstado()) {
			case QUEMADO:
			case ENVENENADO:
				danioResidual = Math.max(1, p.getVitalidad() / 8);
				System.out.println("¡" + p.getNombre() + " se resiente por su estado (" + p.getEstado() + ")!");
				break;
			case GRAVEMENTE_ENVENENADO:
				danioResidual = Math.max(1, p.getVitalidad() / 6);
				System.out.println("¡El grave envenenamiento merma la salud de " + p.getNombre() + "!");
				break;
			default:
				break;
		}

		if (danioResidual > 0) {
			p.setVitalidadActual(p.getVitalidadActual() - danioResidual);
			if (p.getVitalidadActual() <= 0) {
				p.setVitalidadActual(0);
				procesarDebilitamiento(p);
			}
		}
	}
	
	/**
	 * Procesa cuando los PS de un pokemon llegan a 0. Actualiza los contadores de KO,
	 * reparte experiencia si el debilitado es del rival y comprueba si el combate debe finalizar.
	 * * @param pokemonDebilitado El Pokémon que acaba de perder todos sus PS.
	 */
	
	public void procesarDebilitamiento(Pokemon pokemonDebilitado) {
		System.out.println("¡" + pokemonDebilitado.getNombre() + " se ha debilitado!");
		pokemonDebilitado.setEstado(Estado.DEBILITADO);
		pokemonDebilitado.resetearModificadores();

		if (pokemonDebilitado == this.pokemonActivoJugador) {
			this.pokemonKOJugador++;
		} else {
			this.pokemonKORival++;
			int nivelP = this.pokemonActivoJugador.getNivel();
			int nivelR = this.pokemonActivoRival.getNivel();
			int expGanada = (nivelP + (nivelR * 10)) / 4;
			this.pokemonActivoJugador.ganarExperiencia(expGanada);
		}
		comprobarFinCombate();
	}
	
	/**
	 * Verifica si alguno de los entrenadores ha perdido a sus 6 Pokémon
	 * para finalizar el combate y declarar un ganador.
	 */

	private void comprobarFinCombate() {
		if (this.pokemonKOJugador >= 6) {
			finalizarCombate(2); 
		} else if (this.pokemonKORival >= 6) {
			finalizarCombate(1); 
		}
	}
	
	/**
	 * Concluye el combate, gestiona la transferencia de Pokédolares del perdedor al ganador,
	 * resetea los modificadores de los Pokémon activos en ese momento y exporta el historial del combate.
	 * * @param ganador numero que representa quién gana el combate (1 para el jugador, 2 para el rival).
	 */

	private void finalizarCombate(int ganador) {
		this.idGanador = ganador;
		Entrenador winner = (ganador == 1) ? this.jugador : this.rival;
		Entrenador loser = (ganador == 1) ? this.rival : this.jugador;

		System.out.println("\n¡El combate ha terminado! Ganador: " + winner.getNombre());
		int dineroPerdido = loser.getPokedollars() / 3;
		loser.setPokedollars(loser.getPokedollars() - dineroPerdido);
		winner.setPokedollars(winner.getPokedollars() + dineroPerdido);

		System.out.println(loser.getNombre() + " paga " + dineroPerdido + " PokéDollars al ganador.");
		
		if (this.pokemonActivoJugador != null) this.pokemonActivoJugador.resetearModificadores();
		if (this.pokemonActivoRival != null) this.pokemonActivoRival.resetearModificadores();

		exportarHistorial();
	}

	/**
	 * Permite al jugador huir del combate. Esto cuenta como una derrota,
	 * penalizándolo como si sus 6 Pokémon hubieran sido debilitados.
	 */
	
	public void retirarse() {
		System.out.println("¡" + this.jugador.getNombre() + " ha huido del combate!");
		this.pokemonKOJugador = 6; 
		finalizarCombate(2);
	}
	
	/**
	 * Añade el registro de las acciones de los jugadores al historial del combate.
	 * * @param accionJugador El movimiento o acción ejecutada por el jugador.
	 * @param accionRival El movimiento o acción ejecutada por el rival.
	 */
	
	public void registrarTurno(String accionJugador, String accionRival) {
		Turno nuevoTurno = new Turno(this.turno, accionJugador, accionRival);
		this.listadoTurnos.add(nuevoTurno);
		this.turno++; 
	}
	
	/**
	 * Exporta el historial completo de los turnos de la batalla a un archivo .txt.
	 * El nombre del archivo se genera usando la hora actual para evitar colisiones.
	 */

	private void exportarHistorial() {
		String nombreFichero = "CombateLog_" + System.currentTimeMillis() + ".txt";
		try (java.io.FileWriter writer = new java.io.FileWriter(nombreFichero)) {
			for (Turno t : this.listadoTurnos) {
				writer.write("Turno " + t.getNumTurno() + ":\n");
				writer.write("Entrenador: " + t.getAccionEntrenador() + "\n");
				writer.write("Rival: " + t.getAccionRival() + "\n\n");
			}
			System.out.println("¡Historial exportado correctamente a " + nombreFichero + "!");
		} catch (java.io.IOException e) {
			System.err.println("Error al crear el archivo de registro: " + e.getMessage());
		}
	}
}