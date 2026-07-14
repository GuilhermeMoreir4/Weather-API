package com.example.whaterapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Weather API Wrapper")
                        .version("1.0.0")
                        .description("""
                                REST API that wraps the Visual Crossing Weather service, providing current conditions \
                                and a multi-day forecast for any city.

                                **Features**
                                - Responses cached in Redis for 1 hour to reduce upstream calls
                                - Rate-limited to 10 requests per minute per IP address
                                """)
                        .contact(new Contact()
                                .name("Guilherme Jacinto")
                                .url("https://github.com/guilhermejacinto")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local development")));
    }
}
