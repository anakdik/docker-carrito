package com.TiendaPeluches.carrito.dto;

public class LoginResponseDTO {
    private String token;
    private UsuarioDTO usuario;

    public LoginResponseDTO(String token, UsuarioDTO usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    public String getToken() { return token; }
    public UsuarioDTO getUsuario() { return usuario; }
}
