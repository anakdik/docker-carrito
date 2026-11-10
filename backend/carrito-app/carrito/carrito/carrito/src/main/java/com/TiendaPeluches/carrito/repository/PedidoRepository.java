package com.TiendaPeluches.carrito.repository;

import java.util.List;
import com.TiendaPeluches.carrito.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByUsuario_Id(Long idUsuario);
}
