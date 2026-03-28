package model;

public class Objeto {
	
	private int idObjeto;
    private String nombre;
    
 // Estos valores son porcentajes (por ejemplo 20 = +20%, -10 = -10%)
    private int ataque;      
    private int defensa;
    private int ataEsp;
    private int defEsp;
    private int velocidad;
    
    //CONSTRUCTORES
    
	public Objeto(int idObjeto, String nombre, int ataque, int defensa, int ataEsp, int defEsp, int velocidad) {
		super();
		this.idObjeto = idObjeto;
		this.nombre = nombre;
		this.ataque = ataque;
		this.defensa = defensa;
		this.ataEsp = ataEsp;
		this.defEsp = defEsp;
		this.velocidad = velocidad;
	}
	
	public Objeto() {
		super();
		this.idObjeto = 0;
		this.nombre = "";
		this.ataque = 0;
		this.defensa = 0;
		this.ataEsp = 0;
		this.defEsp = 0;
		this.velocidad = 0;
	}
	
	public Objeto(Objeto o) {
		super();
		this.idObjeto = o.idObjeto;
		this.nombre = o.nombre;
		this.ataque = o.ataque;
		this.defensa = o.defensa;
		this.ataEsp = o.ataEsp;
		this.defEsp = o.defEsp;
		this.velocidad = o.velocidad;
	}
	
	//GETTERS Y SETTERS

	public int getIdObjeto() {
		return idObjeto;
	}

	public void setIdObjeto(int idObjeto) {
		this.idObjeto = idObjeto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public int getAtaEsp() {
		return ataEsp;
	}

	public void setAtaEsp(int ataEsp) {
		this.ataEsp = ataEsp;
	}

	public int getDefEsp() {
		return defEsp;
	}

	public void setDefEsp(int defEsp) {
		this.defEsp = defEsp;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	
	
	//TOSTRING
	
	@Override
	public String toString() {
		return "Objeto [idObjeto=" + idObjeto + ", nombre=" + nombre + ", ataque=" + ataque + ", defensa=" + defensa
				+ ", ataEsp=" + ataEsp + ", defEsp=" + defEsp + ", velocidad=" + velocidad + "]";
	}
	
	
	
	
    
    

}
