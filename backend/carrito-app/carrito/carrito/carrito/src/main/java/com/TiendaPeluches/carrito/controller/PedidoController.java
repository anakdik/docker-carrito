package com.TiendaPeluches.carrito.controller;

import java.util.List;

import com.TiendaPeluches.carrito.entity.Usuario;
import com.TiendaPeluches.carrito.repository.UsuarioRepository;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.TiendaPeluches.carrito.dto.PedidoDTO;
import com.TiendaPeluches.carrito.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:4200")
public class PedidoController {

    @Autowired
    private PedidoService servicio;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @PostMapping
    public PedidoDTO crearPedido(Authentication auth) {

        if (auth == null || !auth.isAuthenticated()) {
            throw new RuntimeException("Usuario no autenticado");
        }

        String correo = auth.getName();

        Usuario usuario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return servicio.crearPedido(usuario.getId());
    }

    @GetMapping
    public List<PedidoDTO> listar(Authentication auth) {

        String correo = auth.getName();

        Usuario usuario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return servicio.listarPedidos(usuario.getId());
    }
}
