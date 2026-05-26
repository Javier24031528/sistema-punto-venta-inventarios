package com.franco.sistemapuntoventa.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDAO<T> {

    void insertar(T objeto);

    void actualizar(T objeto);

    void eliminar(int id);

    Optional<T> buscarPorId(int id);

    List<T> listar();
}