package com.ceica.Modelos;

public class Categoria extends ModeloBase {
    private int id;
    private String nombre;

    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Categoria(){

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

    @Override
    public String toString() {
        return "Escribe: " + id + " para la categoría " + nombre + "\n";
    }

    @Override
    protected String getNombreTabla() {
        return "categorias";
    }

    public static void main(String[] args) {
        Categoria categoria = new Categoria();
        categoria.setNombre("Ordenadores");

        String sql = "(nombre) VALUES (?)";
        categoria.insertar(sql,categoria.getNombre());
    }
}
