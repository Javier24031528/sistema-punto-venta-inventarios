package com.franco.sistemapuntoventa.dao;

import com.franco.sistemapuntoventa.model.DetalleCompra;
import com.franco.sistemapuntoventa.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompraDAO {

    private final Connection conexion;

    public CompraDAO() {

        conexion = ConexionBD.getInstancia().getConexion();

    }

    public void registrarCompra(DetalleCompra detalleCompra) {

        String sqlCompra = """
                insert into compras(
                    id_compra,
                    fecha_compra,
                    total_compra,
                    id_proveedor,
                    id_usuario
                )
                values (?, now(), ?, ?, ?)
                """;

        String sqlDetalle = """
                insert into detalle_compras(
                    id_detalle_c,
                    cantidad,
                    costo_unitario,
                    id_compra,
                    id_producto
                )
                values (?, ?, ?, ?, ?)
                """;

        String sqlActualizarStock = """
                update productos
                set stock_actual = stock_actual + ?
                where id_producto = ?
                """;

        try {

            PreparedStatement psCompra =
                    conexion.prepareStatement(sqlCompra);

            psCompra.setInt(
                    1,
                    detalleCompra.getCompra().getIdCompra()
            );

            psCompra.setDouble(
                    2,
                    detalleCompra.calcularSubtotal()
            );

            psCompra.setInt(
                    3,
                    detalleCompra.getCompra()
                            .getProveedor()
                            .getIdProveedor()
            );

            psCompra.setInt(
                    4,
                    detalleCompra.getCompra()
                            .getUsuario()
                            .getIdUsuario()
            );

            psCompra.executeUpdate();

            PreparedStatement psDetalle =
                    conexion.prepareStatement(sqlDetalle);

            psDetalle.setInt(
                    1,
                    detalleCompra.getIdDetalleC()
            );

            psDetalle.setInt(
                    2,
                    detalleCompra.getCantidad()
            );

            psDetalle.setDouble(
                    3,
                    detalleCompra.getCostoUnitario()
            );

            psDetalle.setInt(
                    4,
                    detalleCompra.getCompra().getIdCompra()
            );

            psDetalle.setInt(
                    5,
                    detalleCompra.getProducto().getIdProducto()
            );

            psDetalle.executeUpdate();

            PreparedStatement psStock =
                    conexion.prepareStatement(sqlActualizarStock);

            psStock.setInt(
                    1,
                    detalleCompra.getCantidad()
            );

            psStock.setInt(
                    2,
                    detalleCompra.getProducto().getIdProducto()
            );

            psStock.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public int contarCompras() {

        String sql = """
            select count(*) total
            from compras
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