package com.TiendaPeluches.carrito.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TiendaPeluches.carrito.dto.PedidoDTO;
import com.TiendaPeluches.carrito.entity.DetalleCarrito;
import com.TiendaPeluches.carrito.entity.DetallePedido;
import com.TiendaPeluches.carrito.entity.Pedido;
import com.TiendaPeluches.carrito.entity.Producto;
import com.TiendaPeluches.carrito.entity.Usuario;
import com.TiendaPeluches.carrito.exception.CarritoVacioException;
import com.TiendaPeluches.carrito.repository.DetalleCarritoRepository;
import com.TiendaPeluches.carrito.repository.DetallePedidoRepository;
import com.TiendaPeluches.carrito.repository.PedidoRepository;
import com.TiendaPeluches.carrito.repository.ProductoRepository;
import com.TiendaPeluches.carrito.repository.UsuarioRepository;

@Service
@Transactional
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    @Autowired
    private DetallePedidoRepository detallePedidoRepo;

    @Autowired
    private DetalleCarritoRepository carritoDetalleRepo;

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Override
    public PedidoDTO crearPedido(Long idUsuario) {

        Usuario usuario = usuarioRepo.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<DetalleCarrito> carrito = carritoService.listarDetalles(idUsuario).stream()
                .map(dto -> {
                    DetalleCarrito d = new DetalleCarrito();
                    d.setCantidad(dto.getCantidad());
                    d.setPrecioUnitario(dto.getPrecioUnitario());

                    Producto p = new Producto();
                    p.setId(dto.getProducto().getId());
                    d.setProducto(p);

                    return d;
                }).toList();

        if (carrito.isEmpty()) {
            throw new CarritoVacioException("Carrito vacío");
        }

        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFechaPedido(new Date());
        pedido.setEstado("Pendiente");

        double total = carrito.stream()
                .mapToDouble(d -> d.getCantidad() * d.getPrecioUnitario())
                .sum();

        pedido.setTotal(total);
        pedidoRepo.save(pedido);

        for (DetalleCarrito d : carrito) {

            Producto producto = productoRepo.findById(d.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no existe"));

            if (producto.getStock() < d.getCantidad()) {
                throw new RuntimeException(
                        "Stock insuficiente para el producto: " + producto.getNombre());
            }

            producto.setStock(producto.getStock() - d.getCantidad());
            productoRepo.save(producto);

            DetallePedido dp = new DetallePedido();
            dp.setPedido(pedido);
            dp.setProducto(producto);
            dp.setCantidad(d.getCantidad());
            dp.setPrecioUnitario(d.getPrecioUnitario());
            dp.setSubtotal(d.getCantidad() * d.getPrecioUnitario());

            detallePedidoRepo.save(dp);
        }

        carritoDetalleRepo.deleteAll(carrito);

        List<DetallePedido> detalles = detallePedidoRepo.findByPedido_Id(pedido.getId());
        return PedidoDTO.fromEntity(pedido, detalles);
    }

    @Override
    public List<PedidoDTO> listarPedidos(Long idUsuario) {

        List<Pedido> pedidos = pedidoRepo.findByUsuario_Id(idUsuario);

        return pedidos.stream()
                .map(p -> {
                    List<DetallePedido> detalles = detallePedidoRepo.findByPedido_Id(p.getId());
                    return PedidoDTO.fromEntity(p, detalles);
                })
                .toList();
    }
}
