package com.TiendaPeluches.carrito.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getServletPath();
        String method = request.getMethod();

        // =======================
        // 🔓 RUTAS PÚBLICAS
        // =======================
        if (
                "OPTIONS".equals(method) ||
                        path.startsWith("/actuator") ||
                        path.equals("/usuarios/login") ||
                        (path.equals("/usuarios") && "POST".equals(method)) ||
                        path.startsWith("/productos") ||
                        path.equals("/error")
        ) {
            filterChain.doFilter(request, response);
            return;
        }

        // =======================
        // 🔐 RUTAS PROTEGIDAS
        // =======================
        String authHeader = request.getHeader("Authorization");

        // ❗ NO devolver error aquí → dejar que Security maneje el 401
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {
            String correo = jwtUtil.extraerCorreo(token);

            // Evita sobreescribir autenticación existente
            if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails =
                        userDetailsService.loadUserByUsername(correo);

                if (jwtUtil.validarToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }
            }

        } catch (Exception e) {
            // Token inválido → limpiar contexto, pero no romper la cadena
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
