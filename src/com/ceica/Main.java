package com.ceica;

import com.ceica.Controladores.AlmacenController;
import com.ceica.Modelos.Color;

public class Main {
    public static void main(String[] args) {
        AlmacenController almacen = new AlmacenController();
        almacen.nuevoProveedor("a", "noma", "dira", "loca", "proa");
        almacen.nuevoProveedor("b", "nomb", "dirb", "locb", "prob");

        //System.out.println(almacen.toString());

        //para borrar
        //almacen.borrarProveedor("a");

        //para modificar datos
        //almacen.editarNombreProveedor("b","nuevoNombre");
        //almacen.editarDireccionProveedor("b","nuevaDirección");
        //almacen.editarLocalidadProveedor("b","nuevaLocalidad");
        //almacen.editarProvinciaProveedor("b","nuevaProvincia");

        almacen.nuevaPieza("pieza1", Color.AZUL, 45.6, 1);
        almacen.nuevaPieza("pieza2", Color.ROJO, 105.6, 3);
        System.out.println(almacen.toString());

        almacen.editarNombrePieza(0,"nuevoNombre");
        almacen.editarColorPieza(0,Color.AMARILLO);
        almacen.editarPrecioPieza(0,55.5);

        System.out.println(almacen.toString());

    }
}