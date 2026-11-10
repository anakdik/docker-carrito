    package com.TiendaPeluches.carrito.entity;

    import jakarta.persistence.*;
    import java.util.Date;

    @Entity
    @Table(name = "PRODUCTOS")
    public class Producto {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productos_seq")
        @SequenceGenerator(name = "productos_seq", sequenceName = "productos_seq", allocationSize = 1)
        @Column(name = "ID_PRODUCTO")
        private Long id;


        @Column(name = "NOMBRE", nullable = false)
        private String nombre;

        @Column(name = "DESCRIPCION")
        private String descripcion;

        @Column(name = "PRECIO", nullable = false)
        private Double precio;

        @Column(name = "STOCK", nullable = false)
        private Integer stock;

        @Column(name = "IMAGEN_URL", length = 500)
        private String imagenUrl;

        @Column(name = "CATEGORIA")
        private String categoria;

        @Column(name = "TAMANO")
        private String tamano;

        @Column(name = "COLOR")
        private String color;

        @Column(name = "FECHA_ALTA")
        private Date fechaAlta;



        @PrePersist
        protected void onCreate() {
            fechaAlta = new Date();
        }

        // Getters y Setters


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

        public String getImagenUrl() {
            return imagenUrl;
        }

        public void setImagenUrl(String imagenUrl) {
            this.imagenUrl = imagenUrl;
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
    }

