package com.example.demo.config;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Spotify Clone API")
                .version("1.0")
                .description("API for managing a minimalist Spotify version")
                .termsOfService("https://example.com/terms")
                .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                .contact(new io.swagger.v3.oas.models.info.Contact()
                    .name("Jorge Mojica")
                    .email("jorgemojica@minispotify.com")
                    .url("https://minispotify.com")));
    }

}
