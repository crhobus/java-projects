package CafeCha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChaComGancho extends BebidasComGancho {

    @Override
    public void mistura() {
        System.out.println("Colocar o chá em influsão na água fervente");
    }

    @Override
    public void addCondimentos() {
        System.out.println("Adicionar limão");
    }

    @Override
    public boolean clienteQuerCondimentos() {
        String resposta = getUsuarioEntrada();
        if (resposta.toLowerCase().startsWith("s")) {
            return true;
        } else {
            return false;
        }
    }

    private String getUsuarioEntrada() {
        String resposta = null;
        System.out.println("Gostaria adicionar limão (s/n)? ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            resposta = in.readLine();
        } catch (IOException ex) {
            System.err.println("Erro na leitura da resposta");
        }
        if (resposta == null) {
            return "não";
        }
        return resposta;
    }
}
