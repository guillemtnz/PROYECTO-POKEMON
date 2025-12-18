package proyecto;

public abstract class Combate {
	
	protected Pokemon miPokemon;
	protected Pokemon pokemonRival;
	
	protected Movimiento movimiento;
	
	public void atacar(Pokemon yo, Pokemon rival, Movimiento ataque){
		
	};
	
	public void usarObjeto() {
		
	};
	
	public void cambiarPokemon(){
		
	}
	
	public void huir() {
		
		System.out.println("No se puede huir de un combate contra un entrenador!!");
	
		
	}

}
