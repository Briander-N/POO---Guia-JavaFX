package com.example.tareaguiajavafx.strategy;

public class ValidarCorreoStrategy implements strategy{
    @Override
    public boolean validar(String dato){
        return dato.contains("@");
    }
}
