package com.TiendaPeluches.carrito.repository;

import java.util.List;
import com.TiendaPeluches.carrito.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
    List<DetallePedido> findByPedido_Id(Long idPedido);
}
