package controller;

import dao.EntrenadorDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import model.Entrenador;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/** Controlador de la pantalla de Login.
 * Se encarga de gestionar el inicio de sesión y el registro de nuevos entrenadores.*/

public class LoginController {
	
	// Campos de texto vinculados al FXML mediante el @FXML
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensaje;
    
    // Instanciamos del DAO para acceder a los datos de entrenadores en la BD
    private EntrenadorDAO entrenadorDAO = new EntrenadorDAO();
    
    //BOTON DE LOGIN
    @FXML
    public void handleLogin(ActionEvent event) {
        String nombre = txtUsuario.getText().trim();
        String password = txtPassword.getText().trim();

        if (nombre.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Por favor rellena todos los campos");
            lblMensaje.setStyle("-fx-text-fill: white; -fx-font-size: 10px; -fx-font-weight: bold;");
            return;
        }
        
        // Llamo al DAO para verificar las credenciales en la BD
        Entrenador entrenador = entrenadorDAO.login(nombre, password);

        if (entrenador != null) {
        	// Guardo el entrenador logueado para usarlo en otras vistas
        	Entrenador.entrenadorLogueado = entrenador;
        	
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/view/PantallaCarga.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos");
            lblMensaje.setStyle("-fx-text-fill: white; -fx-font-size: 10px; -fx-font-weight: bold;");
        }
    }
    
    // BOTON DE LIMPIAR
    @FXML
    public void handleLimpiar(ActionEvent event) {
        txtUsuario.clear();
        txtPassword.clear();
        lblMensaje.setText("");
    }
    
    //BOTON DE REGISTRO
    @FXML
    public void handleRegistro(ActionEvent event) {
        // Crear ventana emergente
        Stage ventanaRegistro = new Stage();
        ventanaRegistro.setTitle("Registro de Entrenador");
        
        // Creo el panel principal de la ventana emergent
        AnchorPane panel = new AnchorPane();
        panel.setPrefSize(350, 220);
        panel.setStyle("-fx-background-color: #1a1a2e;");
        
        // Título de la ventana
        Label lblTitulo = new Label("Nuevo Entrenador");
        lblTitulo.setLayoutX(100);
        lblTitulo.setLayoutY(15);
        lblTitulo.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        
        // Etiqueta y campo de texto para el nombre
        Label lblNombre = new Label("Nombre:");
        lblNombre.setLayoutX(30);
        lblNombre.setLayoutY(60);
        lblNombre.setStyle("-fx-text-fill: white;");
        
        TextField txtNombre = new TextField();
        txtNombre.setLayoutX(120);
        txtNombre.setLayoutY(56);
        txtNombre.setPrefWidth(180);
        
        // Etiqueta y campo de contraseña
        Label lblPass = new Label("Contraseña:");
        lblPass.setLayoutX(30);
        lblPass.setLayoutY(100);
        lblPass.setStyle("-fx-text-fill: white;");

        PasswordField txtPass = new PasswordField();
        txtPass.setLayoutX(120);
        txtPass.setLayoutY(96);
        txtPass.setPrefWidth(180);
        
        // Label para mostrar mensajes de error o confirmacion
        Label lblInfo = new Label("");
        lblInfo.setLayoutX(30);
        lblInfo.setLayoutY(135);
        lblInfo.setPrefWidth(290);
        lblInfo.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");
        
        // Botón para confirmar el registro
        Button btnConfirmar = new Button("Registrar");
        btnConfirmar.setLayoutX(100);
        btnConfirmar.setLayoutY(165);
        btnConfirmar.setPrefWidth(150);
        btnConfirmar.setStyle("-fx-background-color: #1a237e; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");
        
        // Acción del botón Registrar
        btnConfirmar.setOnAction(e -> {
            String nombre = txtNombre.getText().trim();
            String password = txtPass.getText().trim();
            
            // Validamos que los campos no estén vacíos
            if (nombre.isEmpty() || password.isEmpty()) {
                lblInfo.setText("Rellena todos los campos.");
                lblInfo.setStyle("-fx-text-fill: #ff4444; -fx-font-size: 10px; -fx-font-weight: bold;");
                return;
            }
            
            // Comprobamos que el nombre no esté ya registrado en la base de datos
            if (entrenadorDAO.existeEntrenador(nombre)) {
                lblInfo.setText("Ese nombre ya existe.");
                lblInfo.setStyle("-fx-text-fill: #ff4444; -fx-font-size: 10px; -fx-font-weight: bold;");
                return;
            }
            
            // Llamamos al DAO para insertar el nuevo entrenador en la BD
            boolean registrado = entrenadorDAO.registrar(nombre, password);
            if (registrado) {
                lblInfo.setText("Registro correcto! Ya puedes iniciar sesion.");
                lblInfo.setStyle("-fx-text-fill: #44dd44; -fx-font-size: 10px; -fx-font-weight: bold;");
            } else {
                lblInfo.setText("Error al registrar. Intentalo de nuevo.");
                lblInfo.setStyle("-fx-text-fill: #ff4444; -fx-font-size: 10px; -fx-font-weight: bold;");
            }
        });

        panel.getChildren().addAll(lblTitulo, lblNombre, txtNombre, lblPass, txtPass, lblInfo, btnConfirmar);

        Scene escena = new Scene(panel);
        ventanaRegistro.setScene(escena);
        ventanaRegistro.setResizable(false);
        ventanaRegistro.show();
    }
}