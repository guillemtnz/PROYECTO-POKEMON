package model;

import java.util.ArrayList;
import java.util.Random;

public class Entrenador {
	
	private String nombre;
	private int pokedollars;
	
	private ArrayList<Pokemon> equipoPrincipal;
	private ArrayList<Pokemon> equipoSecundario;
	
	private ArrayList<Objeto> mochila;

	//constructores me falta poner parametros y copia 
	public Entrenador(String nombre, int pokedollars, ArrayList<Pokemon> equipoPrincipal,
			ArrayList<Pokemon> equipoSecundario, ArrayList<Objeto> mochila) {
		super();
		this.nombre = nombre;
		this.pokedollars = pokedollars;
		this.equipoPrincipal = equipoPrincipal;
		this.equipoSecundario = equipoSecundario;
		this.mochila = mochila;
	
	}
	//getter setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPokedollars() {
		return pokedollars;
	}

	public void setPokedollars(int pokedollars) {
		this.pokedollars = pokedollars;
	}

	public ArrayList<Pokemon> getEquipoPrincipal() {
		return equipoPrincipal;
	}

	public void setEquipoPrincipal(ArrayList<Pokemon> equipoPrincipal) {
		this.equipoPrincipal = equipoPrincipal;
	}

	public ArrayList<Pokemon> getEquipoSecundario() {
		return equipoSecundario;
	}

	public void setEquipoSecundario(ArrayList<Pokemon> equipoSecundario) {
		this.equipoSecundario = equipoSecundario;
	}

	public ArrayList<Objeto> getMochila() {
		return mochila;
	}

	public void setMochila(ArrayList<Objeto> mochila) {
		this.mochila = mochila;
	}
	
	//METODOS 
	
	// MOVER DE EQUIPO A CAJA
    public boolean moverEquipoACaja(Pokemon p) {
        if (equipoPrincipal.size() > 1 && equipoPrincipal.contains(p)) {
            equipoPrincipal.remove(p);
            equipoSecundario.add(p);
            return true;
        }
        return false;
    }
	// MOVER DE CAJA A EQUIPO
    public boolean moverCajaAEquipo(Pokemon p) {
        if (equipoPrincipal.size() < 6 && equipoSecundario.contains(p)) {
            equipoSecundario.remove(p);
            equipoPrincipal.add(p);
            return true;
        }
        return false;
    }
 // CAPTURAR POKÉMON
    public void capturarPokemon(Pokemon p) {
        equipoSecundario.add(p);
}
 // ENTRENAMIENTO PESADO
    public boolean entrenamientoPesado(Pokemon p) {
        int coste = 20 * p.getNivel();

        if (pokedollars >= coste) {
            pokedollars -= coste;
            p.aumentarDefensa(5);
            p.aumentarDefensaEspecial(5);
            p.aumentarVitalidad(5);
            return true;
        }
        return false;
    }
 // ENTRENAMIENTO FURIOSO
    public boolean entrenamientoFurioso(Pokemon p) {
        int coste = 30 * p.getNivel();

        if (pokedollars >= coste) {
            pokedollars -= coste;
            p.aumentarAtaque(5);
            p.aumentarAtaqueEspecial(5);
            p.aumentarVelocidad(5);
            return true;
        }
        return false;
    }
 // ENTRENAMIENTO FUNCIONAL
    public boolean entrenamientoFuncional(Pokemon p) {
        int coste = 40 * p.getNivel();

        if (pokedollars >= coste) {
            pokedollars -= coste;
            p.aumentarVelocidad(5);
            p.aumentarAtaque(5);
            p.aumentarDefensa(5);
            p.aumentarVitalidad(5);
            return true;
        }
        return false;
    }
    // ENTRENAMIENTO ONÍRICO
    public boolean entrenamientoOnirico(Pokemon p) {
        int coste = 40 * p.getNivel();

        if (pokedollars >= coste) {
            pokedollars -= coste;
            p.aumentarVelocidad(5);
            p.aumentarAtaqueEspecial(5);
            p.aumentarDefensaEspecial(5);
            p.aumentarVitalidad(5);
            return true;
        }
        return false;
    }
 // CRIAR POKÉMON (fumadita)
   
}

	

	
	
	

