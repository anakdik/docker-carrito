package com.TiendaPeluches.carrito.controller;

import java.util.List;

import com.TiendaPeluches.carrito.entity.Usuario;
import com.TiendaPeluches.carrito.repository.UsuarioRepository;
import com.TiendaPeluches.carrito.service.CarritoService;
import com.TiendaPeluches.carrito.dto.DetalleCarritoDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "http://localhost:4200")
public class CarritoController {

    @Autowired
    private CarritoService servicio;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping
    public List<DetalleCarritoDTO> listar(Authentication auth) {

        String correo = auth.getName(); // 👈 YA NO ES NULL

        Usuario usuario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return servicio.listarDetalles(usuario.getId());
    }

    @PostMapping("/agregar")
    public DetalleCarritoDTO agregar(
            Authentication auth,
            @RequestParam Long idProducto,
            @RequestParam Integer cantidad) {

        String correo = auth.getName();

        Usuario usuario = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return servicio.agregarProducto(usuario.getId(), idProducto, cantidad);
    }

    @DeleteMapping("/detalle/{idDetalle}")
    public void eliminar(@PathVariable Long idDetalle) {
        servicio.eliminarDetalle(idDetalle);
    }
}
