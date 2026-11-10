package com.TiendaPeluches.carrito.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String msg) {
        super(msg);
    }
}
