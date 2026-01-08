package proyecto;

import java.util.random.*;

public class Pokemon {
	
	private String nombre;
	private String mote;
	private int vitalidad;
	private int ataque;
	private int defensa;
	private int ataqueEspecial;
	private int defensaEspecial;
	private int velocidad;
	private int estamina;
	private int nivel;
	
	//COLECCION DE MOVIMIENTOS
	
	private int fertilidad;
	private String sexo;
	
	
	//COLECCION DE TIPOS
	
	//ESTADOS
	
	
	// SETTERS Y GETTERS
	
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
	public int getEstamina() {
		return estamina;
	}
	public void setEstamina(int estamina) {
		this.estamina = estamina;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public int getFertilidad() {
		return fertilidad;
	}
	public void setFertilidad(int fertilidad) {
		this.fertilidad = fertilidad;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	//CONSTRUCTORES 
	
	//todos los parámetros
	public Pokemon(String nombre, String mote, int vitalidad, int ataque, int defensa, int ataqueEspecial,
			int defensaEspecial, int velocidad, int estamina, int nivel, int fertilidad, String sexo) {
		super();
		this.nombre = nombre;
		this.mote = mote;
		this.vitalidad = vitalidad;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataqueEspecial = ataqueEspecial;
		this.defensaEspecial = defensaEspecial;
		this.velocidad = velocidad;
		this.estamina = estamina;
		this.nivel = nivel;
		this.fertilidad = fertilidad;
		this.sexo = sexo;
	}
	//por defecto
	public Pokemon() {
		super();
		this.nombre = "";
		this.mote = "";
		this.vitalidad = (int)(Math.random()*10)+1;
		this.ataque = (int)(Math.random()*10)+1;
		this.defensa = (int)(Math.random()*10)+1;
		this.ataqueEspecial = (int)(Math.random()*10)+1;
		this.defensaEspecial = (int)(Math.random()*10)+1;
		this.velocidad = (int)(Math.random()*10)+1;
		this.estamina = 0; //no sé cómo funciona la estamina
		this.nivel = 1;
		this.fertilidad = 5;
		this.sexo = "";
	}
	
	//copia

	public Pokemon(Pokemon p) {
		super();
		this.nombre = p.nombre;
		this.mote = p.mote;
		this.vitalidad = p.vitalidad;
		this.ataque = p.ataque;
		this.defensa = p.defensa;
		this.ataqueEspecial = p.ataqueEspecial;
		this.defensaEspecial = p.defensaEspecial;
		this.velocidad = p.velocidad;
		this.estamina = p.estamina;
		this.nivel = p.nivel;
		this.fertilidad = p.fertilidad;
		this.sexo = p.sexo;
	}

}
