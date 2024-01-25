package com.ceica.Modelos;

import com.ceica.bbdd.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Pieza extends ModeloBase{
    private static int idPieza = 0;
    private int id;
    private String nombre;
    private String color;
    private double precio;
    private Categoria categoria;

    public Pieza(String nombre, String color, double precio) {
        this.id = idPieza++;
        this.nombre = nombre;
        this.color = color;
        this.precio = precio;
    }

    public Pieza() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public static List<Pieza> getPiezas() {
        List<Pieza> piezaList = new ArrayList<>();
        Connection conn = Conexion.conectar();
        String sql = "SELECT P.id,P.nombre,P.color,P.precio,C.id as idcategoria,C.nombre as nombre_categoria " +
                "FROM empresa.piezas as P inner join categorias as C on  P.idcategoria=C.id";
        try {
            Statement stm = conn.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while (respuesta.next()) {
                Pieza pieza = new Pieza();
                pieza.setId(respuesta.getInt("id"));
                pieza.setNombre(respuesta.getString("nombre"));
                pieza.setColor(respuesta.getString("color"));
                pieza.setPrecio(respuesta.getDouble("precio"));

                Categoria categoria1=new Categoria();
                categoria1.setId(respuesta.getInt("idcategoria"));
                categoria1.setNombre(respuesta.getString("nombre_categoria"));
                pieza.setCategoria(categoria1);

                piezaList.add(pieza);
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return piezaList;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
        return piezaList;
    }

    @Override
    public String toString() {
        return "Pieza{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", color='" + color + '\'' +
                ", precio=" + precio +
                ", categoria=" + categoria +
                '}';
    }


    @Override
    protected String getNombreTabla() {
        return "piezas";
    }

    @Override
    protected Object createObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }
}
