package com.TiendaPeluches.carrito.service;

import java.util.List;

import com.TiendaPeluches.carrito.dto.DetalleCarritoDTO;

public interface CarritoService {
    List<DetalleCarritoDTO> listarDetalles(Long idUsuario);
    DetalleCarritoDTO agregarProducto(Long idUsuario, Long idProducto, Integer cantidad);
    void eliminarDetalle(Long idDetalle);
}
