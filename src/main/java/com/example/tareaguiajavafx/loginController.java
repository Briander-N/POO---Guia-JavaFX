package com.example.tareaguiajavafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    private void inicioSesion() throws IOException {

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

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("crud.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 500);
            Stage stage = (Stage) btnValidar.getScene().getWindow();
            stage.setTitle("CRUD");
            stage.setScene(scene);
            stage.show();

        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de acceso");
            alerta.setHeaderText(null);
            alerta.setContentText("Usuario o contraseña incorrectos.");
            alerta.showAndWait();
        }
    }
}