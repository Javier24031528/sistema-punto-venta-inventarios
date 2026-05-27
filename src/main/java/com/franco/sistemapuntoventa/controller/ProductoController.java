package com.franco.sistemapuntoventa.controller;

import com.franco.sistemapuntoventa.dao.CategoriaDAO;
import com.franco.sistemapuntoventa.dao.ProductoDAO;
import com.franco.sistemapuntoventa.model.Categoria;
import com.franco.sistemapuntoventa.model.Producto;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductoController {

    @FXML private TextField txtIdProducto;
    @FXML private TextField txtCodigoBarras;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecioVenta;
    @FXML private TextField txtStockActual;
    @FXML private ComboBox<Categoria> cmbCategoria;

    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, Integer> colId;
    @FXML private TableColumn<Producto, String> colCodigo;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, Categoria> colCategoria;

    private final ProductoDAO productoDAO = new ProductoDAO();
    private final CategoriaDAO categoriaDAO = new CategoriaDAO();

    @FXML
    public void initialize() {
        configurarTabla();
        cargarCategorias();
        cargarProductos();

        tablaProductos.setOnMouseClicked(event -> seleccionarProducto());
    }

    private void configurarTabla() {
        colId.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoBarras"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stockActual"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
    }

    private void cargarCategorias() {
        cmbCategoria.setItems(FXCollections.observableArrayList(categoriaDAO.listar()));
    }

    private void cargarProductos() {
        tablaProductos.setItems(FXCollections.observableArrayList(productoDAO.listar()));
    }

    @FXML
    private void guardarProducto() {
        try {
            Producto producto = obtenerProductoDesdeFormulario();
            productoDAO.insertar(producto);
            cargarProductos();
            limpiarCampos();
            mostrarAlerta("Producto guardado", "El producto se registró correctamente.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Verifica que los datos sean correctos.");
        }
    }

    @FXML
    private void actualizarProducto() {
        try {
            Producto producto = obtenerProductoDesdeFormulario();
            productoDAO.actualizar(producto);
            cargarProductos();
            limpiarCampos();
            mostrarAlerta("Producto actualizado", "El producto se actualizó correctamente.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Selecciona un producto y verifica los datos.");
        }
    }

    @FXML
    private void eliminarProducto() {
        try {
            int id = Integer.parseInt(txtIdProducto.getText());
            productoDAO.eliminar(id);
            cargarProductos();
            limpiarCampos();
            mostrarAlerta("Producto eliminado", "El producto se eliminó correctamente.");
        } catch (Exception e) {
            mostrarAlerta("Error", "Selecciona un producto válido.");
        }
    }

    @FXML
    private void limpiarCampos() {
        txtIdProducto.clear();
        txtCodigoBarras.clear();
        txtNombre.clear();
        txtPrecioVenta.clear();
        txtStockActual.clear();
        cmbCategoria.setValue(null);
    }

    private Producto obtenerProductoDesdeFormulario() {
        return new Producto(
                Integer.parseInt(txtIdProducto.getText()),
                txtCodigoBarras.getText(),
                txtNombre.getText(),
                Double.parseDouble(txtPrecioVenta.getText()),
                Integer.parseInt(txtStockActual.getText()),
                cmbCategoria.getValue()
        );
    }

    private void seleccionarProducto() {
        Producto producto = tablaProductos.getSelectionModel().getSelectedItem();

        if (producto != null) {
            txtIdProducto.setText(String.valueOf(producto.getIdProducto()));
            txtCodigoBarras.setText(producto.getCodigoBarras());
            txtNombre.setText(producto.getNombre());
            txtPrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
            txtStockActual.setText(String.valueOf(producto.getStockActual()));
            cmbCategoria.setValue(producto.getCategoria());
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