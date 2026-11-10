package com.TiendaPeluches.carrito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TiendaPeluches.carrito.dto.DetalleCarritoDTO;
import com.TiendaPeluches.carrito.entity.Carrito;
import com.TiendaPeluches.carrito.entity.DetalleCarrito;
import com.TiendaPeluches.carrito.entity.Producto;
import com.TiendaPeluches.carrito.entity.Usuario;
import com.TiendaPeluches.carrito.exception.CarritoVacioException;
import com.TiendaPeluches.carrito.exception.ProductoNoEncontradoException;
import com.TiendaPeluches.carrito.exception.UsuarioNoEncontradoException;
import com.TiendaPeluches.carrito.repository.CarritoRepository;
import com.TiendaPeluches.carrito.repository.DetalleCarritoRepository;
import com.TiendaPeluches.carrito.repository.ProductoRepository;
import com.TiendaPeluches.carrito.repository.UsuarioRepository;
import com.TiendaPeluches.carrito.service.CarritoService;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepo;
    @Autowired
    private DetalleCarritoRepository detalleRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private UsuarioRepository usuarioRepo;

    private Carrito obtenerCarrito(Long idUsuario) {
        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con id " + idUsuario));

        return carritoRepo.findByUsuario_Id(idUsuario)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setUsuario(usuario);
                    return carritoRepo.save(nuevo);
                });
    }

    @Override
    public List<DetalleCarritoDTO> listarDetalles(Long idUsuario) {
        Carrito carrito = obtenerCarrito(idUsuario);
        List<DetalleCarrito> detalles = detalleRepo.findByCarrito_Id(carrito.getId());

        return detalles.stream()
                .map(DetalleCarritoDTO::fromEntity)
                .toList();
    }

    @Override
    public DetalleCarritoDTO agregarProducto(Long idUsuario, Long idProducto, Integer cantidad) {

        Carrito carrito = obtenerCarrito(idUsuario);

        Producto producto = productoRepo.findById(idProducto)
                .orElseThrow(() ->
                        new ProductoNoEncontradoException("Producto no encontrado con id " + idProducto));

        // ✅ BUSCAR SI YA EXISTE EL PRODUCTO EN EL CARRITO
        DetalleCarrito detalle = detalleRepo
                .findByCarrito_IdAndProducto_Id(carrito.getId(), idProducto)
                .orElse(null);

        if (detalle != null) {
            // 🟢 YA EXISTE → SOLO ACTUALIZAMOS
            detalle.setCantidad(detalle.getCantidad() + cantidad);
        } else {
            // 🔵 NO EXISTE → CREAMOS
            detalle = new DetalleCarrito();
            detalle.setCarrito(carrito);
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(producto.getPrecio());
        }

        return DetalleCarritoDTO.fromEntity(detalleRepo.save(detalle));
    }


    @Override
    public void eliminarDetalle(Long idDetalle) {
        detalleRepo.deleteById(idDetalle);
    }
}
