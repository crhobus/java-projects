package br.client.middleware.callback;
/*
 * Document   : ClientPasswordCallback.java
 * Created on : 22/09/2013, 10:40:21
 * Author     : Caio
 */

import br.client.middleware.config.Config;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

public class ClientPasswordCallback implements CallbackHandler {

    private String userClient;
    private String passwordClient;
    private String userServer;
    private String passwordServer;

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        try {
            obterClientServerUserPassKeystore();
        } catch (Exception ex) {
            throw new SecurityException("Não foi possível realizar a autenticação do usuário");
        }
        boolean passwordInformado = false;
        WSPasswordCallback pc;
        for (Callback callback : callbacks) {
            if (callback instanceof WSPasswordCallback) {
                pc = (WSPasswordCallback) callback;
                if (pc.getUsage() == WSPasswordCallback.USERNAME_TOKEN) {
                    if (userServer.equals(pc.getIdentifier())) {
                        pc.setPassword(passwordServer);
                        passwordInformado = true;
                    } else {
                        Config config = Config.getInstancie();
                        pc.setIdentifier(config.getNmUsuario());
                        pc.setPassword(config.getDsSenha());
                        passwordInformado = true;
                    }
                } else if (pc.getUsage() == WSPasswordCallback.DECRYPT
                        || pc.getUsage() == WSPasswordCallback.SIGNATURE) {
                    if (userClient.equals(pc.getIdentifier())) {
                        pc.setPassword(passwordClient);
                        passwordInformado = true;
                    }
                }
            }
        }
        if (!passwordInformado) {
            throw new SecurityException("Usuário/Senha inválido");
        }
    }

    private void obterClientServerUserPassKeystore() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(new File("clientUserPassKeystore.key")));
        userClient = in.readLine();
        userClient = userClient.substring(userClient.indexOf(":") + 1, userClient.length());
        passwordClient = in.readLine();
        passwordClient = passwordClient.substring(passwordClient.indexOf(":") + 1, passwordClient.length());
        in.close();

        in = new BufferedReader(new FileReader(new File("serverUserPassKeystore.key")));
        userServer = in.readLine();
        userServer = userServer.substring(userServer.indexOf(":") + 1, userServer.length());
        passwordServer = in.readLine();
        passwordServer = passwordServer.substring(passwordServer.indexOf(":") + 1, passwordServer.length());
        in.close();
    }
}