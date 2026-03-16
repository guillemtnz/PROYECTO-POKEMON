package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    // 1. Creamos un método para que el código tenga un "hogar"
    public static Connection conectar() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/pokemon_db";
        String login = "root";
        String password = "";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, login, password);
            System.out.println("Conexión establecida");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error al conectar");
            e.printStackTrace();
        }
        
        // 2. IMPORTANTE: No cierres la conexión aquí (finally) 
        // Si la cierras ahora, no podrás usarla para el Login.
        
        return connection; 
    }
}