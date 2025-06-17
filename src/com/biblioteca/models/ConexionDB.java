package com.biblioteca.models;

import java.sql.*;

public class ConexionDB {
    private final static String strCone = "jdbc:sqlite:" + System.getProperty("user.dir") + "/res/database/biblioteca.db";

    public static Connection conectar() throws SQLException{
        Connection conexion = null;
        try{
            conexion = DriverManager.getConnection(strCone);
            return conexion;
        }catch(SQLException e){
            throw e;
        }
        
    }

    public static void testConnection (){
        Connection testCone = null;
        try{
            testCone = DriverManager.getConnection(ConexionDB.strCone);
            System.out.println("Conexion a la base de datos exitosa");

        }catch(SQLException e){
            System.out.println("ERROR: " + e.getMessage());
        }

        try {
            testCone.close();
            System.out.println("La base de datos se cerro correctamente");
        } catch (SQLException e) {
            System.out.println("ERROR al cerrar la base de datos: " + e.getMessage());
        }
        
    }



}
