package com.TiendaPeluches.carrito.repository;

import java.util.Optional;
import com.TiendaPeluches.carrito.entity.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByUsuario_Id(Long idUsuario);
}
