package com.franco.sistemapuntoventa.model;

public class Proveedor {

    private int idProveedor;
    private String razonSocial;
    private String telefono;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, String razonSocial, String telefono) {
        this.idProveedor = idProveedor;
        this.razonSocial = razonSocial;
        this.telefono = telefono;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}