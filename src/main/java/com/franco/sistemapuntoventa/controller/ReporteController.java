package com.franco.sistemapuntoventa.controller;

import com.franco.sistemapuntoventa.strategy.EstrategiaReporte;
import com.franco.sistemapuntoventa.strategy.ReporteCompras;
import com.franco.sistemapuntoventa.strategy.ReporteInventario;
import com.franco.sistemapuntoventa.strategy.ReporteVentas;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

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
}