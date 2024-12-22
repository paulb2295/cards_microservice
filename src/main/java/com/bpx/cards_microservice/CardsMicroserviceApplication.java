package com.bpx.cards_microservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Cards microservice REST API Documentation",
				description = "Bank App Cards microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "BP",
						email = "bpx@mail.com",
						url = "https://www.bpx.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.bpx.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Bank App Cards microservice REST API Documentation",
				url = "https://www.bpx.com/swagger-ui.html"
		)
)
@SpringBootApplication
public class CardsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsMicroserviceApplication.class, args);
	}

}
