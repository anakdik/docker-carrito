package com.TiendaPeluches.carrito.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "DETALLE_CARRITO")
public class DetalleCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_carrito_seq")
    @SequenceGenerator(name = "detalle_carrito_seq", sequenceName = "detalle_carrito_seq", allocationSize = 1)
    @Column(name = "ID_DETALLE")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "ID_CARRITO", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(name = "PRECIO_UNITARIO", nullable = false)
    private Double precioUnitario;

    // Getters y Setters...


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
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
}
