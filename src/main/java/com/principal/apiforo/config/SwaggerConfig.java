package com.principal.apiforo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API FORO",
                description = "En esta API se puede hacer uso de las operaciones basicas de un foro, manejando tanto perfiles como usuarios, categorias, etc. Los parametros y campos necesarios se hacen evidencia en la API.",
                version = "1.0.0",
                contact = @Contact(
                        name = "Brayan Ruiz Marreros"),
                license = @License(
                        name = "Standard Software Use License for UnProgramadorNace",
                        url = "www.unprogramadornace.com/licence"
                )
        ),
        security = @SecurityRequirement(
                name = "Security Token"
        )
)

@SecurityScheme(
        name = "Security Token",
        description = "Access Token For My API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
