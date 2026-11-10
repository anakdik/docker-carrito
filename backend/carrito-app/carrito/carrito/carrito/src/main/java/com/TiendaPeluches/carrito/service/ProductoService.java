package com.TiendaPeluches.carrito.service;

import java.util.List;

import com.TiendaPeluches.carrito.dto.ProductoDTO;

public interface ProductoService {
    List<ProductoDTO> listar();
    ProductoDTO obtener(Long id);
}
