package com.example.tareaguiajavafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static Connection conexion;

    private Conexion(){}

    public static Connection getConexion(){
        try{
            if (conexion == null || conexion.isClosed()){
                conexion = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/concurso_escuela",
                        "root",
                        "123456"
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conexion;

    }
}
