package com.franco.sistemapuntoventa.controller;

import com.franco.sistemapuntoventa.dao.CategoriaDAO;
import com.franco.sistemapuntoventa.model.Categoria;

import javafx.collections.FXCollections;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class CategoriaController {

    @FXML
    private TextField txtIdCategoria;

    @FXML
    private TextField txtNombreCategoria;

    @FXML
    private TableView<Categoria> tablaCategorias;

    @FXML
    private TableColumn<Categoria, Integer> colIdCategoria;

    @FXML
    private TableColumn<Categoria, String> colNombreCategoria;

    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    @FXML
    public void initialize() {

        configurarTabla();
        cargarCategorias();

        tablaCategorias.setOnMouseClicked(event -> seleccionarCategoria());

    }

    private void configurarTabla() {

        colIdCategoria.setCellValueFactory(
                new PropertyValueFactory<>("idCategoria")
        );

        colNombreCategoria.setCellValueFactory(
                new PropertyValueFactory<>("nombreCategoria")
        );

    }

    private void cargarCategorias() {

        tablaCategorias.setItems(
                FXCollections.observableArrayList(
                        categoriaDAO.listar()
                )
        );

    }

    @FXML
    private void guardarCategoria() {

        try {

            Categoria categoria = new Categoria(
                    Integer.parseInt(txtIdCategoria.getText()),
                    txtNombreCategoria.getText()
            );

            categoriaDAO.insertar(categoria);

            cargarCategorias();
            limpiarCampos();

            mostrarAlerta(
                    "Categoría guardada",
                    "La categoría se registró correctamente."
            );

        } catch (Exception e) {

            mostrarAlerta(
                    "Error",
                    "Verifica los datos ingresados."
            );

        }

    }

    @FXML
    private void actualizarCategoria() {

        try {

            Categoria categoria = new Categoria(
                    Integer.parseInt(txtIdCategoria.getText()),
                    txtNombreCategoria.getText()
            );

            categoriaDAO.actualizar(categoria);

            cargarCategorias();
            limpiarCampos();

            mostrarAlerta(
                    "Categoría actualizada",
                    "La categoría se actualizó correctamente."
            );

        } catch (Exception e) {

            mostrarAlerta(
                    "Error",
                    "No se pudo actualizar la categoría."
            );

        }

    }

    @FXML
    private void eliminarCategoria() {

        try {

            int id = Integer.parseInt(
                    txtIdCategoria.getText()
            );

            categoriaDAO.eliminar(id);

            cargarCategorias();
            limpiarCampos();

            mostrarAlerta(
                    "Categoría eliminada",
                    "La categoría se eliminó correctamente."
            );

        } catch (Exception e) {

            mostrarAlerta(
                    "Error",
                    "No se pudo eliminar la categoría."
            );

        }

    }

    @FXML
    private void limpiarCampos() {

        txtIdCategoria.clear();
        txtNombreCategoria.clear();

    }

    private void seleccionarCategoria() {

        Categoria categoria = tablaCategorias
                .getSelectionModel()
                .getSelectedItem();

        if (categoria != null) {

            txtIdCategoria.setText(
                    String.valueOf(categoria.getIdCategoria())
            );

            txtNombreCategoria.setText(
                    categoria.getNombreCategoria()
            );

        }

    }

    private void mostrarAlerta(
            String titulo,
            String mensaje
    ) {

        Alert alerta = new Alert(
                Alert.AlertType.INFORMATION
        );

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