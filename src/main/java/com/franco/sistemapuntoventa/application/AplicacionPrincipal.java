package com.franco.sistemapuntoventa.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.franco.sistemapuntoventa.util.ConexionBD;

public class AplicacionPrincipal extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AplicacionPrincipal.class.getResource("/com/franco/sistemapuntoventa/view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Sistema Punto de Venta");
        stage.setScene(scene);
        ConexionBD.getInstancia();
        stage.show();
    }
}
