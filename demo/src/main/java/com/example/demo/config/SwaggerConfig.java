package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

@Bean
public OpenAPI customOpenAPI() {
    return new OpenAPI().info(new Info().title("Atividade").version("1.0.0"));
}

}

///http://localhost:8080/swagger-ui/index.html#/