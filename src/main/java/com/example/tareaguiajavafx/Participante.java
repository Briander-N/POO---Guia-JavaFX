package com.example.tareaguiajavafx;

public class Participante {

    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private int edad;
    private String correo;
    private String estadoCivil;
    private String jornada;
    private String categoria;
    public Participante(int id,
                        String cedula,
                        String nombre,
                        String apellido,
                        int edad,
                        String correo,
                        String estadoCivil,
                        String jornada,
                        String categoria) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.correo = correo;
        this.estadoCivil = estadoCivil;
        this.jornada = jornada;
        this.categoria = categoria;
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public String getCorreo() {
        return correo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public String getJornada() {
        return jornada;
    }

    public String getCategoria() {
        return categoria;
    }
}