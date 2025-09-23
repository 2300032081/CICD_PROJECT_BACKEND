package com.klu.CICDPROJECT.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
            .cors().and()
            .authorizeHttpRequests(auth -> auth
                // ✅ allow backend APIs
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/portfolio/**").permitAll()

                // ✅ allow static resources (images, css, js, uploads)
                .requestMatchers(
                    "/images/**",
                    "/css/**",
                    "/js/**",
                    "/static/**",
                    "/uploads/**"   // <-- allow uploaded images
                ).permitAll()

                // ✅ everything else also open (can restrict later)
                .anyRequest().permitAll()
            );

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // React dev server
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
