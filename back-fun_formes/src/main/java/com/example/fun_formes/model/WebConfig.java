package com.example.fun_formes.model;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.lang.NonNull;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) { // Ajout de @NonNull
        registry.addMapping("/api/**") // URL de votre API
                .allowedOrigins("http://localhost:4200") // Origine autorisée
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Méthodes autorisées
                .allowCredentials(true); // Autoriser les cookies
    }
}
