package com.ceica.Controladores;

import com.ceica.Modelos.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlmacenController {
    private List<Proveedor> proveedorList;
    private List<Pieza> piezaList;
    private List<Pedido> pedidoList;
    private List<Categoria> categorias;

    public AlmacenController() {
        proveedorList = new ArrayList<>();
        proveedorList = Proveedor.getProveedores();

        pedidoList = new ArrayList<>();
        piezaList = new ArrayList<>();
        categorias = new ArrayList<>();

        categorias.add(new Categoria(1, "pequeño"));
        categorias.add(new Categoria(2, "mediano"));
        categorias.add(new Categoria(3, "grande"));
    }

    //-------------------------------------------------- PROVEEDORES --------------------------------------------------

    public boolean nuevoProveedor(String cif, String nombre, String direccion, String localidad, String provincia) {
        Proveedor proveedor = new Proveedor(cif, nombre);

        if (proveedor.insertar("(cif,nombre,direccion,localidad,provincia) values (?,?,?,?,?)", cif, nombre, direccion, localidad, provincia)) {
            proveedorList = Proveedor.getProveedores();
            return true;
        } else {
            return false;
        }
    }

    public Proveedor getProveedorByCif(String cif) {
        for (Proveedor proveedor : proveedorList) {
            if (cif.equals(proveedor.getCif())) {
                return proveedor;
            }
        }
        return null;
    }

    public boolean editarNombreProveedor(String cif, String nombre) {
        Proveedor proveedor = new Proveedor();
        if (proveedor.actualizar("nombre=? where cif=?", nombre, cif)) {
            proveedorList = Proveedor.getProveedores();
            return true;
        } else {
            return false;
        }
    }

    public boolean editarDireccionProveedor(String cif, String direccion) {
        Proveedor proveedor = new Proveedor();
        if (proveedor.actualizar("direccion=? where cif=?", direccion, cif)) {
            proveedorList = Proveedor.getProveedores();
            return true;
        } else {
            return false;
        }
    }

    public boolean editarLocalidadProveedor(String cif, String localidad) {
        Proveedor proveedor = new Proveedor();
        if (proveedor.actualizar("localidad=? where cif=?", localidad, cif)) {
            proveedorList = Proveedor.getProveedores();
            return true;
        } else {
            return false;
        }
    }

    public boolean editarProvinciaProveedor(String cif, String provincia) {
        Proveedor proveedor = new Proveedor();
        if (proveedor.actualizar("provincia=? where cif=?", provincia, cif)) {
            proveedorList = Proveedor.getProveedores();
            return true;
        } else {
            return false;
        }
    }

    public boolean borrarProveedor(String cif) {
        Proveedor proveedor = new Proveedor();

        if (proveedor.borrar("cif=?", cif)) {
            proveedorList = Proveedor.getProveedores();
            return true;
        } else {
            return false;
        }
    }

    //----------------------------------------------------- PIEZAS -----------------------------------------------------

    public boolean nuevaPieza(String nombre, Color color, Double precio, int idCategoria) {
        Pieza pieza = new Pieza(nombre, color.toString(), precio);

        if (pieza.insertar("(nombre,color,precio,idcategoria) values (?,?,?,?)", nombre, color.toString(), precio, idCategoria)) {
            piezaList = Pieza.getPiezas();
            return true;
        } else {
            return false;
        }
    }

    public Pieza getPiezaById(int idPieza) {
        for (Pieza pieza : piezaList) {
            if (pieza.getId() == idPieza) {
                return pieza;
            }
        }
        return null;
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

    public boolean borrarPieza(int idPieza) {
        //función landa
        return piezaList.removeIf(pieza -> idPieza == (pieza.getId()));
    }

    //---------------------------------------------------- PEDIDOS ----------------------------------------------------

    public String nuevoPedido(String cif, int idPieza, int cantidad) {
        Proveedor proveedor = getProveedorByCif(cif);
        if (proveedor != null) {
            Pieza pieza = getPiezaById(idPieza);
            if (pieza != null) {
                Pedido pedido1 = new Pedido(proveedor, pieza);
                pedido1.setCantidad(cantidad);
                pedido1.setFecha(LocalDate.now());
                pedidoList.add(pedido1);
                return "Pedido creado";
            } else {
                return "Error al crear el pedido, Pieza no existe";
            }
        } else {
            return "Error al crear el pedido, Proveedor no existe";
        }
    }

    public String getPedidosByPieza(int idPieza) {
        List<Pedido> pedidosByPieza = new ArrayList<>();
        for (Pedido pedido : pedidoList) {
            if (pedido.getPieza().getId() == idPieza) {
                pedidosByPieza.add(pedido);
            }
        }
        if (pedidosByPieza.size() > 0) {
            return pedidosByPieza.toString();
        } else {
            return "No hay pedidos de esta pieza";
        }
    }

    public String getPedidosByProveedor(String cif) {
        List<Pedido> pedidosByProveedor = new ArrayList<>();
        for (Pedido pedido : pedidoList) {
            if (pedido.getProveedor().getCif() == cif) {
                pedidosByProveedor.add(pedido);
            }
        }
        if (pedidosByProveedor.size() > 0) {
            return pedidosByProveedor.toString();
        } else {
            return "No hay pedidos para este proveedor";
        }
    }

    //--------------------------------------------------- CATEGORÍAS ---------------------------------------------------

    private Categoria getCategoriaById(int id) {
        return categorias.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    public String categoriasDisponibles() {
        //...
        return categorias.toString();
    }

    public boolean validarCategoria(int categoria) {
        for (Categoria cat : categorias) {
            if (cat.getId() == categoria) {
                return true;
            }
        }
        return false;
    }

    //--------------------------------------------------- TO STRING ---------------------------------------------------

    @Override
    public String toString() {
        return "AlmacenController{" + "\n" +
                "proveedorList=" + proveedorList + "\n" +
                "piezaList=" + piezaList + "\n" +
                "pedidoList=" + pedidoList + "\n" +
                "}" + "\n";
    }
}
