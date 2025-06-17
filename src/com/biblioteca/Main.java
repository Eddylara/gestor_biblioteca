package com.biblioteca;
import com.biblioteca.models.clasesdb.Seccion;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        try {
            ArrayList<Seccion> secciones = Seccion.getAllSeccion();
            secciones.forEach(e->{
                System.out.println(e);
            });

            
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
