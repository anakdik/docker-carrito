package com.TiendaPeluches.carrito.dto;

public class DetallePedidoDTO {
    private Long id;
    private ProductoDTO producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public ProductoDTO getProducto() { return producto; }
    public void setProducto(ProductoDTO producto) { this.producto = producto; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }
    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }

    // Conversión de entidad a DTO
    public static DetallePedidoDTO fromEntity(com.TiendaPeluches.carrito.entity.DetallePedido detalle) {
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setId(detalle.getId());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        dto.setSubtotal(detalle.getSubtotal());
        dto.setProducto(ProductoDTO.fromEntity(detalle.getProducto()));
        return dto;
    }
}
