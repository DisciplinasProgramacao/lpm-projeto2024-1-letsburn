package com.api.letsburn_restaurante.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocOpenApiConfig {
  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI().info(new Info().title("🍖 Restaurante Let's burn").description(
        "A API Let's Burn oferece o  gerenciamento de pedidos, cardápios e informações essenciais relacionadas ao nosso restaurante vegano.")
        .version("v0").license(new License().name("CC-BY 4.0").url(
            "https://raw.githubusercontent.com/DisciplinasProgramacao/lpm-projeto2024-1-letsburn/master/LICENSE"))
        .contact(new Contact().name("Repositório da API")
            .url("https://github.com/DisciplinasProgramacao/lpm-projeto2024-1-letsburn")));
  }
}
