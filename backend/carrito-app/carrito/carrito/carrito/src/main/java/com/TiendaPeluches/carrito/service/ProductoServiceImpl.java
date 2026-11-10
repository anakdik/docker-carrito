package com.TiendaPeluches.carrito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TiendaPeluches.carrito.dto.ProductoDTO;
import com.TiendaPeluches.carrito.entity.Producto;
import com.TiendaPeluches.carrito.exception.ProductoNoEncontradoException;
import com.TiendaPeluches.carrito.repository.ProductoRepository;
import com.TiendaPeluches.carrito.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> listar() {
        List<Producto> productos = repo.findAll();
        return productos.stream()
                .map(ProductoDTO::fromEntity)
                .toList();
    }

    @Override
    public ProductoDTO obtener(Long id) {
        Producto producto = repo.findById(id)
                .orElseThrow(() -> new ProductoNoEncontradoException("Producto no encontrado con id " + id));
        return ProductoDTO.fromEntity(producto);
    }
}
