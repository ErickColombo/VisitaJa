package br.com.visitaja.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("VisitaJá API - Gestão de Agendamentos em Pátios de Leilão")
                        .description("Documentação oficial da REST API do sistema VisitaJá. " +
                                "Esta aplicação gerencia o fluxo completo de agendamentos de visitas a pátios de leilão, " +
                                "permitindo controle de horários, vagas, cadastro de visitantes e validação de acessos.")
                        .version("v1.0.0")
                        .termsOfService("https://www.visitaja.com.br/terms")
                        .contact(new Contact()
                                .name("Equipe de Desenvolvimento VisitaJá")
                                .email("suporte@visitaja.com.br")
                                .url("https://www.visitaja.com.br")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                )

                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(
                                SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("Insira o token JWT gerado no endpoint de login no formato: Bearer {seu_token}")
                        )
                );
    }
}