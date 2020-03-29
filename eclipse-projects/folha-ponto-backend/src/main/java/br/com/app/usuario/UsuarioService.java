package br.com.app.usuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.infra.security.utils.SecurityUtils;
import br.com.app.usuario.dao.UsuarioEntity;
import br.com.app.usuario.dao.UsuarioRepository;
import br.com.app.usuario.dto.PerfilEnum;
import br.com.app.usuario.dto.SituacaoUserEnum;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    @Autowired
    private SecurityUtils securityUtils;

    @Transactional(readOnly = true)
    public Optional<UsuarioEntity> getUsuario(String email) {
        return Optional.ofNullable(repository.findByEmail(email));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public UsuarioEntity create(String email, String senha, SituacaoUserEnum situacao, PerfilEnum perfil) throws Exception {
        if (repository.existsByEmail(email)) {
            throw new Exception("E-mail inválido, o mesmo já se encontra cadastrado");
        }

        UsuarioEntity usuario = mapper.toEntity(securityUtils, email, senha, situacao, perfil);

        return repository.save(usuario);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public UsuarioEntity update(long id, String email, String senha, SituacaoUserEnum situacao, PerfilEnum perfil) throws Exception {
        Optional<UsuarioEntity> usuarioOpt = repository.findById(id);
        if (usuarioOpt.isPresent()) {
            UsuarioEntity usuario = usuarioOpt.get();

            if (repository.existsByEmailIgnoringCurrentUser(id, email)) {
                throw new Exception("E-mail inválido, o mesmo já se encontra cadastrado");
            }

            mapper.merge(usuario, securityUtils, email, senha, situacao, perfil);

            return repository.save(usuario);
        }
        return null;
    }
}
