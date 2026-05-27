package com.franco.sistemapuntoventa.controller;

import com.franco.sistemapuntoventa.dao.UsuarioDAO;
import com.franco.sistemapuntoventa.model.Usuario;
import com.franco.sistemapuntoventa.util.HashUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasena;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    @FXML
    private void iniciarSesion() {

        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Debes ingresar usuario y contraseña.");
            return;
        }

        String contrasenaHash = HashUtil.convertirSHA1(contrasena);

        Usuario usuarioEncontrado = usuarioDAO.validarLogin(usuario, contrasenaHash);

        if (usuarioEncontrado != null) {
            mostrarAlerta("Acceso correcto", "Bienvenido: " + usuarioEncontrado.getUsername());
        } else {
            mostrarAlerta("Acceso denegado", "Usuario o contraseña incorrectos.");
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}