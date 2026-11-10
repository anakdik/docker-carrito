package com.TiendaPeluches.carrito.repository;

import java.util.Optional;

import com.TiendaPeluches.carrito.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}
