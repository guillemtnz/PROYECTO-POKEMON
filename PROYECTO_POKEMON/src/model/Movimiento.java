package model;


public abstract class Movimiento {
	
	public enum MecanicaEspecial {
        SUICIDIO, RECOIL, DRENAJE, MULTIGOLPE, CLIMA, MISMO_DESTINO
    }

	protected int idMovimiento;
	protected String nombreMovimiento;
	protected Tipo tipoMovimiento; //definida en la clase Tipo
	protected int nivel;
	protected int prioridad;
	protected int precision;
	protected int pp;
	protected String desc; //breve descripción del movimiento
	
	protected MecanicaEspecial mecanicaEspecial;
    protected int valorMecanica;
	
	//CONSTRUCTOR POR DEFECTO
	public Movimiento() {
		super();
		this.idMovimiento = 0;
		this.nombreMovimiento = "";
		this.tipoMovimiento = null;
		this.prioridad = 0;
		this.precision = 100;
		this.pp = 40;
		this.nivel = 0;
		this.desc = "";
		this.mecanicaEspecial = null;
		this.valorMecanica = 0;
	}

	//CONSTRUCTOR CON TODOS LOS ATRIBUTOS
	public Movimiento(int idMovimiento, String nombreMovimiento, Tipo tipoMovimiento, int nivel, 
						int prioridad, int precision, int pp, String desc, MecanicaEspecial mecanicaEspecial, int valorMecanica) {
		super();
		this.idMovimiento = idMovimiento;
		this.nombreMovimiento = nombreMovimiento;
		this.tipoMovimiento = tipoMovimiento;
		this.nivel = nivel;
		this.prioridad = prioridad;
		this.precision = precision;
		this.pp = pp;
		this.desc = desc;
		this.mecanicaEspecial = mecanicaEspecial;
		this.valorMecanica = valorMecanica;
	}
	
	//CONSTRUCTOR COPIA  (no creo que se use nunca)
	public Movimiento(Movimiento m) {
		super();
		this.idMovimiento = m.idMovimiento;
		this.nombreMovimiento = m.nombreMovimiento;
		this.tipoMovimiento = m.tipoMovimiento;
		this.nivel = m.nivel;
		this.prioridad = m.prioridad;
		this.precision = m.precision;
		this.pp = m.pp;
		this.desc = m.desc;
		this.mecanicaEspecial = m.mecanicaEspecial;
		this.valorMecanica = m.valorMecanica;
	}
	
	//GETTERS Y SETTERS

	public int getIdMovimiento() {
		return idMovimiento;
	}

	public void setIdMovimiento(int idMovimiento) {
		this.idMovimiento = idMovimiento;
	}

	public String getNombreMovimiento() {
		return nombreMovimiento;
	}

	public void setNombreMovimiento(String nombreMovimiento) {
		this.nombreMovimiento = nombreMovimiento;
	}

	public Tipo getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(Tipo tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}


	public int getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}


	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getPp() {
		return pp;
	}

	public void setPp(int pp) {
		this.pp = pp;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}


	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	public MecanicaEspecial getMecanicaEspecial() {
		return mecanicaEspecial;
	}

	public void setMecanicaEspecial(MecanicaEspecial mecanicaEspecial) {
		this.mecanicaEspecial = mecanicaEspecial;
	}

	public int getValorMecanica() {
		return valorMecanica;
	}

	public void setValorMecanica(int valorMecanica) {
		this.valorMecanica = valorMecanica;
	}

	public abstract void ejecutarMovimiento();

}