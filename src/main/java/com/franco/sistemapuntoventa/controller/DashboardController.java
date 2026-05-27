package com.franco.sistemapuntoventa.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

import com.franco.sistemapuntoventa.dao.ProductoDAO;
import com.franco.sistemapuntoventa.dao.CompraDAO;
import com.franco.sistemapuntoventa.dao.VentaDAO;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardController {
    @FXML
    private Label lblProductos;

    @FXML
    private Label lblVentas;

    @FXML
    private Label lblCompras;

    private final ProductoDAO productoDAO =
            new ProductoDAO();

    private final CompraDAO compraDAO =
            new CompraDAO();

    private final VentaDAO ventaDAO =
            new VentaDAO();

    public void abrirProductos(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/franco/sistemapuntoventa/view/productos-view.fxml")
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Productos");
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    public void abrirCategorias(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/franco/sistemapuntoventa/view/categorias-view.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Categorías");
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    public void abrirProveedores(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/franco/sistemapuntoventa/view/proveedores-view.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Proveedores");
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void abrirCompras(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/franco/sistemapuntoventa/view/compras-view.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Compras");
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void abrirVentas(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/franco/sistemapuntoventa/view/ventas-view.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Ventas");
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    @FXML
    public void initialize() {

        lblProductos.setText(
                "Productos registrados: "
                        + productoDAO.contarProductos()
        );

        lblVentas.setText(
                "Ventas registradas: "
                        + ventaDAO.contarVentas()
        );

        lblCompras.setText(
                "Compras registradas: "
                        + compraDAO.contarCompras()
        );

    }

}