package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1. Cargar el archivo FXML desde la carpeta view
            // El "/" inicial busca desde la raíz del proyecto (carpeta src)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            Parent root = loader.load();

            // 2. Crear la escena con el contenido del FXML
            Scene scene = new Scene(root);

            // 3. Configurar y mostrar la ventana
            primaryStage.setTitle("Pokedex - Inicio de Sesión");
            primaryStage.setScene(scene);
            
            // Si quieres que no se pueda cambiar el tamaño de la ventana:
            primaryStage.setResizable(false);
            
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ERROR: No se pudo cargar el archivo Login.fxml. Verifica el nombre y la carpeta.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}