package br.com.app.infra.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.app.infra.security.utils.SecurityUtils;
import br.com.app.usuario.dao.UsuarioEntity;
import br.com.app.usuario.dao.UsuarioRepository;
import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;

@Component
public class ContextInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SecurityUtils securityUtils;

    /*
     * Executa ao iniciar a aplicação
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (!usuarioRepository.existsByEmail("admin@admin.com.br")) { // Caso não existe um usuário padrão, o mesmo é criado
            UsuarioEntity usuario = new UsuarioEntity();
            usuario.setEmail("admin@admin.com.br");
            usuario.setPerfil(PerfilEnum.ROLE_ADMIN);
            usuario.setSituacao(SituacaoUserEnum.ATIVO);
            usuario.setSenha(securityUtils.gerarSenha("Admin@Admin1"));
            usuarioRepository.save(usuario);
        }

    }

}
