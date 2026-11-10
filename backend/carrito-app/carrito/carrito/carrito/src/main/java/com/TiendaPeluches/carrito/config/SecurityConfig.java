package com.TiendaPeluches.carrito.config;

import com.TiendaPeluches.carrito.security.JwtFilter;
import com.TiendaPeluches.carrito.security.CustomUserDetailsService;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // ✅ CORS configurado correctamente para Angular + JWT
        http.cors(cors -> cors.configurationSource(request -> {
            CorsConfiguration cfg = new CorsConfiguration();
            cfg.setAllowedOrigins(List.of("http://localhost:4200"));
            cfg.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
            cfg.setAllowedHeaders(List.of("*"));
            cfg.setAllowCredentials(true); // permite enviar headers de Authorization
            return cfg;
        }));

        http.csrf(AbstractHttpConfigurer::disable);

        // 🔑 Stateless JWT
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // 🔐 Authentication Provider
        http.authenticationProvider(authProvider());

        // ⚡ Endpoints públicos y privados
        http.authorizeHttpRequests(auth -> auth
                // 🔓 PÚBLICOS
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuarios/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                .requestMatchers(HttpMethod.GET, "/productos/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/home").permitAll() // home público

                // 🔐 PRIVADOS
                .requestMatchers("/usuarios/perfil").authenticated()
                .requestMatchers("/carrito/**").authenticated()
                .requestMatchers("/pedidos/**").authenticated()

                .anyRequest().authenticated()
        );

        // 🔑 JWT Filter
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
