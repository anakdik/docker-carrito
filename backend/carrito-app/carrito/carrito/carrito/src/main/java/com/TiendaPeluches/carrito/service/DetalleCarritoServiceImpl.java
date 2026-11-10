package com.TiendaPeluches.carrito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TiendaPeluches.carrito.entity.DetalleCarrito;
import com.TiendaPeluches.carrito.repository.DetalleCarritoRepository;

@Service
public class DetalleCarritoServiceImpl implements DetalleCarritoService {

    @Autowired
    private DetalleCarritoRepository repo;

    @Override
    public List<DetalleCarrito> listarPorCarrito(Long idCarrito) {
        return repo.findByCarrito_Id(idCarrito);
    }

    @Override
    public DetalleCarrito guardar(DetalleCarrito detalle) {
        return repo.save(detalle);
    }

    @Override
    public void eliminar(Long idDetalle) {
        repo.deleteById(idDetalle);
    }
}
