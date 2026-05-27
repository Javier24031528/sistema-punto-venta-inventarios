package com.franco.sistemapuntoventa.dao;

import com.franco.sistemapuntoventa.model.Categoria;
import com.franco.sistemapuntoventa.util.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoriaDAO implements CrudDAO<Categoria> {

    private final Connection conexion;

    public CategoriaDAO() {

        conexion = ConexionBD.getInstancia().getConexion();

    }

    @Override
    public void insertar(Categoria categoria) {

        String sql = """
                insert into categorias(id_categoria, nombre_categoria)
                values(?, ?)
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, categoria.getIdCategoria());
            ps.setString(2, categoria.getNombreCategoria());

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void actualizar(Categoria categoria) {

        String sql = """
                update categorias
                set nombre_categoria = ?
                where id_categoria = ?
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, categoria.getNombreCategoria());
            ps.setInt(2, categoria.getIdCategoria());

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public void eliminar(int id) {

        String sql = """
                delete from categorias
                where id_categoria = ?
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    @Override
    public Optional<Categoria> buscarPorId(int id) {

        String sql = """
                select *
                from categorias
                where id_categoria = ?
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return Optional.of(
                        new Categoria(
                                rs.getInt("id_categoria"),
                                rs.getString("nombre_categoria")
                        )
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return Optional.empty();

    }

    @Override
    public List<Categoria> listar() {

        List<Categoria> categorias = new ArrayList<>();

        String sql = """
                select *
                from categorias
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Categoria categoria = new Categoria(
                        rs.getInt("id_categoria"),
                        rs.getString("nombre_categoria")
                );

                categorias.add(categoria);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return categorias;

    }

}