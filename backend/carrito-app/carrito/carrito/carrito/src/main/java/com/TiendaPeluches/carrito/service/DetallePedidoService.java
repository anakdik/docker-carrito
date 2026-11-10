package com.TiendaPeluches.carrito.service;

import com.TiendaPeluches.carrito.entity.DetallePedido;
import java.util.List;

public interface DetallePedidoService {

    List<DetallePedido> listarPorPedido(Long idPedido);

    DetallePedido guardar(DetallePedido detalle);

    void eliminar(Long idDetallePedido);
}
