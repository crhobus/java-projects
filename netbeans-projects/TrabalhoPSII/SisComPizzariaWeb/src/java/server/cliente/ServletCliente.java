package server.cliente;

import control.Servidor;
import control.funcoes.Dados;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entidades.Cliente;
import server.Autenticar;
import server.Index;

public class ServletCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Autenticar autenticar = new Autenticar();
        if (autenticar.executaAutenticacao(request, request.getParameter("bt_ok_cliente"), request.getParameter("bt_sair_cliente"))) {
            RequestDispatcher rd = request.getRequestDispatcher("cliente.jsp");
            rd.forward(request, response);
            return;
        }
        String mensagem = "";
        Servidor servidor = null;
        Dados dados = new Dados();
        dados.addInfo("NM_CLIENTE", request.getParameter("nm_cliente"));
        dados.addInfo("NM_USUARIO", request.getParameter("nm_usuario"));
        dados.addInfo("DS_SENHA", request.getParameter("ds_senha"));
        dados.addInfo("DS_CONFIRMA_SENHA", request.getParameter("ds_confirma_senha"));
        dados.addInfo("DS_ENDERECO", request.getParameter("ds_endereco"));
        dados.addInfo("DS_BAIRRO", request.getParameter("ds_bairro"));
        dados.addInfo("NR_CEP", request.getParameter("nr_cep"));
        dados.addInfo("NR_RESIDENCIA", request.getParameter("nr_residencia"));
        dados.addInfo("NM_CIDADE", request.getParameter("nm_cidade"));
        dados.addInfo("DS_REFERENCIA", request.getParameter("ds_referencia"));
        dados.addInfo("NR_TELEFONE", request.getParameter("nr_telefone"));
        dados.addInfo("NR_CELULAR", request.getParameter("nr_celular"));
        dados.addInfo("DS_EMAIL", request.getParameter("ds_email"));
        dados.addInfo("DS_OBSERVACAO", request.getParameter("ds_observacao"));
        try {
            servidor = Index.getInstanceServidor();
        } catch (ExceptionError ex) {
            mensagem = ex.getMessage();
        }
        if ("".equals(mensagem)) {
            try {
                Cliente c = null;
                if (Index.getCdCliente() > 0) {
                    c = servidor.getClienteAction().getCliente(Index.getCdCliente());
                }
                Index.setClienteLogado(servidor.getClienteAction().salvar(dados, c));
            } catch (ExceptionInfo ex) {
                mensagem = ex.getMessage();
            } catch (ExceptionError ex) {
                mensagem = ex.getMessage();
            }
        }
        if (!"".equals(mensagem)) {
            request.setAttribute("msg", mensagem);
            request.setAttribute("dados", dados.getDados());
        }
        RequestDispatcher rd = request.getRequestDispatcher("cliente.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
