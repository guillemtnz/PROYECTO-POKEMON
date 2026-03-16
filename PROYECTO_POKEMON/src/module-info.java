module PROYECTO_POKEMON {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
	requires java.sql;

    // Solo deja los paquetes que tengan al menos una clase .java dentro
    opens main to javafx.graphics, javafx.fxml;
    opens controller to javafx.fxml;
    opens view to javafx.fxml;
    
    // Si la carpeta 'proyecto' tiene a Pokemon.java, déjala:
    opens model to javafx.base;

    exports main;
}