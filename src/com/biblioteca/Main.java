package com.biblioteca;

import com.biblioteca.models.clasesdb.Usuario;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            ArrayList<Usuario> todos = Usuario.getAllList();
            todos.forEach(e->{
               System.out.println(e);
            });
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
