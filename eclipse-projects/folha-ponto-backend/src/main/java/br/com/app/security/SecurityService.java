package br.com.app.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.app.usuario.UsuarioService;
import br.com.app.usuario.dao.UsuarioEntity;

@Service
public class SecurityService implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuario = usuarioService.getUsuario(username);

        if (usuario.isPresent()) {
            return JwtUserFactory.create(usuario.get());
        }

        throw new UsernameNotFoundException("Email não encontrado");
    }

}
