package model;

import java.util.LinkedList;

public class Combate {
	
	private int idCombate;
	private Entrenador jugador;
	private Entrenador rival;
	private int idGanador;  //1 = jugador, 2 = rival
	
	private int turno; //turno actual
	
	private int pokemonKOJugador;
	private int pokemonKORival;
	
	private Pokemon pokemonActivoJugador;
	private Pokemon pokemonActivoRival;
	
	private LinkedList<Turno> listadoTurnos;
	
	//CONSTRUCTOR

	public Combate(int idCombate, Entrenador jugador, Entrenador rival, int idGanador, int turno, int pokemonKOJugador,
			int pokemonKORival, Pokemon pokemonActivoJugador, Pokemon pokemonActivoRival,
			LinkedList<Turno> listadoTurnos) {
		super();
		this.idCombate = idCombate;
		this.jugador = jugador;
		this.rival = rival;
		this.idGanador = idGanador;
		this.turno = turno;
		this.pokemonKOJugador = pokemonKOJugador;
		this.pokemonKORival = pokemonKORival;
		this.pokemonActivoJugador = pokemonActivoJugador;
		this.pokemonActivoRival = pokemonActivoRival;
		this.listadoTurnos = listadoTurnos;
	}
	
	//GETTERS Y SETTERS

	public int getIdCombate() {
		return idCombate;
	}

	public void setIdCombate(int idCombate) {
		this.idCombate = idCombate;
	}

	public Entrenador getJugador() {
		return jugador;
	}

	public void setJugador(Entrenador jugador) {
		this.jugador = jugador;
	}

	public Entrenador getRival() {
		return rival;
	}

	public void setRival(Entrenador rival) {
		this.rival = rival;
	}

	public int getIdGanador() {
		return idGanador;
	}

	public void setIdGanador(int idGanador) {
		this.idGanador = idGanador;
	}

	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getPokemonKOJugador() {
		return pokemonKOJugador;
	}

	public void setPokemonKOJugador(int pokemonKOJugador) {
		this.pokemonKOJugador = pokemonKOJugador;
	}

	public int getPokemonKORival() {
		return pokemonKORival;
	}

	public void setPokemonKORival(int pokemonKORival) {
		this.pokemonKORival = pokemonKORival;
	}

	public Pokemon getPokemonActivoJugador() {
		return pokemonActivoJugador;
	}

	public void setPokemonActivoJugador(Pokemon pokemonActivoJugador) {
		this.pokemonActivoJugador = pokemonActivoJugador;
	}

	public Pokemon getPokemonActivoRival() {
		return pokemonActivoRival;
	}

	public void setPokemonActivoRival(Pokemon pokemonActivoRival) {
		this.pokemonActivoRival = pokemonActivoRival;
	}

	public LinkedList<Turno> getListadoTurnos() {
		return listadoTurnos;
	}

	public void setListadoTurnos(LinkedList<Turno> listadoTurnos) {
		this.listadoTurnos = listadoTurnos;
	}
	

}
