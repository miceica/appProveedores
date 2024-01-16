package com.ceica;

import com.ceica.Controladores.AlmacenController;

public class Main {
    public static void main(String[] args) {
        AlmacenController almacen = new AlmacenController();
        almacen.nuevoProveedor("a","noma","dira","loca","proa");
        almacen.nuevoProveedor("b","nomb","dirb","locb","prob");

        System.out.println(almacen.toString());

        System.out.println(almacen.borrarProveedor("a"));

        System.out.println(almacen.toString());
    }
}