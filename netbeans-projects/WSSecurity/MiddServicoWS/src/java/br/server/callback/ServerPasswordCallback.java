package br.server.callback;
/*
 * Document   : ServerPasswordCallback.java
 * Created on : 21/09/2013, 18:50:15
 * Author     : Caio
 */

import br.server.action.ServiceAction;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

public class ServerPasswordCallback implements CallbackHandler {

    private String user;
    private String password;

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        ServiceAction serviceAction;
        try {
            serviceAction = ServiceAction.getInstancie();
        } catch (Exception ex) {
            throw new SecurityException("Não foi possível estabelecer conexão com o servidor");
        }
        ResultSet rs = null;
        boolean passwordInformado = false;
        try {
            obterServerUserPassKeystore();
            WSPasswordCallback pc;
            for (Callback callback : callbacks) {
                if (callback instanceof WSPasswordCallback) {
                    pc = (WSPasswordCallback) callback;
                    if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {
                        if (user.equals(pc.getIdentifier())) {
                            pc.setPassword(password);
                            passwordInformado = true;
                        } else {
                            rs = serviceAction.getConexao().prepareStatement("SELECT NM_USUARIO, DS_SENHA, IE_ATIVO FROM USUARIO "
                                    + "WHERE NM_USUARIO = '" + pc.getIdentifier() + "'").executeQuery();
                            if (rs.next()
                                    && rs.getString("NM_USUARIO").equals(pc.getIdentifier())
                                    && rs.getBoolean("IE_ATIVO") == true) {
                                pc.setPassword(rs.getString("DS_SENHA"));
                                passwordInformado = true;
                            }
                        }
                    } else if (pc.getUsage() == WSPasswordCallback.DECRYPT
                            || pc.getUsage() == WSPasswordCallback.SIGNATURE) {
                        if (user.equals(pc.getIdentifier())) {
                            pc.setPassword(password);
                            passwordInformado = true;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            throw new SecurityException("Não foi possível autenticar o usuário no servidor");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {}
            if (!passwordInformado) {
                throw new SecurityException("Usuário/Senha inválido");
            }
        }
    }

    private void obterServerUserPassKeystore() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File(System.getProperty("user.home") + "\\", "serverUserPassKeystore.key")));
        user = in.readLine();
        user = user.substring(user.indexOf(":") + 1, user.length());
        password = in.readLine();
        password = password.substring(password.indexOf(":") + 1, password.length());
        in.close();
    }
}