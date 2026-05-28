package com.franco.sistemapuntoventa.strategy;

import com.franco.sistemapuntoventa.dao.CompraDAO;

public class ReporteCompras implements EstrategiaReporte {

    private final CompraDAO compraDAO = new CompraDAO();

    @Override
    public String generarReporte() {

        return """
                REPORTE DE COMPRAS
                -----------------------------
                Total de compras registradas:
                """ + compraDAO.contarCompras();

    }

}