package com.franco.sistemapuntoventa.model;

public class DetalleVenta {

    private int idDetalleV;
    private int cantidad;
    private double subtotal;
    private Venta venta;
    private Producto producto;

    public DetalleVenta() {
    }

    public DetalleVenta(int idDetalleV, int cantidad, double subtotal, Venta venta, Producto producto) {
        this.idDetalleV = idDetalleV;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.venta = venta;
        this.producto = producto;
    }

    public int getIdDetalleV() {
        return idDetalleV;
    }

    public void setIdDetalleV(int idDetalleV) {
        this.idDetalleV = idDetalleV;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double calcularSubtotal() {
        return cantidad * producto.getPrecioVenta();
    }
}