package com.example.tareaguiajavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class loginController {

    // Credenciales
    private final String usuarioCorrecto = "Admin";
    private final String passwordCorrecto = "1234";

    // TextField
    @FXML
    private TextField txtUsuario;

    @FXML
    private TextField txtPassword;

    // Botón
    @FXML
    private Button btnValidar;


    //Iniciar Sesion
    @FXML
    private void inicioSesion() {

        String usuarioIngresado = txtUsuario.getText().trim();
        String passwordIngresado = txtPassword.getText().trim();
        // Validar campos vacíos
        if (usuarioIngresado.isEmpty() || passwordIngresado.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Campos vacíos");
            alerta.setHeaderText(null);
            alerta.setContentText("Debe ingresar usuario y contraseña.");
            alerta.showAndWait();
            return;
        }
        // Validar credenciales
        if (usuarioIngresado.equals(usuarioCorrecto)
                && passwordIngresado.equals(passwordCorrecto)) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Inicio de sesión");
            alerta.setHeaderText(null);
            alerta.setContentText("Bienvenido al sistema.");
            alerta.showAndWait();
            // Abrir la ventana CRUD
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de acceso");
            alerta.setHeaderText(null);
            alerta.setContentText("Usuario o contraseña incorrectos.");
            alerta.showAndWait();
        }
    }
}