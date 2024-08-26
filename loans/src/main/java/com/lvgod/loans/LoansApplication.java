package com.lvgod.loans;

import com.lvgod.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*@ComponentScans({ @ComponentScan("com.lvgod.loans.controller") })
@EnableJpaRepositories("com.lvgod.loans.repository")
@EntityScan("com.lvgod.loans.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {LoansContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "lvgod Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Mr Andarabski",
						email = "tutor@lvgod.com",
						url = "https://www.lvgod.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.lvgod.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "lvgod Loans microservice REST API Documentation",
				url = "https://www.lvgod.com/swagger-ui.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
		System.out.println("Hello Mr Andarabski this Loans microservice");
	}
}
