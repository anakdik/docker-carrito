package com.TiendaPeluches.carrito.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ProductoNoEncontradoException.class,
            UsuarioNoEncontradoException.class,
            CarritoVacioException.class,
            StockInsuficienteException.class,
            LoginException.class
    })
    public ResponseEntity<Map<String,String>> handleCustomExceptions(RuntimeException ex){
        Map<String,String> resp = new HashMap<>();
        resp.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(resp);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleGeneralExceptions(Exception ex){
        Map<String,String> resp = new HashMap<>();
        resp.put("error", "Ocurrió un error inesperado: " + ex.getMessage());
        return ResponseEntity.internalServerError().body(resp);
    }
}
