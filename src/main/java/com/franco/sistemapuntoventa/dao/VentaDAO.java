package com.franco.sistemapuntoventa.dao;

import com.franco.sistemapuntoventa.model.DetalleVenta;
import com.franco.sistemapuntoventa.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentaDAO {

    private final Connection conexion;

    public VentaDAO() {
        conexion = ConexionBD.getInstancia().getConexion();
    }

    public void registrarVenta(DetalleVenta detalleVenta) {

        String sqlVenta = """
                insert into ventas(
                    id_venta,
                    fecha_hora,
                    total,
                    id_usuario
                )
                values (?, now(), ?, ?)
                """;

        String sqlDetalle = """
                insert into detalle_ventas(
                    id_detalle_v,
                    cantidad,
                    subtotal,
                    id_venta,
                    id_producto
                )
                values (?, ?, ?, ?, ?)
                """;

        String sqlActualizarStock = """
                update productos
                set stock_actual = stock_actual - ?
                where id_producto = ?
                """;

        try {
            PreparedStatement psVenta = conexion.prepareStatement(sqlVenta);
            psVenta.setInt(1, detalleVenta.getVenta().getIdVenta());
            psVenta.setDouble(2, detalleVenta.calcularSubtotal());
            psVenta.setInt(3, detalleVenta.getVenta().getUsuario().getIdUsuario());
            psVenta.executeUpdate();

            PreparedStatement psDetalle = conexion.prepareStatement(sqlDetalle);
            psDetalle.setInt(1, detalleVenta.getIdDetalleV());
            psDetalle.setInt(2, detalleVenta.getCantidad());
            psDetalle.setDouble(3, detalleVenta.calcularSubtotal());
            psDetalle.setInt(4, detalleVenta.getVenta().getIdVenta());
            psDetalle.setInt(5, detalleVenta.getProducto().getIdProducto());
            psDetalle.executeUpdate();

            PreparedStatement psStock = conexion.prepareStatement(sqlActualizarStock);
            psStock.setInt(1, detalleVenta.getCantidad());
            psStock.setInt(2, detalleVenta.getProducto().getIdProducto());
            psStock.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public int contarVentas() {

        String sql = """
            select count(*) total
            from ventas
            """;

        try (PreparedStatement ps =
                     conexion.prepareStatement(sql);

             ResultSet rs =
                     ps.executeQuery()) {

            if (rs.next()) {

                return rs.getInt("total");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return 0;

    }
}