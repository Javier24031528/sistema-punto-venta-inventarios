package com.franco.sistemapuntoventa.controller;

import com.franco.sistemapuntoventa.dao.ProductoDAO;
import com.franco.sistemapuntoventa.dao.VentaDAO;

import com.franco.sistemapuntoventa.model.DetalleVenta;
import com.franco.sistemapuntoventa.model.Producto;
import com.franco.sistemapuntoventa.model.Usuario;
import com.franco.sistemapuntoventa.model.Venta;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class VentaController {

    @FXML
    private TextField txtIdVenta;

    @FXML
    private TextField txtIdDetalleVenta;

    @FXML
    private TextField txtCantidad;

    @FXML
    private ComboBox<Producto> cmbProducto;

    private final VentaDAO ventaDAO =
            new VentaDAO();

    private final ProductoDAO productoDAO =
            new ProductoDAO();

    @FXML
    public void initialize() {

        cargarProductos();

    }

    private void cargarProductos() {

        cmbProducto.setItems(
                FXCollections.observableArrayList(
                        productoDAO.listar()
                )
        );

    }

    @FXML
    private void registrarVenta() {

        try {

            Producto producto =
                    cmbProducto.getValue();

            int cantidad =
                    Integer.parseInt(
                            txtCantidad.getText()
                    );

            if (!producto.validarStock(cantidad)) {

                mostrarAlerta(
                        "Stock insuficiente",
                        "No hay suficiente inventario."
                );

                return;

            }

            Usuario usuario =
                    new Usuario();

            usuario.setIdUsuario(1);

            Venta venta = new Venta(
                    Integer.parseInt(
                            txtIdVenta.getText()
                    ),
                    LocalDateTime.now(),
                    0,
                    usuario
            );

            DetalleVenta detalleVenta =
                    new DetalleVenta(
                            Integer.parseInt(
                                    txtIdDetalleVenta.getText()
                            ),
                            cantidad,
                            0,
                            venta,
                            producto
                    );

            ventaDAO.registrarVenta(
                    detalleVenta
            );

            mostrarAlerta(
                    "Venta registrada",
                    "La venta se registró correctamente."
            );

            limpiarCampos();

        } catch (Exception e) {

            mostrarAlerta(
                    "Error",
                    "Verifica los datos ingresados."
            );

            e.printStackTrace();

        }

    }

    @FXML
    private void limpiarCampos() {

        txtIdVenta.clear();
        txtIdDetalleVenta.clear();
        txtCantidad.clear();

        cmbProducto.setValue(null);

    }

    private void mostrarAlerta(
            String titulo,
            String mensaje
    ) {

        Alert alerta =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();

    }

}