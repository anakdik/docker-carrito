package com.TiendaPeluches.carrito.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CarritoDTO {
    private Long id;
    private UsuarioDTO usuario;
    private Date fechaCreacion;
    private List<DetalleCarritoDTO> detalles;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public UsuarioDTO getUsuario() { return usuario; }
    public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public List<DetalleCarritoDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleCarritoDTO> detalles) { this.detalles = detalles; }

    // Conversión de entidad a DTO
    public static CarritoDTO fromEntity(com.TiendaPeluches.carrito.entity.Carrito carrito,
                                        java.util.List<com.TiendaPeluches.carrito.entity.DetalleCarrito> detalles) {
        CarritoDTO dto = new CarritoDTO();
        dto.setId(carrito.getId());
        dto.setFechaCreacion(carrito.getFechaCreacion());
        dto.setUsuario(UsuarioDTO.fromEntity(carrito.getUsuario()));
        dto.setDetalles(detalles.stream()
                .map(DetalleCarritoDTO::fromEntity)
                .collect(Collectors.toList()));
        return dto;
    }
}
