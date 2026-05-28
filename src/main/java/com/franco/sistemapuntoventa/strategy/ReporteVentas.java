package com.franco.sistemapuntoventa.strategy;

import com.franco.sistemapuntoventa.dao.VentaDAO;

public class ReporteVentas implements EstrategiaReporte {

    private final VentaDAO ventaDAO = new VentaDAO();

    @Override
    public String generarReporte() {

        return """
                REPORTE DE VENTAS
                -----------------------------
                Total de ventas registradas: 
                """ + ventaDAO.contarVentas();

    }

}