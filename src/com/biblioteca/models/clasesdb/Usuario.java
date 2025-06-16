package com.biblioteca.models.clasesdb;

import com.biblioteca.models.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Usuario {
    private Integer idUsuario;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String sexo;
    private String correo;
    private String telefono;
    private String direccion;
    
        

    private Usuario(Integer idUsuario, String nombre, String apellido, Integer edad, String sexo, String correo,
        String telefono, String direccion) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public static Usuario createUser(String nombre, String apellido, Integer edad, String sexo, String correo,
        String telefono, String direccion) throws SQLException{
            Integer miid = null;
            try{
                Connection cx = ConexionDB.conectar();
            String sql = """
            insert into usuario (nombre, apellido, edad, sexo, correo, telefono, direccion)
            values (?,?,?,?,?,?,?);
            """;
            PreparedStatement pst = cx.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, apellido);
            pst.setInt(3, edad);
            pst.setString(4, sexo);
            pst.setString(5, correo);
            pst.setString(6, telefono);
            pst.setString(7, direccion);
            int filas = pst.executeUpdate();
            ResultSet res = pst.getGeneratedKeys();
            res.close();
            pst.close();
            cx.close();
            while(res.next()){
                miid = res.getInt(1);
            }
            }catch(SQLException e){
                throw e;
            }
            return new Usuario(miid, nombre, apellido, edad, sexo, correo, telefono, direccion);
        }

    public static ArrayList<Usuario> getAllList() throws SQLException{
        ArrayList<Usuario> retorno = new ArrayList<>();
        Connection cx = ConexionDB.conectar();
        String sql = "select * from usuario;";
        java.sql.Statement stmet = cx.createStatement();
        ResultSet res = stmet.executeQuery(sql);
        
        while(res.next()){
            Integer id = res.getInt("idUsuario");
            String nombre = res.getString("nombre");
            String apellido = res.getString("apellido");
            Integer edad = res.getInt("edad");
            String sexo = res.getString("sexo");
            String correo = res.getString("correo");
            String tel = res.getString("telefono");
            String dir = res.getString("direccion");
            retorno.add(new Usuario(id, nombre, apellido, edad, sexo, correo, tel,dir));
        }
        res.close();
        stmet.close();
        cx.close();


        return retorno;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("idUsuario=").append(idUsuario);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellido=").append(apellido);
        sb.append(", edad=").append(edad);
        sb.append(", sexo=").append(sexo);
        sb.append(", correo=").append(correo);
        sb.append(", telefono=").append(telefono);
        sb.append(", direccion=").append(direccion);
        sb.append('}');
        return sb.toString();
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    private void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
}
