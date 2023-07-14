package org.tattour.server.global.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
	name = "JWT Auth",
	type = SecuritySchemeType.HTTP,
	bearerFormat = "JWT",
	scheme = "bearer"
)
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
			.title("API Document")
			.description("Tattour API Document")
			.version("v1");
		return new OpenAPI()
			.components(new Components())
			.info(info);
	}
}