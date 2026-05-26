package com.franco.sistemapuntoventa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instancia;
    private Connection conexion;

    private final String URL = "jdbc:mysql://localhost:3306/proyectoFinal";
    private final String USUARIO = "root";
    private final String PASSWORD = "DobleAtakez1.";

    private ConexionBD() {

        try {

            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("Conexión exitosa a MySQL");

        } catch (SQLException e) {

            System.out.println("Error de conexión");
            e.printStackTrace();

        }

    }

    public static ConexionBD getInstancia() {

        if (instancia == null) {
            instancia = new ConexionBD();
        }

        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

}