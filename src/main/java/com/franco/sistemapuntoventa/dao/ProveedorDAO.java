package com.franco.sistemapuntoventa.dao;

import com.franco.sistemapuntoventa.model.Proveedor;
import com.franco.sistemapuntoventa.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProveedorDAO implements CrudDAO<Proveedor> {

    private final Connection conexion;

    public ProveedorDAO() {

        conexion = ConexionBD.getInstancia().getConexion();

    }

    @Override
    public void insertar(Proveedor proveedor) {

        String sql = """
                insert into proveedores(
                    id_proveedor,
                    razon_social,
                    telefono
                )
                values (?, ?, ?)
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, proveedor.getIdProveedor());
            ps.setString(2, proveedor.getRazonSocial());
            ps.setString(3, proveedor.getTelefono());

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void actualizar(Proveedor proveedor) {

        String sql = """
                update proveedores
                set razon_social = ?,
                    telefono = ?
                where id_proveedor = ?
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, proveedor.getRazonSocial());
            ps.setString(2, proveedor.getTelefono());
            ps.setInt(3, proveedor.getIdProveedor());

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void eliminar(int id) {

        String sql = """
                delete from proveedores
                where id_proveedor = ?
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public Optional<Proveedor> buscarPorId(int id) {

        String sql = """
                select *
                from proveedores
                where id_proveedor = ?
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return Optional.of(
                        new Proveedor(
                                rs.getInt("id_proveedor"),
                                rs.getString("razon_social"),
                                rs.getString("telefono")
                        )
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return Optional.empty();

    }

    @Override
    public List<Proveedor> listar() {

        List<Proveedor> proveedores = new ArrayList<>();

        String sql = """
                select *
                from proveedores
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Proveedor proveedor = new Proveedor(
                        rs.getInt("id_proveedor"),
                        rs.getString("razon_social"),
                        rs.getString("telefono")
                );

                proveedores.add(proveedor);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return proveedores;

    }

}