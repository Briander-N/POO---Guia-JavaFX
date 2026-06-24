package com.example.tareaguiajavafx.strategy;

public class ValidarEdadStrategy implements strategy{
    @Override
    public boolean validar(String dato) {

        try {

            int edad = Integer.parseInt(dato);

            return edad > 5;

        } catch (Exception e) {

            return false;
        }
    }
}
