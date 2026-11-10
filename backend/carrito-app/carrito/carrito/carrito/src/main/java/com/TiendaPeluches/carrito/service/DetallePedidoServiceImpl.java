package com.TiendaPeluches.carrito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TiendaPeluches.carrito.entity.DetallePedido;
import com.TiendaPeluches.carrito.repository.DetallePedidoRepository;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    @Autowired
    private DetallePedidoRepository repo;

    @Override
    public List<DetallePedido> listarPorPedido(Long idPedido) {
        return repo.findByPedido_Id(idPedido);
    }

    @Override
    public DetallePedido guardar(DetallePedido detalle) {
        return repo.save(detalle);
    }

    @Override
    public void eliminar(Long idDetallePedido) {
        repo.deleteById(idDetallePedido);
    }
}
