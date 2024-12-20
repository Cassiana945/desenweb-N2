package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite todas as rotas
                .allowedOrigins("http://localhost:3000") // Permite que o front-end no localhost:3000 faça requisições
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permite apenas os métodos específicos
                .allowedHeaders("*"); // Permite todos os cabeçalhos
    }
}
