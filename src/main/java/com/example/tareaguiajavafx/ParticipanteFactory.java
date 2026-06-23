package com.example.tareaguiajavafx;

public class ParticipanteFactory {
    public static Participante crearParticipante(
            int id,
            String cedula,
            String nombre,
            String apellido,
            int edad,
            String correo,
            String estadoCivil,
            String jornada,
            String categoria){
        return new Participante(
                id,
                cedula,
                nombre,
                apellido,
                edad,
                correo,
                estadoCivil,
                jornada,
                categoria
        );
    }
}

