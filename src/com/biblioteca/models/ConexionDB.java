package com.biblioteca.models;

import java.sql.*;
import java.util.ArrayList;

public class ConexionDB {
    private static String strCone = "jdbc:sqlite:" + System.getProperty("user.dir") + "/res/database/biblioteca.db";
    private Connection cone = null;

    public static void testConnection (){
        Connection testCone = null;
        try{
            testCone = DriverManager.getConnection(ConexionDB.strCone);
            System.out.println("Conexion a la base de datos exitosa");
            Statement consulta = testCone.createStatement();
            ResultSet resultado = consulta.executeQuery("select * from usuario;");
            
            ArrayList<String> nombres = new ArrayList<>();
            
            while(resultado.next()){
                nombres.add(resultado.getString("nombre"));
            }

            for(String nombre : nombres){
                System.out.println(nombre);
            }


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
