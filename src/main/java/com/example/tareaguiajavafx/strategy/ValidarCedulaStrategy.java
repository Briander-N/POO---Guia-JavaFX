package com.example.tareaguiajavafx.strategy;

public class ValidarCedulaStrategy
        implements strategy{

    @Override
    public boolean validar(String dato) {

        return dato.matches("\\d+");
    }
}