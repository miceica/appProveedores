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

    /**
     * @param nombre Nombre de la pieza
     * @param color color de la pieza
     * @param precio precio de la pieza
     * @param idCategoria
     * @return true si la pieza es creada
     */
    public boolean nuevaPieza(String nombre, Color color, Double precio, int idCategoria) {
        Pieza pieza = new Pieza(nombre, color.toString(), precio);
        pieza.setCategoria(getCategoriaById(idCategoria));
        piezaList.add(pieza);
        return true;
    }

    private Categoria getCategoriaById(int id) {
        return categorias.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    /**
     * @param cif CIF del proveedor
     * @param idPieza ID de la pieza
     * @param cantidad Cantidad solicitada
     * @return información sobre si ha completado el pedido o no
     */
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

    private Pieza getPiezaById(int id) {
        for (int i = 0; i < piezaList.size(); i++) {
            if (piezaList.get(i).getId() == id) {
                return piezaList.get(i);
            }
        }
        return null;
    }

    private Proveedor getProveedorByCif(String cif) {
        for (Proveedor p : proveedorList) {
            if (cif.equals(p.getCif())) {
                return p;
            }
        }
        return null;
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

    /**
     * @param cif cif del proveedor
     * @return un String con el pedido o información si no existe el proveedor
     */
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

    @Override
    public String toString() {
        return "AlmacenController{" + "\n" +
                "proveedorList=" + proveedorList + "\n" +
                "piezaList=" + piezaList + "\n" +
                "pedidoList=" + pedidoList + "\n" +
                "}" + "\n";
    }
}
