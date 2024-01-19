package com.ceica;

import com.ceica.Controladores.AlmacenController;
import com.ceica.Controladores.LoginController;
import com.ceica.Modelos.Color;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String user, pass;

        Scanner leer = new Scanner(System.in);
        AlmacenController almacen = new AlmacenController();

        System.out.println("AppAlmacen");
        System.out.println("... Enter para empezar");
        leer.nextLine();
        do {
            System.out.println("Login para empezar");
            System.out.print("Introduce usuario: ");
            user = leer.nextLine();
            System.out.print("Introduce password: ");
            pass = leer.nextLine();
            if (LoginController.login(user, pass)) {
                System.out.println("Bienvenido a AppAlmacen");
                menuPrincipal(leer, almacen);
            } else {
                System.out.println("Usuario o Contraseña incorrecta");
            }
        } while (true);
    }

    private static void menuPrincipal(Scanner leer, AlmacenController almacen) {
        String op;
        String menuPrincipal = """
                1. Proveedores
                2. Piezas
                3. Pedidos
                0. Salir
                """;
        do {
            System.out.println("Elija una opción:");
            System.out.println(menuPrincipal);
            op = leer.nextLine();
            switch (op) {
                case "0":
                    System.out.println("Saliendo...");
                    break;
                case "1":
                    menuProveedores(leer, almacen);
                    break;
                case "2":
                    menuPiezas(leer, almacen);
                    break;
                case "3":
                    menuPedidos(leer, almacen);
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        } while (!"0".equals(op));
    }

    private static void menuProveedores(Scanner leer, AlmacenController almacen) {
        String op;
        String menuProveedores = """
                1. Nuevo proveedor
                2. Ver proveedores
                3. Editar proveedor
                4. Eliminar proveedor
                0. Volver a menu principal
                """;
        do {
            System.out.println("Elija una opción:");
            System.out.println(menuProveedores);
            op = leer.nextLine();
            switch (op) {
                case "0":
                    System.out.println("Volviendo al menu principal...");
                    break;
                case "1":
                    nuevoProveedor(leer, almacen);
                    break;
                case "2":
                    verProveedor(leer, almacen);
                    break;
                case "3":
                    editarProveedor(leer, almacen);
                    break;
                case "4":
                    eliminarProveedor(leer, almacen);
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        } while (!"0".equals(op));
    }

    private static void nuevoProveedor(Scanner leer, AlmacenController almacen) {
        String cif, nombre, direccion, localidad, provincia;

        System.out.println("Escriba los datos del nuevo proveedor.");
        System.out.print("Cif: ");
        cif = leer.nextLine();
        System.out.print("Nombre: ");
        nombre = leer.nextLine();
        System.out.print("Dirección: ");
        direccion = leer.nextLine();
        System.out.print("Localidad: ");
        localidad = leer.nextLine();
        System.out.print("Provincia: ");
        provincia = leer.nextLine();

        if (almacen.nuevoProveedor(cif, nombre, direccion, localidad, provincia)) {
            System.out.println("Nuevo proveedor creado con éxito");
        } else {
            System.out.println("No se ha podido crear el proveedor");
        }
    }

    private static void verProveedor(Scanner leer, AlmacenController almacen) {
        String cif;
        System.out.println("Escriba el cif del proveedor");
        cif = leer.nextLine();
        System.out.println(almacen.verProveedor(cif));
    }

    private static void editarProveedor(Scanner leer, AlmacenController almacen) {
        String op;
        String menuProveedores = """
                1. Editar nombre proveedor
                2. Editar dirección proveedor
                3. Editar localidad proveedor
                4. Editar provincia proveedor
                0. Volver a menu proveedores
                """;
        do {
            System.out.println("Escriba el CIF del proveedor que quiera editar");
            String cif = leer.nextLine();
            System.out.println("Elija una opción:");
            System.out.println(menuProveedores);
            op = leer.nextLine();
            switch (op) {
                case "0":
                    System.out.println("Volviendo al menu proveedores...");
                    break;
                case "1":
                    System.out.println("Escriba el nuevo nombre para el proveedor");
                    String nombre = leer.nextLine();
                    almacen.editarNombreProveedor(cif, nombre);
                    break;
                case "2":
                    System.out.println("Escriba la nueva dirección para el proveedor");
                    String direccion = leer.nextLine();
                    almacen.editarDireccionProveedor(cif, direccion);
                    break;
                case "3":
                    System.out.println("Escriba la nueva localidad para el proveedor");
                    String localidad = leer.nextLine();
                    almacen.editarLocalidadProveedor(cif, localidad);
                    break;
                case "4":
                    System.out.println("Escriba la nueva provincia para el proveedor");
                    String provincia = leer.nextLine();
                    almacen.editarProvinciaProveedor(cif, provincia);
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        } while (!"0".equals(op));
    }

    private static void eliminarProveedor(Scanner leer, AlmacenController almacen) {
        System.out.println("Escriba el CIF del proveedor que quiera borrar");
        String cif = leer.nextLine();
        if (almacen.borrarProveedor(cif)){
            System.out.println("Proveedor eliminado");
        }else{
            System.out.println("No existe ningún proveedor con el CIF: " + cif);
        }
    }

    private static void menuPiezas(Scanner leer, AlmacenController almacen) {
        String op;
        String menuPiezas = """
                1. Nueva pieza
                2. Ver piezas
                3. Editar precio pieza
                4. Eliminar pieza
                0. Volver a menu principal
                """;
        do {
            System.out.println("Elija una opción:");
            System.out.println(menuPiezas);
            op = leer.nextLine();
            switch (op) {
                case "0":
                    System.out.println("Volviendo al menu principal...");
                    break;
                case "1":
                    nuevaPieza(leer, almacen);
                    break;
                case "2":
                    verPieza(leer, almacen);
                    break;
                case "3":
                    editarPrecioPieza(leer, almacen);
                    break;
                case "4":
                    eliminarPieza(leer, almacen);
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        } while (!"0".equals(op));
    }

    private static void nuevaPieza(Scanner leer, AlmacenController almacen) {
        String nombre, op;
        double precio;
        Color color = null;
        boolean colorValido = false;
        String colores = """
                1. ROJO
                2. VERDE
                3. AZUL
                4. AMARILLO
                5. BLANCO
                6. NEGRO
                """;

        System.out.println("Escriba los datos de la nueva pieza.");
        System.out.print("Nombre: ");
        nombre = leer.nextLine();

        do{
            System.out.print("Elija un color: ");
            System.out.println(colores);
            op = leer.nextLine();
            switch (op){
                case "1":
                    color = Color.ROJO;
                    colorValido = true;
                    break;
                case "2":
                    color = Color.VERDE;
                    colorValido = true;
                    break;
                case "3":
                    color = Color.AZUL;
                    colorValido = true;
                    break;
                case "4":
                    color = Color.AMARILLO;
                    colorValido = true;
                    break;
                case "5":
                    color = Color.BLANCO;
                    colorValido = true;
                    break;
                case "6":
                    color = Color.NEGRO;
                    colorValido = true;
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        }while (!colorValido);

        System.out.print("Precio: ");
        precio = leer.nextDouble();

        if (almacen.nuevaPieza(nombre, color, precio, idCategoria)) {
            System.out.println("Nueva pieza creada con éxito");
        } else {
            System.out.println("No se ha podido crear el proveedor");
        }
    }

    private static void verPieza(Scanner leer, AlmacenController almacen) {
    }

    private static void editarPrecioPieza(Scanner leer, AlmacenController almacen) {
    }

    private static void eliminarPieza(Scanner leer, AlmacenController almacen) {
    }
}