package br.com.app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.app.usuario.dao.UsuarioEntity;
import br.com.app.usuario.dto.PerfilEnum;

public class JwtUserFactory {

    private JwtUserFactory() {}

    /**
     * Converte e gera um JwtUser com base nos dados de um usuário.
     * 
     * @param usuario
     * @return JwtUser
     */
    public static JwtUser create(UsuarioEntity usuario) {
        return new JwtUser(usuario.getId(), usuario.getEmail(), usuario.getSenha(), mapToGrantedAuthorities(usuario.getPerfil()));
    }

    /**
     * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
     * 
     * @param perfil
     * @return List<GrantedAuthority>
     */
    private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfil) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(perfil.name()));
        return authorities;
    }
}
