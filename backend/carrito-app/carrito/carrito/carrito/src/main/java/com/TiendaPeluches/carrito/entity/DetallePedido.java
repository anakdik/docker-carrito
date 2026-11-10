package com.TiendaPeluches.carrito.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DETALLE_PEDIDO")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_pedido_seq")
    @SequenceGenerator(name = "detalle_pedido_seq", sequenceName = "detalle_pedido_seq", allocationSize = 1)
    @Column(name = "ID_DETALLE_PEDIDO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "PRECIO_UNITARIO", nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double subtotal;

    // Getters y Setters...


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
