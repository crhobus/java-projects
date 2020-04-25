package br.com.app.infra.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // Habilita o Swagger
public class SwaggerConfig {

    /*
     * Informa ao Swagger como ele irá extrair as informações das APIs da aplicação
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select() //
                .apis(RequestHandlerSelectors.basePackage("br.com.app.handler")) // Define o pacote onde se encontra as APIs
                .paths(PathSelectors.any()) // Realiza qualquer tipo de busca
                .build() //
                .apiInfo(apiInfo());
    }

    /*
     * Documentação da parte gráfica
     */
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Swagger API") //
                .description("Documentação da API de acesso aos endpoints com Swagger") //
                .version("1.0") //
                .build();
    }
}
