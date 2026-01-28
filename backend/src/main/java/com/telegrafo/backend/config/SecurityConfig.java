package com.telegrafo.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())

                .csrf(AbstractHttpConfigurer::disable)

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios", "/usuarios/auth").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios/contatos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/contatos/**").permitAll()
                        .requestMatchers("/mensagens/historico/**").permitAll()
                        .requestMatchers("/mensagens/**").permitAll()
                        .requestMatchers("/ws-telegrafo/**").permitAll()
                        .requestMatchers("/chat/**").permitAll()
                        .requestMatchers("/notificacoes/{usuarioId}").permitAll()
                        .requestMatchers("/notificacoes/{id}/ler").permitAll()
                        .requestMatchers("mensagens/notificacoes/{usuarioId}").permitAll()
                        .requestMatchers("mensagens/notificacoes/{id}/ler").permitAll()

                        .anyRequest().authenticated()
                )

                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}