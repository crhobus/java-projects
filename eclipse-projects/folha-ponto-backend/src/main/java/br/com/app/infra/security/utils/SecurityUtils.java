package br.com.app.infra.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {

    public String gerarSenha(String senha) {
        if (senha == null) {
            return null;
        }

        BCryptPasswordEncoder bCryptPass = new BCryptPasswordEncoder();
        return bCryptPass.encode(senha);
    }

    public boolean validaSenha(String senha, String senhaEncoded) {
        BCryptPasswordEncoder bCryptPass = new BCryptPasswordEncoder();
        return bCryptPass.matches(senha, senhaEncoded);
    }
}
