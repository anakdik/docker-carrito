package com.TiendaPeluches.carrito.security;

import com.TiendaPeluches.carrito.entity.Usuario;
import com.TiendaPeluches.carrito.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String correo)
            throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado: " + correo)
                );

        return User.builder()
                .username(usuario.getCorreo())
                .password(usuario.getContrasena())
                .roles("USER") // puedes cambiarlo luego
                .build();
    }
}
