package com.franco.sistemapuntoventa.model;

import java.time.LocalDateTime;

public class Venta {

    private int idVenta;
    private LocalDateTime fechaHora;
    private double total;
    private Usuario usuario;

    public Venta() {
    }

    public Venta(int idVenta, LocalDateTime fechaHora, double total, Usuario usuario) {
        this.idVenta = idVenta;
        this.fechaHora = fechaHora;
        this.total = total;
        this.usuario = usuario;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}