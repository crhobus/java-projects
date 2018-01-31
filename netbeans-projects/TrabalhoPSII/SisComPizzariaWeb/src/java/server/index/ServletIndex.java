package server.index;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import server.Autenticar;

public class ServletIndex extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Autenticar autenticar = new Autenticar();
        if (autenticar.executaAutenticacao(request, getNomeBtOK(request), getNomeBtSair(request))) {
            String pagina = getPaginaRequisitaSair(request);
            if (pagina == null) {
                pagina = getPaginaRequisitaLogar(request);
            }
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
            return;
        }
        //fazer o que falta aqui
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
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

    private String getNomeBtSair(HttpServletRequest request) {
        String acao = request.getParameter("bt_sair_index");
        if (acao != null
                && "Sair".equalsIgnoreCase(acao)) {
            return request.getParameter("bt_sair_index");
        }
        acao = request.getParameter("bt_sair_aCasa");
        if (acao != null
                && "Sair".equalsIgnoreCase(acao)) {
            return request.getParameter("bt_sair_aCasa");
        }
        acao = request.getParameter("bt_sair_horarios");
        if (acao != null
                && "Sair".equalsIgnoreCase(acao)) {
            return request.getParameter("bt_sair_horarios");
        }
        acao = request.getParameter("bt_sair_promocoes");
        if (acao != null
                && "Sair".equalsIgnoreCase(acao)) {
            return request.getParameter("bt_sair_promocoes");
        }
        acao = request.getParameter("bt_sair_contato");
        if (acao != null
                && "Sair".equalsIgnoreCase(acao)) {
            return request.getParameter("bt_sair_contato");
        }
        return null;
    }

    private String getPaginaRequisitaSair(HttpServletRequest request) {
        String acao = request.getParameter("bt_sair_index");
        if (acao != null
                && "Sair".equalsIgnoreCase(acao)) {
            return "index.jsp";
        }
        acao = request.getParameter("bt_sair_aCasa");
        if (acao != null
                && "Sair".equalsIgnoreCase(acao)) {
            return "aCasa.jsp";
        }
        acao = request.getParameter("bt_sair_horarios");
        if (acao != null
                && "Sair".equalsIgnoreCase(acao)) {
            return "horarios.jsp";
        }
        acao = request.getParameter("bt_sair_promocoes");
        if (acao != null
                && "Sair".equalsIgnoreCase(acao)) {
            return "promocoes.jsp";
        }
        acao = request.getParameter("bt_sair_contato");
        if (acao != null
                && "Sair".equalsIgnoreCase(acao)) {
            return "contato.jsp";
        }
        return null;
    }

    private String getNomeBtOK(HttpServletRequest request) {
        String acao = request.getParameter("bt_ok_index");
        if (acao != null
                && "OK".equalsIgnoreCase(acao)) {
            return request.getParameter("bt_ok_index");
        }
        acao = request.getParameter("bt_ok_aCasa");
        if (acao != null
                && "OK".equalsIgnoreCase(acao)) {
            return request.getParameter("bt_ok_aCasa");
        }
        acao = request.getParameter("bt_ok_horarios");
        if (acao != null
                && "OK".equalsIgnoreCase(acao)) {
            return request.getParameter("bt_ok_horarios");
        }
        acao = request.getParameter("bt_ok_promocoes");
        if (acao != null
                && "OK".equalsIgnoreCase(acao)) {
            return request.getParameter("bt_ok_promocoes");
        }
        acao = request.getParameter("bt_ok_contato");
        if (acao != null
                && "OK".equalsIgnoreCase(acao)) {
            return request.getParameter("bt_ok_contato");
        }
        return null;
    }

    private String getPaginaRequisitaLogar(HttpServletRequest request) {
        String acao = request.getParameter("bt_ok_index");
        if (acao != null
                && "OK".equalsIgnoreCase(acao)) {
            return "index.jsp";
        }
        acao = request.getParameter("bt_ok_aCasa");
        if (acao != null
                && "OK".equalsIgnoreCase(acao)) {
            return "aCasa.jsp";
        }
        acao = request.getParameter("bt_ok_horarios");
        if (acao != null
                && "OK".equalsIgnoreCase(acao)) {
            return "horarios.jsp";
        }
        acao = request.getParameter("bt_ok_promocoes");
        if (acao != null
                && "OK".equalsIgnoreCase(acao)) {
            return "promocoes.jsp";
        }
        acao = request.getParameter("bt_ok_contato");
        if (acao != null
                && "OK".equalsIgnoreCase(acao)) {
            return "contato.jsp";
        }
        return null;
    }
}
