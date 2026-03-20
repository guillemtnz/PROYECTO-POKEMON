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


public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensaje;

    private EntrenadorDAO entrenadorDAO = new EntrenadorDAO();

    @FXML
    public void handleLogin(ActionEvent event) {
        String nombre = txtUsuario.getText().trim();
        String password = txtPassword.getText().trim();

        if (nombre.isEmpty() || password.isEmpty()) {
            lblMensaje.setText("Por favor rellena todos los campos, inútil");
            lblMensaje.setStyle("-fx-text-fill: white; -fx-font-size: 10px; -fx-font-weight: bold;");
            return;
        }

        Entrenador entrenador = entrenadorDAO.login(nombre, password);

        if (entrenador != null) {
        	
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
            lblMensaje.setText("Usuario o contraseña incorrectos. Bobo o que");
            lblMensaje.setStyle("-fx-text-fill: white; -fx-font-size: 10px; -fx-font-weight: bold;");
        }
    }

    @FXML
    public void handleLimpiar(ActionEvent event) {
        txtUsuario.clear();
        txtPassword.clear();
        lblMensaje.setText("");
    }

    @FXML
    public void handleRegistro(ActionEvent event) {
        // Crear ventana emergente
        Stage ventanaRegistro = new Stage();
        ventanaRegistro.setTitle("Registro de Entrenador");

        AnchorPane panel = new AnchorPane();
        panel.setPrefSize(350, 220);
        panel.setStyle("-fx-background-color: #1a1a2e;");

        Label lblTitulo = new Label("Nuevo Entrenador");
        lblTitulo.setLayoutX(100);
        lblTitulo.setLayoutY(15);
        lblTitulo.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

        Label lblNombre = new Label("Nombre:");
        lblNombre.setLayoutX(30);
        lblNombre.setLayoutY(60);
        lblNombre.setStyle("-fx-text-fill: white;");

        TextField txtNombre = new TextField();
        txtNombre.setLayoutX(120);
        txtNombre.setLayoutY(56);
        txtNombre.setPrefWidth(180);

        Label lblPass = new Label("Contraseña:");
        lblPass.setLayoutX(30);
        lblPass.setLayoutY(100);
        lblPass.setStyle("-fx-text-fill: white;");

        PasswordField txtPass = new PasswordField();
        txtPass.setLayoutX(120);
        txtPass.setLayoutY(96);
        txtPass.setPrefWidth(180);

        Label lblInfo = new Label("");
        lblInfo.setLayoutX(30);
        lblInfo.setLayoutY(135);
        lblInfo.setPrefWidth(290);
        lblInfo.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");

        Button btnConfirmar = new Button("Registrar");
        btnConfirmar.setLayoutX(100);
        btnConfirmar.setLayoutY(165);
        btnConfirmar.setPrefWidth(150);
        btnConfirmar.setStyle("-fx-background-color: #1a237e; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8;");

        btnConfirmar.setOnAction(e -> {
            String nombre = txtNombre.getText().trim();
            String password = txtPass.getText().trim();

            if (nombre.isEmpty() || password.isEmpty()) {
                lblInfo.setText("Rellena todos los campos.");
                lblInfo.setStyle("-fx-text-fill: #ff4444; -fx-font-size: 10px; -fx-font-weight: bold;");
                return;
            }

            if (entrenadorDAO.existeEntrenador(nombre)) {
                lblInfo.setText("Ese nombre ya existe.");
                lblInfo.setStyle("-fx-text-fill: #ff4444; -fx-font-size: 10px; -fx-font-weight: bold;");
                return;
            }

            boolean registrado = entrenadorDAO.registrar(nombre, password);
            if (registrado) {
                lblInfo.setText("Registro exitoso chaval! Ya puedes iniciar sesion.");
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