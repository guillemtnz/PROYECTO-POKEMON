package model;

import java.util.ArrayList;
import java.util.Random;

public class Entrenador {
	
	public static Entrenador entrenadorLogueado;
	
	private int idEntrenador;
	
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
	public int getIdEntrenador() {
		return idEntrenador;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setIdEntrenador(int idEntrenador) {
		this.idEntrenador = idEntrenador;
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
            p.setDefensa(p.getDefensa() + 5);
            p.setDefensaEspecial(p.getDefensaEspecial() + 5);
            p.setVitalidad(p.getVitalidad() + 5);
            return true;
        }
        return false;
    }
 // ENTRENAMIENTO FURIOSO
    public boolean entrenamientoFurioso(Pokemon p) {
        int coste = 30 * p.getNivel();

        if (pokedollars >= coste) {
            pokedollars -= coste;
            p.setAtaque(p.getAtaque() + 5);
            p.setAtaqueEspecial(p.getAtaqueEspecial() + 5);
            p.setVelocidad(p.getVelocidad() + 5);
            return true;
        }
        return false;
    }
 // ENTRENAMIENTO FUNCIONAL
    public boolean entrenamientoFuncional(Pokemon p) {
        int coste = 40 * p.getNivel();

        if (pokedollars >= coste) {
            pokedollars -= coste;
            p.setVelocidad(p.getVelocidad() + 5);
            p.setAtaque(p.getAtaque() + 5);
            p.setDefensa(p.getDefensa() + 5);
            p.setVitalidad(p.getVitalidad() + 5);
            return true;
        }
        return false;
    }
    // ENTRENAMIENTO ONÍRICO
    public boolean entrenamientoOnirico(Pokemon p) {
        int coste = 40 * p.getNivel();

        if (pokedollars >= coste) {
            pokedollars -= coste;
            p.setVelocidad(p.getVelocidad() + 5);
            p.setAtaqueEspecial(p.getAtaqueEspecial() + 5);
            p.setDefensaEspecial(p.getDefensaEspecial() + 5);
            p.setVitalidad(p.getVitalidad() + 5);
            return true;
        }
        return false;
    }
    
    //COMBATIR
    
    
    
    //CAPTURAR
    
    public void capturar(Pokemon pokemonSalvaje) {
    	this.equipoSecundario.add(pokemonSalvaje);
    }
    
    //CRIANZA
    /**  
     * @param padre Pokémon MACHO (debe tener fertilidad > 0)
     * @param madre Pokémon HEMBRA (debe tener fertilidad > 0)
     * @return Pokémon hijo o null si no se puede criar
     */
    public Pokemon criarPokemon(Pokemon padre, Pokemon madre) {

        // Condiciones
        if (padre == null || madre == null) return null;
        if (padre.getSexo() != Pokemon.Sexo.MACHO)  return null;
        if (madre.getSexo() != Pokemon.Sexo.HEMBRA) return null;
        if (padre.getFertilidad() <= 0 || madre.getFertilidad() <= 0) return null;

        Random rnd = new Random();

        //  Mote mezclado 
        String motePadre = (padre.getMote() != null && !padre.getMote().isEmpty())
                ? padre.getMote() : padre.getNombre();
        String moteMadre = (madre.getMote() != null && !madre.getMote().isEmpty())
                ? madre.getMote() : madre.getNombre();

        int mitadP = motePadre.length() / 2;
        int mitadM = moteMadre.length() / 2;
        String segP = motePadre.substring(0, mitadP);
        String segM = moteMadre.substring(0, mitadM);

        // Orden aleatorio de las mitades
        String moteHijo = rnd.nextBoolean() ? segP + segM : segM + segP;
        if (moteHijo.isEmpty()) moteHijo = "Hijo"; // fallback

        // ── Movimientos mezclados (2 padre + 2 madre) ──
        Movimiento[] movHijo = new Movimiento[4];
        Movimiento[] movPadre = padre.getMovimientos();
        Movimiento[] movMadre = madre.getMovimientos();

        // indices no nulos de cada padre
        java.util.List<Integer> idxP = new java.util.ArrayList<>();
        java.util.List<Integer> idxM = new java.util.ArrayList<>();
        if (movPadre != null) for (int i = 0; i < movPadre.length; i++) if (movPadre[i] != null) idxP.add(i);
        if (movMadre != null) for (int i = 0; i < movMadre.length; i++) if (movMadre[i] != null) idxM.add(i);

        java.util.Collections.shuffle(idxP, rnd);
        java.util.Collections.shuffle(idxM, rnd);

        int slot = 0;
        for (int i = 0; i < 2 && i < idxP.size() && slot < 4; i++) movHijo[slot++] = movPadre[idxP.get(i)];
        for (int i = 0; i < 2 && i < idxM.size() && slot < 4; i++) movHijo[slot++] = movMadre[idxM.get(i)];

        // ── Tipos mezclados ──
        // Recogemos todos los tipos de ambos progenitores (sin nulos ni duplicados)
        java.util.Set<Tipo> tiposDisponibles = new java.util.LinkedHashSet<>();
        if (padre.getTipo1() != null) tiposDisponibles.add(padre.getTipo1());
        if (padre.getTipo2() != null) tiposDisponibles.add(padre.getTipo2());
        if (madre.getTipo1() != null) tiposDisponibles.add(madre.getTipo1());
        if (madre.getTipo2() != null) tiposDisponibles.add(madre.getTipo2());

        java.util.List<Tipo> listaTipos = new java.util.ArrayList<>(tiposDisponibles);
        java.util.Collections.shuffle(listaTipos, rnd);

        Tipo tipo1Hijo = listaTipos.isEmpty()  ? null : listaTipos.get(0);
        Tipo tipo2Hijo = listaTipos.size() < 2 ? null : listaTipos.get(1);

        // Favorecer tipos compartidos: si padre y madre comparten tipo1, el hijo lo hereda seguro
        if (padre.getTipo1() != null && padre.getTipo1().equals(madre.getTipo1())) {
            tipo1Hijo = padre.getTipo1();
        }

        // ── Mejores estadisticas ──
        int vitHijo   = Math.max(padre.getVitalidad(),        madre.getVitalidad());
        int atkHijo   = Math.max(padre.getAtaque(),           madre.getAtaque());
        int defHijo   = Math.max(padre.getDefensa(),          madre.getDefensa());
        int atkEHijo  = Math.max(padre.getAtaqueEspecial(),   madre.getAtaqueEspecial());
        int defEHijo  = Math.max(padre.getDefensaEspecial(),  madre.getDefensaEspecial());
        int velHijo   = Math.max(padre.getVelocidad(),        madre.getVelocidad());

        // ── Sexo aleatorio del hijo ──
        Pokemon.Sexo sexoHijo = rnd.nextBoolean() ? Pokemon.Sexo.MACHO : Pokemon.Sexo.HEMBRA;

        // ── Construir hijo ──
        // El hijo hereda nombre/numPokedex de la madre 
        Pokemon hijo = new Pokemon(
                0,                          // idPokemon (lo asigna ala BD)
                madre.getNumPokedex(),      // numPokedex (especie de la madre)
                this.idEntrenador,          // pertenece al entrenador actual
                madre.getNombre(),          // nombre de especie
                moteHijo,                   // mote mezclado
                vitHijo,                    // vitalidad
                vitHijo,                    // vitalidad actual (misma que la que tiene)
                atkHijo,                    // ataque
                defHijo,                    // defensa
                atkEHijo,                   // ataqueEspecial
                defEHijo,                   // defensaEspecial
                velHijo,                    // velocidad
                1,                          // nivel 1
                0,                          // experiencia 0
                movHijo,                    // movimientos mezclados
                5,                          // fertilidad inicial
                sexoHijo,                   // sexo aleatorio
                tipo1Hijo,                  // tipo1 mezclado
                tipo2Hijo,                  // tipo2 mezclado
                null,                       // sin estado
                "CAJA",
                null
        );

        // ── Reduce fertilidad de los padres ──
        padre.setFertilidad(padre.getFertilidad() - 1);
        madre.setFertilidad(madre.getFertilidad() - 1);

        return hijo;
    }
   
}

	

	
	
	

