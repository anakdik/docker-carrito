package com.TiendaPeluches.carrito.repository;

import java.util.List;
import java.util.Optional;

import com.TiendaPeluches.carrito.entity.DetalleCarrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCarritoRepository extends JpaRepository<DetalleCarrito, Long> {

    List<DetalleCarrito> findByCarrito_Id(Long idCarrito);

    Optional<DetalleCarrito> findByCarrito_IdAndProducto_Id(
            Long carritoId,
            Long productoId
    );
}
