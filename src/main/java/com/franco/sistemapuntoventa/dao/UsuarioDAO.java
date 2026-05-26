package com.franco.sistemapuntoventa.dao;

import com.franco.sistemapuntoventa.model.Rol;
import com.franco.sistemapuntoventa.model.Usuario;
import com.franco.sistemapuntoventa.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioDAO implements CrudDAO<Usuario> {

    private final Connection conexion;

    public UsuarioDAO() {
        conexion = ConexionBD.getInstancia().getConexion();
    }

    @Override
    public void insertar(Usuario usuario) {
        String sql = "insert into usuarios(id_usuario, username, password_hash, id_rol) values (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, usuario.getIdUsuario());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getPasswordHash());
            ps.setInt(4, usuario.getRol().getIdRol());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Usuario usuario) {
        String sql = "update usuarios set username = ?, password_hash = ?, id_rol = ? where id_usuario = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPasswordHash());
            ps.setInt(3, usuario.getRol().getIdRol());
            ps.setInt(4, usuario.getIdUsuario());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "delete from usuarios where id_usuario = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(int id) {
        String sql = """
                select u.id_usuario, u.username, u.password_hash,
                       r.id_rol, r.nombre_rol
                from usuarios u
                inner join roles r on u.id_rol = r.id_rol
                where u.id_usuario = ?
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return Optional.of(mapearUsuario(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = """
                select u.id_usuario, u.username, u.password_hash,
                       r.id_rol, r.nombre_rol
                from usuarios u
                inner join roles r on u.id_rol = r.id_rol
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public Usuario validarLogin(String username, String passwordHash) {
        String sql = """
                select u.id_usuario, u.username, u.password_hash,
                       r.id_rol, r.nombre_rol
                from usuarios u
                inner join roles r on u.id_rol = r.id_rol
                where u.username = ?
                and u.password_hash = ?
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearUsuario(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Rol rol = new Rol(
                rs.getInt("id_rol"),
                rs.getString("nombre_rol")
        );

        return new Usuario(
                rs.getInt("id_usuario"),
                rs.getString("username"),
                rs.getString("password_hash"),
                rol
        );
    }
}