package com.biblioteca.models.clasesdb;

import com.biblioteca.models.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Seccion {
    private Integer idSeccion;
    private String nombre;

    private Seccion(Integer idSeccion, String nombre) {
        this.idSeccion = idSeccion;
        this.nombre = nombre;
    }

    public static Seccion crearSeccion(String name) throws SQLException {
        Integer id = null;
        try {
            Connection cx = ConexionDB.conectar();
            String sql = """
                    insert into seccion(nombre) values (?);
                    """;
            PreparedStatement stp = cx.prepareStatement(sql);
            stp.setString(1, name);
            stp.execute();
            ResultSet res = stp.getGeneratedKeys();
            while (res.next()) {
                id = res.getInt(1);
            }
            stp.close();
            cx.close();
            return new Seccion(id, name);
        } catch (SQLException e) {
            throw e;
        }
    }

    protected void operationSet(String sql, String var) throws SQLException {
        try {
            Connection cx = ConexionDB.conectar();
            PreparedStatement stp = cx.prepareStatement(sql);
            stp.setString(1, var);
            stp.setInt(2, this.idSeccion);
            stp.execute();
            stp.close();
            cx.close();
        } catch (SQLException e) {
            throw e;
        }
    }

    public void setNombre(String nombre) throws SQLException {
        try {
            String sql = """
                    update seccion
                    set nombre = ? where idSeccion = ?;
                    """;
            operationSet(sql, nombre);
        } catch (SQLException e) {
            throw e;
        }
        this.nombre = nombre;
    }

    public static Seccion getSeccion(Integer id) throws Exception {
        Seccion retorno = null;
        try {

            Connection cx = ConexionDB.conectar();
            String sql = """
                    select * from seccion where idSeccion = ?;
                    """;
            PreparedStatement stp = cx.prepareStatement(sql);
            stp.setInt(1, id);
            ResultSet res = stp.executeQuery();

            while (res.next()) {
                retorno = new Seccion(id, res.getString("nombre"));
            }
            stp.close();
            cx.close();

        } catch (SQLException e) {
            throw e;
        }

        if (retorno == null) {
            throw new Exception("No se encontro la Seccion con id: " + id.toString());
        }

        return retorno;

    }

    public static void deleteSeccion(Integer id) throws SQLException {
        try {
            Connection cx = ConexionDB.conectar();
            String sql = """
                    delete from seccion where idSeccion = ?;
                    """;
            PreparedStatement stp = cx.prepareStatement(sql);
            stp.setInt(1, id);
            stp.execute();

            stp.close();
            cx.close();

        } catch (SQLException e) {
            throw e;
        }
    }

    public static ArrayList<Seccion> getAllSeccion() throws SQLException{
        ArrayList<Seccion> retorno = new ArrayList();
        try {
            Connection cx = ConexionDB.conectar();
            String sql = """
                    select * from seccion;
                    """;
            Statement st = cx.createStatement();
            ResultSet res = st.executeQuery(sql);

            while(res.next()){
                retorno.add(new Seccion(res.getInt("idSeccion"), res.getString("nombre")));
            }
            
        } catch (SQLException e) {
            throw e;
        }
        return retorno;
    }
    public Integer getIdSeccion() {
        return idSeccion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Seccion [idSeccion=" + idSeccion + ", nombre=" + nombre + "]";
    }

    
}
