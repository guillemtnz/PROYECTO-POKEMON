package main;

import dao.PokemonDAO;
import model.Pokemon;

public class MainPruebaConexion {
	
	public static void main(String[] args) {
		
		PokemonDAO dao = new PokemonDAO();
        
        System.out.println("--- BUSCANDO POKÉMON SALVAJE ---");
        
        Pokemon salvaje = dao.generarPokemonAleatorio();
        
        if (salvaje != null) {
            System.out.println("¡Un " + salvaje.getNombre() + " salvaje apareció!");
            System.out.println("Nivel: " + salvaje.getNivel());
            System.out.println("Sexo: " + salvaje.getSexo());
            
            double suerte = Math.random();
            System.out.println("\nLanzando Pokéball...");
            
            if (suerte <= (2.0/3.0)) {
                System.out.println("¡Tatatachán! " + salvaje.getNombre() + " ha sido atrapado.");
            } else {
                System.out.println("¡Nooo! El Pokémon rompió la bola y huyó.");
            }
            
        } else {
            System.out.println("No se pudo conectar con la Pokedex. Revisa XAMPP.");
        }
    }

}
