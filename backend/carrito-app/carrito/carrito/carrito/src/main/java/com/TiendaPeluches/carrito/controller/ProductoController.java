package com.TiendaPeluches.carrito.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.TiendaPeluches.carrito.dto.ProductoDTO;
import com.TiendaPeluches.carrito.service.ProductoService;

@RestController
@RequestMapping("/productos")
@CrossOrigin(
        origins = "http://localhost:4200",
        allowCredentials = "true"
)
public class ProductoController {


    @Autowired
    private ProductoService servicio;

    @GetMapping
    public List<ProductoDTO> listar() {
        return servicio.listar();
    }

    @GetMapping("/{id}")
    public ProductoDTO obtener(@PathVariable Long id) {
        return servicio.obtener(id);
    }
}
