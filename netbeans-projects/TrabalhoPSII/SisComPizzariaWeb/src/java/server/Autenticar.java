package server;

import control.Servidor;
import control.funcoes.Dados;
import control.funcoes.ExceptionError;
import javax.servlet.http.HttpServletRequest;

public class Autenticar {

    public boolean executaAutenticacao(HttpServletRequest request, String ok, String sair) {
        String mensagem = "";
        Servidor servidor = null;
        String acao = sair;
        if (acao != null
                && "Sair".equalsIgnoreCase(acao)) {
            Index.setClienteLogado(null);
            return true;
        }
        acao = ok;
        if (acao != null
                && "OK".equalsIgnoreCase(acao)) {
            Dados dados = new Dados();
            dados.addInfo("NM_USUARIO", request.getParameter("usuario"));
            dados.addInfo("DS_SENHA", request.getParameter("senha"));
            try {
                servidor = Index.getInstanceServidor();
            } catch (ExceptionError ex) {
                mensagem = ex.getMessage();
            }
            if ("".equals(mensagem)) {
                try {
                    servidor.getUsuarioAction().autenticacao(dados);
                    Index.setClienteLogado(servidor.getClienteAction().getCliente(dados.getInfo("NM_USUARIO")));
                } catch (ExceptionError ex) {
                    mensagem = ex.getMessage();
                }
            }
            if (!"".equals(mensagem)) {
                request.setAttribute("mensagem", mensagem);
            }
            return true;
        }
        return false;
    }
}
