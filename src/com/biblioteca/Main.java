package com.biblioteca;

import com.biblioteca.models.clasesdb.Usuario;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        try {
            ArrayList<Usuario> db = Usuario.getAllList();
            db.forEach(System.out::println);
            
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
}
