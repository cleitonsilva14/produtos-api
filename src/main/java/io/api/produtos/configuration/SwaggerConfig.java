package io.api.produtos.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
        info = @Info(
                title = "Produto API",
                description = "Api de produtos",
                summary = "My API to manager products for ecommerce",
                termsOfService = "https://app.swaggerhub.com/eula",
                license = @License(
                        name = "GPL 3.0",
                        url = "https://opensource.org/license/gpl-3-0"
                ),
                contact = @Contact(
                        name = "SpringBoot Developer",
                        email = "127.0.0.1@localhost.com",
                        url = "https://springdoc.org/"
                )
        )
)
public class SwaggerConfig {

}
