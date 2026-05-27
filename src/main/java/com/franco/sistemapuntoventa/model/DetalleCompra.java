package com.franco.sistemapuntoventa.model;

public class DetalleCompra {

    private int idDetalleC;
    private int cantidad;
    private double costoUnitario;
    private Compra compra;
    private Producto producto;

    public DetalleCompra() {
    }

    public DetalleCompra(int idDetalleC, int cantidad, double costoUnitario, Compra compra, Producto producto) {
        this.idDetalleC = idDetalleC;
        this.cantidad = cantidad;
        this.costoUnitario = costoUnitario;
        this.compra = compra;
        this.producto = producto;
    }

    public int getIdDetalleC() {
        return idDetalleC;
    }

    public void setIdDetalleC(int idDetalleC) {
        this.idDetalleC = idDetalleC;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double calcularSubtotal() {
        return cantidad * costoUnitario;
    }
}