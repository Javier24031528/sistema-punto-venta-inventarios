package com.franco.sistemapuntoventa.model;

public class Producto {

    private int idProducto;
    private String codigoBarras;
    private String nombre;
    private double precioVenta;
    private int stockActual;
    private Categoria categoria;

    public Producto() {
    }

    public Producto(int idProducto, String codigoBarras, String nombre, double precioVenta, int stockActual, Categoria categoria) {
        this.idProducto = idProducto;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.stockActual = stockActual;
        this.categoria = categoria;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean validarStock(int cantidad) {
        return stockActual >= cantidad;
    }

    public void aumentarStock(int cantidad) {
        stockActual += cantidad;
    }

    public boolean disminuirStock(int cantidad) {
        if (validarStock(cantidad)) {
            stockActual -= cantidad;
            return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return nombre;
    }
}