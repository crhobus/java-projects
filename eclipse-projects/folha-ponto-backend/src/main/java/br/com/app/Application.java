package br.com.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.app.infra.logger.AppLogger;

@SpringBootApplication
public class Application {

    @Value("${paginacao.qtd_registros_pagina}") // Exemplo recupera uma propriedade do arquivo application.properties do spring
    private int qtdRegistrosPagina;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            AppLogger.LOGGER.info("### Quantidade registros por página: {}", qtdRegistrosPagina);
        };
    }

}
