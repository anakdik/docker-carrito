package com.TiendaPeluches.carrito.service;

import com.TiendaPeluches.carrito.entity.DetalleCarrito;
import java.util.List;

public interface DetalleCarritoService {

    List<DetalleCarrito> listarPorCarrito(Long idCarrito);

    DetalleCarrito guardar(DetalleCarrito detalle);

    void eliminar(Long idDetalle);
}
