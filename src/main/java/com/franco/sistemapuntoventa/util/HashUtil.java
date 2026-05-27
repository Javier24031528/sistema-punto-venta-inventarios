package com.franco.sistemapuntoventa.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    public static String convertirSHA1(String texto) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(texto.getBytes());

            StringBuilder resultado = new StringBuilder();

            for (byte b : bytes) {
                resultado.append(String.format("%02x", b));
            }

            return resultado.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}