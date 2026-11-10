package com.TiendaPeluches.carrito.service;

import java.util.List;
import com.TiendaPeluches.carrito.dto.PedidoDTO;

public interface PedidoService {
    PedidoDTO crearPedido(Long idUsuario);
    List<PedidoDTO> listarPedidos(Long idUsuario);
}
