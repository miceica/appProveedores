package com.ceica;

import com.ceica.Controladores.AlmacenController;
import com.ceica.Controladores.LoginController;
import com.ceica.Modelos.Color;

import java.util.Arrays;
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

    //-------------------------------------------------- PROVEEDORES --------------------------------------------------

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
        System.out.println(almacen.getProveedorByCif(cif).toString());
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
        if (almacen.borrarProveedor(cif)) {
            System.out.println("Proveedor eliminado");
        } else {
            System.out.println("No existe ningún proveedor con el CIF: " + cif);
        }
    }

    //----------------------------------------------------- PIEZAS -----------------------------------------------------

    private static void menuPiezas(Scanner leer, AlmacenController almacen) {
        String op;
        String menuPiezas = """
                1. Nueva pieza
                2. Ver piezas
                3. Editar pieza
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
                    editarPieza(leer, almacen);
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
        String nombre, precioString, colorPieza, categoriaString;
        double precio = 0;
        Color color = null;
        int categoria = 0;
        boolean precioValido, colorValido, categoriaValida;

        System.out.println("Escriba los datos de la nueva pieza.");
        System.out.print("Nombre: ");
        nombre = leer.nextLine();

        do {
            System.out.print("Precio: ");
            precioString = leer.nextLine();
            try {
                precio = Double.parseDouble(precioString);
                precioValido = true;
            } catch (Exception e) {
                precioValido = false;
                System.out.println("Precio no valido");
            }
        } while (!precioValido);

        do {
            System.out.print("Elija uno de los siguientes colores: ");
            System.out.println(Arrays.stream(Color.values()).toList());
            colorPieza = leer.nextLine();
            try {
                color = Color.valueOf(colorPieza.toUpperCase());
                colorValido = true;
            } catch (Exception e) {
                colorValido = false;
            }
        } while (!colorValido);

        do {
            System.out.println("Elija una de estas opciones como categoría:");
            System.out.println(almacen.categoriasDisponibles());
            categoriaString = leer.nextLine();
            try {
                categoria = Integer.parseInt(categoriaString);
                if (almacen.validarCategoria(categoria)) {
                    categoriaValida = true;
                } else {
                    categoriaValida = false;
                    System.out.println("Opción no valida");
                }
            } catch (Exception e) {
                categoriaValida = false;
                System.out.println("Opción no valida");
            }
        } while (!categoriaValida);

        almacen.nuevaPieza(nombre, color, precio, categoria);
    }

    private static void verPieza(Scanner leer, AlmacenController almacen) {
        int idPieza;
        System.out.println("Escriba el id de la pieza");
        idPieza = leer.nextInt();
        //System.out.println(almacen.verPieza(idPieza));
        System.out.println(almacen.getPiezaById(idPieza).toString());
    }

    private static void editarPieza(Scanner leer, AlmacenController almacen) {
        String op;
        String menuProveedores = """
                1. Editar nombre pieza
                2. Editar color pieza
                3. Editar precio pieza
                0. Volver a menu piezas
                """;
        do {
            System.out.println("Escriba el id de la pieza que quiera editar");
            String idString = leer.nextLine();
            int id = Integer.parseInt(idString);
            System.out.println("Elija una opción:");
            System.out.println(menuProveedores);
            op = leer.nextLine();
            switch (op) {
                case "0":
                    System.out.println("Volviendo al menu piezas...");
                    break;
                case "1":
                    System.out.println("Escriba el nuevo nombre para la pieza");
                    String nombre = leer.nextLine();
                    if (almacen.editarNombrePieza(id, nombre)) {
                        System.out.println("Nombre actualizado");
                    } else {
                        System.out.println("No se ha podido cambiar el nombre");
                    }
                    break;
                case "2":
                    String colorPieza;
                    Color color = null;
                    boolean colorValido;
                    do {
                        System.out.print("Elija uno de los siguientes colores: ");
                        System.out.println(Arrays.stream(Color.values()).toList());
                        colorPieza = leer.nextLine();
                        try {
                            color = Color.valueOf(colorPieza.toUpperCase());
                            colorValido = true;
                        } catch (Exception e) {
                            colorValido = false;
                            System.out.println("Color no valido");
                        }
                    } while (!colorValido);

                    if (almacen.editarColorPieza(id, color)) {
                        System.out.println("Color actualizado");
                    } else {
                        System.out.println("No se ha podido cambiar el color");
                    }
                    break;
                case "3":
                    System.out.println("Escriba el nuevo precio de la pieza");
                    String precioString = leer.nextLine();
                    double precio = Double.parseDouble(precioString);
                    if (almacen.editarPrecioPieza(id, precio)) {
                        System.out.println("Nombre actualizado");
                    } else {
                        System.out.println("No se ha podido cambiar el nombre");
                    }
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        } while (!"0".equals(op));
    }

    private static void eliminarPieza(Scanner leer, AlmacenController almacen) {
        boolean piezaEliminada = false;
        do {
            System.out.println("Escriba el id de la pieza que quiera borrar");
            String idPiezaString = leer.nextLine();
            try {
                int idPieza = Integer.parseInt(idPiezaString);
                if (almacen.borrarPieza(idPieza)) {
                    System.out.println("Pieza eliminada");
                    piezaEliminada = true;
                } else {
                    System.out.println("El id: " + idPieza + " no existe.");
                }
            } catch (Exception e) {
                System.out.println(idPiezaString + " no es un id valido.");
            }
        } while (!piezaEliminada);
    }

    //---------------------------------------------------- PEDIDOS ----------------------------------------------------

    private static void menuPedidos(Scanner leer, AlmacenController almacen) {
        String op;
        String menuPiezas = """
                1. Nuevo pedio
                2. Ver pedidos por pieza
                3. Ver pedidos por proveedor
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
                    nuevoPedido(leer, almacen);
                    break;
                case "2":
                    verPedidoByPieza(leer, almacen);
                    break;
                case "3":
                    verPedidoByProveedor(leer, almacen);
                    break;
                default:
                    System.out.println("Opción no valida");
            }
        } while (!"0".equals(op));
    }

    private static void nuevoPedido(Scanner leer, AlmacenController almacen) {
        String cif, idPiezaString, cantidadString;
        int idPieza, cantidad;

        System.out.println("Escriba los datos del nuevo pedido.");
        System.out.print("Cif: ");
        cif = leer.nextLine();
        System.out.print("ID de la pieza: ");
        idPiezaString = leer.nextLine();
        idPieza = Integer.parseInt(idPiezaString);
        System.out.print("cantidad: ");
        cantidadString = leer.nextLine();
        cantidad = Integer.parseInt(cantidadString);

        System.out.println(almacen.nuevoPedido(cif, idPieza, cantidad));
    }

    private static void verPedidoByPieza(Scanner leer, AlmacenController almacen) {
        String idPiezaString;
        System.out.println("Escriba el ID de la pieza");
        idPiezaString = leer.nextLine();
        int idPieza = Integer.parseInt(idPiezaString);
        System.out.println(almacen.getPedidosByPieza(idPieza));
    }

    private static void verPedidoByProveedor(Scanner leer, AlmacenController almacen) {
        String cif;
        System.out.println("Escriba el CIF de la pieza");
        cif = leer.nextLine();
        System.out.println(almacen.getPedidosByProveedor(cif));
    }

}