package com.franco.sistemapuntoventa.controller;

import com.franco.sistemapuntoventa.dao.CompraDAO;
import com.franco.sistemapuntoventa.dao.ProductoDAO;
import com.franco.sistemapuntoventa.dao.ProveedorDAO;

import com.franco.sistemapuntoventa.model.Compra;
import com.franco.sistemapuntoventa.model.DetalleCompra;
import com.franco.sistemapuntoventa.model.Producto;
import com.franco.sistemapuntoventa.model.Proveedor;
import com.franco.sistemapuntoventa.model.Usuario;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.time.LocalDateTime;

public class CompraController {

    @FXML
    private TextField txtIdCompra;

    @FXML
    private TextField txtIdDetalle;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtCostoUnitario;

    @FXML
    private ComboBox<Proveedor> cmbProveedor;

    @FXML
    private ComboBox<Producto> cmbProducto;

    private final CompraDAO compraDAO = new CompraDAO();

    private final ProductoDAO productoDAO =
            new ProductoDAO();

    private final ProveedorDAO proveedorDAO =
            new ProveedorDAO();

    @FXML
    public void initialize() {

        cargarProductos();
        cargarProveedores();

    }

    private void cargarProductos() {

        cmbProducto.setItems(
                FXCollections.observableArrayList(
                        productoDAO.listar()
                )
        );

    }

    private void cargarProveedores() {

        cmbProveedor.setItems(
                FXCollections.observableArrayList(
                        proveedorDAO.listar()
                )
        );

    }

    @FXML
    private void registrarCompra() {

        try {

            Producto producto =
                    cmbProducto.getValue();

            Proveedor proveedor =
                    cmbProveedor.getValue();

            Usuario usuario =
                    new Usuario();

            usuario.setIdUsuario(1);

            Compra compra = new Compra(
                    Integer.parseInt(
                            txtIdCompra.getText()
                    ),
                    LocalDateTime.now(),
                    0,
                    proveedor,
                    usuario
            );

            DetalleCompra detalleCompra =
                    new DetalleCompra(
                            Integer.parseInt(
                                    txtIdDetalle.getText()
                            ),
                            Integer.parseInt(
                                    txtCantidad.getText()
                            ),
                            Double.parseDouble(
                                    txtCostoUnitario.getText()
                            ),
                            compra,
                            producto
                    );

            compraDAO.registrarCompra(
                    detalleCompra
            );

            mostrarAlerta(
                    "Compra registrada",
                    "La compra se registró correctamente."
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

        txtIdCompra.clear();
        txtIdDetalle.clear();
        txtCantidad.clear();
        txtCostoUnitario.clear();

        cmbProveedor.setValue(null);
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