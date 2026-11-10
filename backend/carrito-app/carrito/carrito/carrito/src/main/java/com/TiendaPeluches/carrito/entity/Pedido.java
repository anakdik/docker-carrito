package com.TiendaPeluches.carrito.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_seq")
    @SequenceGenerator(name = "pedido_seq", sequenceName = "pedido_seq", allocationSize = 1)
    @Column(name = "ID_PEDIDO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "FECHA_PEDIDO")
    private Date fechaPedido;

    @Column(name = "TOTAL")
    private Double total;

    @Column(name = "ESTADO")
    private String estado;

    @PrePersist
    public void prePersist() {
        this.fechaPedido = new Date();
    }

    // Getters y Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
