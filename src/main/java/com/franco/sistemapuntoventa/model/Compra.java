package com.franco.sistemapuntoventa.model;

import java.time.LocalDateTime;

public class Compra {

    private int idCompra;
    private LocalDateTime fechaCompra;
    private double totalCompra;

    private Proveedor proveedor;
    private Usuario usuario;

    public Compra() {
    }

    public Compra(
            int idCompra,
            LocalDateTime fechaCompra,
            double totalCompra,
            Proveedor proveedor,
            Usuario usuario
    ) {

        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
        this.proveedor = proveedor;
        this.usuario = usuario;

    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}