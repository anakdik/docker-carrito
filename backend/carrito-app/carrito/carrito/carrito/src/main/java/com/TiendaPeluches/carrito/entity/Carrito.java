package com.TiendaPeluches.carrito.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CARRITO")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carrito_seq")
    @SequenceGenerator(name = "carrito_seq", sequenceName = "carrito_seq", allocationSize = 1)
    @Column(name = "ID_CARRITO")
    private Long id;
    ;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(name = "FECHA_CREACION")
    private Date fechaCreacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = new Date();
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
