package com.franco.sistemapuntoventa.controller;

import com.franco.sistemapuntoventa.dao.ProveedorDAO;
import com.franco.sistemapuntoventa.model.Proveedor;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class ProveedorController {

    @FXML private TextField txtIdProveedor;
    @FXML private TextField txtRazonSocial;
    @FXML private TextField txtTelefono;

    @FXML private TableView<Proveedor> tablaProveedores;
    @FXML private TableColumn<Proveedor, Integer> colIdProveedor;
    @FXML private TableColumn<Proveedor, String> colRazonSocial;
    @FXML private TableColumn<Proveedor, String> colTelefono;

    private final ProveedorDAO proveedorDAO = new ProveedorDAO();

    @FXML
    public void initialize() {
        configurarTabla();
        cargarProveedores();
        tablaProveedores.setOnMouseClicked(event -> seleccionarProveedor());
    }

    private void configurarTabla() {
        colIdProveedor.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        colRazonSocial.setCellValueFactory(new PropertyValueFactory<>("razonSocial"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    private void cargarProveedores() {
        tablaProveedores.setItems(FXCollections.observableArrayList(proveedorDAO.listar()));
    }

    @FXML
    private void guardarProveedor() {
        try {
            Proveedor proveedor = obtenerProveedorDesdeFormulario();
            proveedorDAO.insertar(proveedor);
            cargarProveedores();
            limpiarCampos();
            mostrarAlerta("Proveedor guardado", "El proveedor se registró correctamente.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Verifica los datos ingresados.");
        }
    }

    @FXML
    private void actualizarProveedor() {
        try {
            Proveedor proveedor = obtenerProveedorDesdeFormulario();
            proveedorDAO.actualizar(proveedor);
            cargarProveedores();
            limpiarCampos();
            mostrarAlerta("Proveedor actualizado", "El proveedor se actualizó correctamente.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo actualizar el proveedor.");
        }
    }

    @FXML
    private void eliminarProveedor() {
        try {
            int id = Integer.parseInt(txtIdProveedor.getText());
            proveedorDAO.eliminar(id);
            cargarProveedores();
            limpiarCampos();
            mostrarAlerta("Proveedor eliminado", "El proveedor se eliminó correctamente.");
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo eliminar el proveedor.");
        }
    }

    @FXML
    private void limpiarCampos() {
        txtIdProveedor.clear();
        txtRazonSocial.clear();
        txtTelefono.clear();
    }

    private Proveedor obtenerProveedorDesdeFormulario() {
        return new Proveedor(
                Integer.parseInt(txtIdProveedor.getText()),
                txtRazonSocial.getText(),
                txtTelefono.getText()
        );
    }

    private void seleccionarProveedor() {
        Proveedor proveedor = tablaProveedores.getSelectionModel().getSelectedItem();

        if (proveedor != null) {
            txtIdProveedor.setText(String.valueOf(proveedor.getIdProveedor()));
            txtRazonSocial.setText(proveedor.getRazonSocial());
            txtTelefono.setText(proveedor.getTelefono());
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
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