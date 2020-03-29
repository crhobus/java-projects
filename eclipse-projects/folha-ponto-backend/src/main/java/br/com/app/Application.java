package br.com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.app.infra.logger.AppLogger;
import br.com.app.infra.security.utils.SecurityUtils;
import br.com.app.usuario.dao.UsuarioEntity;
import br.com.app.usuario.dao.UsuarioRepository;
import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

    @Value("${paginacao.qtd_registros_pagina}")
    private int qtdRegistrosPagina;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityUtils securityUtils;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            AppLogger.LOGGER.info("### Quantidade registros por página: {}", qtdRegistrosPagina);

            // Caso não existe um usuário padrão, o mesmo é criado
            if (!usuarioRepository.existsByEmail("admin@admin.com.br")) {

                UsuarioEntity usuario = new UsuarioEntity();
                usuario.setEmail("admin@admin.com.br");
                usuario.setPerfil(PerfilEnum.ROLE_ADMIN);
                usuario.setSituacao(SituacaoUserEnum.ATIVO);
                usuario.setSenha(securityUtils.gerarSenha("Admin@Admin1"));
                usuarioRepository.save(usuario);

            }

        };
    }

}
