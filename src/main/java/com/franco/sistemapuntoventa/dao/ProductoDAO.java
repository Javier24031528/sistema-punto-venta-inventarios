package com.franco.sistemapuntoventa.dao;

import com.franco.sistemapuntoventa.model.Categoria;
import com.franco.sistemapuntoventa.model.Producto;
import com.franco.sistemapuntoventa.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoDAO implements CrudDAO<Producto> {

    private final Connection conexion;

    public ProductoDAO() {

        conexion = ConexionBD.getInstancia().getConexion();

    }

    @Override
    public void insertar(Producto producto) {

        String sql = """
                insert into productos(
                    id_producto,
                    codigo_barras,
                    nombre,
                    precio_venta,
                    stock_actual,
                    id_categoria
                )
                values (?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, producto.getIdProducto());
            ps.setString(2, producto.getCodigoBarras());
            ps.setString(3, producto.getNombre());
            ps.setDouble(4, producto.getPrecioVenta());
            ps.setInt(5, producto.getStockActual());
            ps.setInt(6, producto.getCategoria().getIdCategoria());

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void actualizar(Producto producto) {

        String sql = """
                update productos
                set codigo_barras = ?,
                    nombre = ?,
                    precio_venta = ?,
                    stock_actual = ?,
                    id_categoria = ?
                where id_producto = ?
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, producto.getCodigoBarras());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecioVenta());
            ps.setInt(4, producto.getStockActual());
            ps.setInt(5, producto.getCategoria().getIdCategoria());
            ps.setInt(6, producto.getIdProducto());

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void eliminar(int id) {

        String sql = """
                delete from productos
                where id_producto = ?
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public Optional<Producto> buscarPorId(int id) {

        String sql = """
                select p.*, c.nombre_categoria
                from productos p
                inner join categorias c
                    on p.id_categoria = c.id_categoria
                where p.id_producto = ?
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return Optional.of(mapearProducto(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return Optional.empty();

    }

    @Override
    public List<Producto> listar() {

        List<Producto> productos = new ArrayList<>();

        String sql = """
                select p.*, c.nombre_categoria
                from productos p
                inner join categorias c
                    on p.id_categoria = c.id_categoria
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                productos.add(mapearProducto(rs));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return productos;

    }

    private Producto mapearProducto(ResultSet rs) throws SQLException {

        Categoria categoria = new Categoria(
                rs.getInt("id_categoria"),
                rs.getString("nombre_categoria")
        );

        return new Producto(
                rs.getInt("id_producto"),
                rs.getString("codigo_barras"),
                rs.getString("nombre"),
                rs.getDouble("precio_venta"),
                rs.getInt("stock_actual"),
                categoria
        );

    }

    public int contarProductos() {

        String sql = """
            select count(*) total
            from productos
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