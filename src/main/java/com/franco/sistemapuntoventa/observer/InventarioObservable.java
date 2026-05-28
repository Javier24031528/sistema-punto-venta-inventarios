package com.franco.sistemapuntoventa.observer;

import java.util.ArrayList;
import java.util.List;

public class InventarioObservable {

    private static final List<ObservadorInventario> observadores = new ArrayList<>();

    public static void agregarObservador(ObservadorInventario observador) {
        observadores.add(observador);
    }

    public static void notificarCambioInventario() {
        for (ObservadorInventario observador : observadores) {
            observador.actualizarInventario();
        }
    }
}