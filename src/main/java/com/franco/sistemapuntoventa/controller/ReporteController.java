package com.franco.sistemapuntoventa.controller;

import com.franco.sistemapuntoventa.strategy.EstrategiaReporte;
import com.franco.sistemapuntoventa.strategy.ReporteCompras;
import com.franco.sistemapuntoventa.strategy.ReporteInventario;
import com.franco.sistemapuntoventa.strategy.ReporteVentas;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ReporteController {

    @FXML
    private ComboBox<String> cmbTipoReporte;

    @FXML
    private TextArea txtReporte;

    @FXML
    public void initialize() {
        cmbTipoReporte.getItems().addAll(
                "Inventario",
                "Ventas",
                "Compras"
        );
    }

    @FXML
    private void generarReporte() {

        String tipo = cmbTipoReporte.getValue();

        if (tipo == null) {
            txtReporte.setText("Selecciona un tipo de reporte.");
            return;
        }

        EstrategiaReporte estrategia;

        switch (tipo) {
            case "Inventario":
                estrategia = new ReporteInventario();
                break;

            case "Ventas":
                estrategia = new ReporteVentas();
                break;

            case "Compras":
                estrategia = new ReporteCompras();
                break;

            default:
                txtReporte.setText("Tipo de reporte no válido.");
                return;
        }

        txtReporte.setText(estrategia.generarReporte());
    }

    @FXML
    private void regresarDashboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/franco/sistemapuntoventa/view/dashboard-view.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}