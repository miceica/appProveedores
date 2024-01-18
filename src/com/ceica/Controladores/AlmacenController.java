package com.ceica.Controladores;

import com.ceica.Modelos.*;

import java.util.ArrayList;
import java.util.List;

public class AlmacenController {
    private List<Proveedor> proveedorList;
    private List<Pieza> piezaList;
    private List<Pedido> pedidoList;
    private List<Categoria> categorias;

    public AlmacenController() {
        proveedorList = new ArrayList<>();
        pedidoList = new ArrayList<>();
        piezaList = new ArrayList<>();
        categorias = new ArrayList<>();
        categorias.add(new Categoria(1, "pequeño"));
        categorias.add(new Categoria(2, "mediano"));
        categorias.add(new Categoria(3, "grande"));
    }

    public boolean nuevoProveedor(String cif, String nombre, String direccion, String localidad, String provincia) {
        Proveedor proveedor = new Proveedor(cif, nombre);
        proveedor.setDireccion(direccion);
        proveedor.setLocalidad(localidad);
        proveedor.setProvincia(provincia);

        return proveedorList.add(proveedor);
    }

    public boolean borrarProveedor(String cif) {
        //función landa
        return proveedorList.removeIf(proveedor -> cif.equals(proveedor.getCif()));
    }

    public boolean editarNombreProveedor(String cif, String nombre) {
        return proveedorList.stream()
                .filter(p -> cif.equals(p.getCif()))
                .findFirst().map(p -> {
                    p.setNombre(nombre);
                    return true;
                }).orElse(false);
    }

    public boolean editarDireccionProveedor(String cif, String direccion) {
        return proveedorList.stream()
                .filter(p -> cif.equals(p.getCif()))
                .findFirst().map(p -> {
                    p.setDireccion(direccion);
                    return true;
                }).orElse(false);
    }

    public boolean editarLocalidadProveedor(String cif, String localidad) {
        return proveedorList.stream()
                .filter(p -> cif.equals(p.getCif()))
                .findFirst().map(p -> {
                    p.setLocalidad(localidad);
                    return true;
                }).orElse(false);
    }

    public boolean editarProvinciaProveedor(String cif, String provincia) {
        return proveedorList.stream()
                .filter(p -> cif.equals(p.getCif()))
                .findFirst().map(p -> {
                    p.setProvincia(provincia);
                    return true;
                }).orElse(false);
    }

    public boolean editarNombrePieza(int id, String nombre) {
        return piezaList.stream()
                .filter(p -> id == p.getId())
                .findFirst().map(p -> {
                    p.setNombre(nombre);
                    return true;
                }).orElse(false);
    }

    public boolean editarColorPieza(int id, Color color) {
        return piezaList.stream()
                .filter(p -> id == p.getId())
                .findFirst().map(p -> {
                    p.setColor(color.toString());
                    return true;
                }).orElse(false);
    }

    public boolean editarPrecioPieza(int id, Double precio) {
        return piezaList.stream()
                .filter(p -> id == p.getId())
                .findFirst().map(p -> {
                    p.setPrecio(precio);
                    return true;
                }).orElse(false);
    }

    public boolean nuevaPieza(String nombre, Color color, Double precio, int idCategoria) {
        Pieza pieza = new Pieza(nombre, color.toString(), precio);
        pieza.setCategoria(getCategoriaById(idCategoria));
        piezaList.add(pieza);
        return true;
    }

    private Categoria getCategoriaById(int id) {
        return categorias.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    @Override
    public String toString() {
        return "AlmacenController{" + "\n" +
                "proveedorList=" + proveedorList + "\n" +
                "piezaList=" + piezaList + "\n" +
                "pedidoList=" + pedidoList + "\n" +
                "}" + "\n";
    }
}
