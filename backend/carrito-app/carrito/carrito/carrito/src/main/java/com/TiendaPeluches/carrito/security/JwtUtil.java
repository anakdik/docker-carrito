package com.TiendaPeluches.carrito.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // 🔑 CLAVE SECRETA (mínimo 32 caracteres)
    private final String SECRET = "mi_clave_secreta_super_segura_123456";

    // ⏱ 24 horas
    private final long EXPIRACION = 1000 * 60 * 60 * 24;

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // 🔹 GENERAR TOKEN
    public String generarToken(String correo) {
        return Jwts.builder()
                .setSubject(correo)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRACION))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 EXTRAER CORREO
    public String extraerCorreo(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // 🔹 VALIDAR TOKEN
    public boolean validarToken(String token, UserDetails userDetails) {
        final String correo = extraerCorreo(token);
        return correo.equals(userDetails.getUsername())
                && !estaExpirado(token);
    }

    private boolean estaExpirado(String token) {
        Date expiracion = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiracion.before(new Date());
    }
}
