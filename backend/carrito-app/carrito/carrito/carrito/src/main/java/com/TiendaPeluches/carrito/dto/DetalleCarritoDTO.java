package com.TiendaPeluches.carrito.dto;

public class DetalleCarritoDTO {
    private Long id;
    private ProductoDTO producto;
    private Integer cantidad;
    private Double precioUnitario;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ProductoDTO getProducto() { return producto; }
    public void setProducto(ProductoDTO producto) { this.producto = producto; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    // Conversión de entidad a DTO
    public static DetalleCarritoDTO fromEntity(com.TiendaPeluches.carrito.entity.DetalleCarrito detalle) {
        DetalleCarritoDTO dto = new DetalleCarritoDTO();
        dto.setId(detalle.getId());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        dto.setProducto(ProductoDTO.fromEntity(detalle.getProducto()));
        return dto;
    }
}
