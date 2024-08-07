package com.reto.proyecto.proyecto.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI customOpenApi() {
    return new OpenAPI().components(new Components()
        .addSecuritySchemes("BearerAuth",
          new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")))
      .info(new Info().title("Api Asignación").version("1.0.0"))
      .addSecurityItem(new SecurityRequirement().addList("BearerAuth"));
  }
}
