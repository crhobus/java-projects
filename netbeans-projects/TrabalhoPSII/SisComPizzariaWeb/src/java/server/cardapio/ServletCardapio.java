package server.cardapio;

import control.Servidor;
import control.funcoes.ExceptionError;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entidades.Sabor;
import server.Autenticar;
import server.Index;

public class ServletCardapio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Autenticar autenticar = new Autenticar();
        if (autenticar.executaAutenticacao(request, request.getParameter("bt_ok_cliente"), request.getParameter("bt_sair_cliente"))) {
            RequestDispatcher rd = request.getRequestDispatcher("cliente.jsp");
            rd.forward(request, response);
            return;
        }
        
        List<Sabor> sabores = null;
        String mensagem = "";
        Servidor servidor = null;

        try {
            servidor = Index.getInstanceServidor();
        } catch (ExceptionError ex) {
            System.out.println("Erro ao conectar ao banco!");
            mensagem = ex.getMessage();
        }

        try {
            sabores = servidor.getSaborAction().getSabores();
        } catch (ExceptionError ex) {
            System.out.println("Erro ao buscar dados no database!");
        }

        request.setAttribute("dados", "  ");
        request.setAttribute("msg", mensagem);
        request.setAttribute("sabores", sabores);

        RequestDispatcher rd = request.getRequestDispatcher("cardapio.jsp");
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
