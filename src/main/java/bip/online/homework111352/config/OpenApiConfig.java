package bip.online.homework111352.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Университет Хогвартс",
                description = "Система по работе со студентами и факультетами", version = "1.0.0",
                contact = @Contact(
                        name = "Александр"
                )
        )
)
public class OpenApiConfig {
}
