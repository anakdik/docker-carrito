package com.TiendaPeluches.carrito.dto;

import java.util.Date;

public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private String categoria;
    private String tamano;
    private String color;
    private Date fechaAlta;

    // 🔥 NUEVO CAMPO PARA LA IMAGEN
    private String imagenUrl;

    // --------------------
    // GETTERS Y SETTERS
    // --------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    // --------------------
    // ENTITY ➜ DTO
    // --------------------
    public static ProductoDTO fromEntity(com.TiendaPeluches.carrito.entity.Producto producto) {

        ProductoDTO dto = new ProductoDTO();

        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria());
        dto.setTamano(producto.getTamano());
        dto.setColor(producto.getColor());
        dto.setFechaAlta(producto.getFechaAlta());

        // 🔥 LÍNEA CLAVE
        dto.setImagenUrl(producto.getImagenUrl());

        return dto;
    }
}
