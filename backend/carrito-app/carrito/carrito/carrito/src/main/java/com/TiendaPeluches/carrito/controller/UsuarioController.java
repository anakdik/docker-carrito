package com.TiendaPeluches.carrito.controller;

import java.util.Map;

import com.TiendaPeluches.carrito.dto.CambiarPasswordDTO;
import com.TiendaPeluches.carrito.security.JwtUtil;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.TiendaPeluches.carrito.dto.UsuarioDTO;
import com.TiendaPeluches.carrito.entity.Usuario;
import com.TiendaPeluches.carrito.repository.UsuarioRepository;
import com.TiendaPeluches.carrito.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class UsuarioController {

    @Autowired
    private UsuarioService servicio;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private JwtUtil jwtUtil;

    // ================= REGISTRO =================
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            UsuarioDTO creado = servicio.crearUsuario(usuarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {

        try {
            String correo = body.get("correo");
            String password = body.get("password");

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(correo, password)
            );

            Usuario usuario = usuarioRepo.findByCorreo(correo)
                    .orElseThrow();

            String token = jwtUtil.generarToken(correo);

            return ResponseEntity.ok(
                    Map.of(
                            "token", token,
                            "usuario", UsuarioDTO.fromEntity(usuario)
                    )
            );

        } catch (AuthenticationException ex) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas");
        }
    }

    // ================= PERFIL =================
    @GetMapping("/perfil")
    public ResponseEntity<UsuarioDTO> perfil(Authentication auth) {

        Usuario usuario = usuarioRepo.findByCorreo(auth.getName())
                .orElseThrow();

        return ResponseEntity.ok(UsuarioDTO.fromEntity(usuario));
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtener(@PathVariable Long id) {
        return servicio.obtenerPorId(id);
    }

    @PutMapping("/cambiar-password")
    public ResponseEntity<?> cambiarPassword(
            @RequestBody CambiarPasswordDTO dto,
            Authentication auth
    ) {

        servicio.cambiarPassword(
                auth.getName(), // correo desde JWT
                dto.getActual(),
                dto.getNueva()
        );

        return ResponseEntity.ok(
                Map.of("mensaje", "Contraseña actualizada correctamente")
        );
    }

}
