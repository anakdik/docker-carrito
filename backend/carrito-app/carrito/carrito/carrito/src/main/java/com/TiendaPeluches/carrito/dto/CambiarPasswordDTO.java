package com.TiendaPeluches.carrito.dto;

import jakarta.validation.constraints.NotBlank;

public class CambiarPasswordDTO {

    @NotBlank
    private String actual;

    @NotBlank
    private String nueva;

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getNueva() {
        return nueva;
    }

    public void setNueva(String nueva) {
        this.nueva = nueva;
    }
}
