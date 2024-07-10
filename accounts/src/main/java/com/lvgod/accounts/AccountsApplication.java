package com.lvgod.accounts;

import com.lvgod.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
/*@ComponentScans({ @ComponentScan("com.lvgod.accounts.controller") })
@EnableJpaRepositories("com.lvgod.accounts.repository")
@EntityScan("com.lvgod.accounts.model")*/
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "lvgod Accounts microservice REST API Documentation",
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
				description =  "lvgod Accounts microservice REST API Documentation",
				url = "https://www.lvgod.com/swagger-ui.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
		System.out.println("Hello Mr Andarabski this Accounts microservice");
	}

}
