package com.pixelpro.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    @Value("${FRONTEND_ORIGIN:http://localhost:5173}")
//    private String frontendOrigin;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(corsConfig()))
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/me").permitAll()
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutUrl("/api/auth/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                ).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    @Bean
//    AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
//        var provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder);
//        return provider;
//    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var c = new CorsConfiguration();
        // Lista de orígenes frontend
        c.setAllowedOrigins(List.of(
                "http://127.0.0.1:5500",
                "http://127.0.0.1:5501",
                "http://127.0.0.1:5502"
        ));
        c.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        c.setAllowedHeaders(List.of("*"));
        c.setAllowCredentials(true); // necesario para cookies de sesión
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", c);
        return source;
    }

//    private CorsConfigurationSource corsConfig() {
//        var corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedOrigins(List.of(frontendOrigin));
//        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//        corsConfig.setAllowedHeaders(List.of("*"));
//        corsConfig.setAllowCredentials(true);
//        var s = new UrlBasedCorsConfigurationSource();
//        s.registerCorsConfiguration("/**", corsConfig);
//        return s;
//    }
}
