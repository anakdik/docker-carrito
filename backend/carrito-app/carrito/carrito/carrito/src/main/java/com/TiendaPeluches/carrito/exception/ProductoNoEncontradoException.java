package com.TiendaPeluches.carrito.exception;

public class ProductoNoEncontradoException extends RuntimeException {
    public ProductoNoEncontradoException(String msg) {
        super(msg);
    }
}
