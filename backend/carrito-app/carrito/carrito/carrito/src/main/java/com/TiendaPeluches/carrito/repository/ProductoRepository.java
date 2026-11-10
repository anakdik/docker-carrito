package com.TiendaPeluches.carrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.TiendaPeluches.carrito.entity.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
