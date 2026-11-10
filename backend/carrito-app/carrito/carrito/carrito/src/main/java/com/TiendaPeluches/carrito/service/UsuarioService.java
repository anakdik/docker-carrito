package com.TiendaPeluches.carrito.service;

import com.TiendaPeluches.carrito.dto.UsuarioDTO;

public interface UsuarioService {
    UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO);
    UsuarioDTO login(String correo, String password);
    UsuarioDTO obtenerPorId(Long id);
    void cambiarPassword(String correo, String actual, String nueva);


}

