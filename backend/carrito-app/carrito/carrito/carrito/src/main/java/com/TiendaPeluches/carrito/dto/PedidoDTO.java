package com.TiendaPeluches.carrito.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PedidoDTO {
    private Long id;
    private UsuarioDTO usuario;
    private Date fechaPedido;
    private Double total;
    private String estado;
    private List<DetallePedidoDTO> detalles;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
    public Date getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(Date fechaPedido) { this.fechaPedido = fechaPedido; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public List<DetallePedidoDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedidoDTO> detalles) { this.detalles = detalles; }

    // Conversión de entidad a DTO
    public static PedidoDTO fromEntity(com.TiendaPeluches.carrito.entity.Pedido pedido,
                                       java.util.List<com.TiendaPeluches.carrito.entity.DetallePedido> detalles) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setFechaPedido(pedido.getFechaPedido());
        dto.setTotal(pedido.getTotal());
        dto.setEstado(pedido.getEstado());
        dto.setUsuario(UsuarioDTO.fromEntity(pedido.getUsuario()));
        dto.setDetalles(detalles.stream()
                .map(DetallePedidoDTO::fromEntity)
                .collect(Collectors.toList()));
        return dto;
    }
}
