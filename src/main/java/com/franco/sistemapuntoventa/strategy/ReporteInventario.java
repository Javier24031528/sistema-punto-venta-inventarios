package com.franco.sistemapuntoventa.strategy;

import com.franco.sistemapuntoventa.dao.ProductoDAO;
import com.franco.sistemapuntoventa.model.Producto;

public class ReporteInventario implements EstrategiaReporte {

    private final ProductoDAO productoDAO = new ProductoDAO();

    @Override
    public String generarReporte() {

        StringBuilder reporte = new StringBuilder();

        reporte.append("REPORTE DE INVENTARIO\n");
        reporte.append("-----------------------------\n");

        for (Producto producto : productoDAO.listar()) {
            reporte.append("Producto: ")
                    .append(producto.getNombre())
                    .append(" | Stock: ")
                    .append(producto.getStockActual())
                    .append("\n");
        }

        return reporte.toString();
    }
}