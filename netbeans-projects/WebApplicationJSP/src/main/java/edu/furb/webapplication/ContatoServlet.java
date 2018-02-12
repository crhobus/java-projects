package edu.furb.webapplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ContatoServlet", urlPatterns = {"/contato/*"})
public class ContatoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getRequestURI().contains("novo")) {
            // formulário para incluir um contato
            request.setAttribute("titulo", "Novo Contato");
            request.getRequestDispatcher("/formContato.jsp").include(request, response);
        } else if (request.getRequestURI().contains("editar")) {
            // formulário para incluir um contato
            request.setAttribute("titulo", "Editar Contato");
            Contato contato = new Contato("Zequinha", "999", "zequinha@inf");
            request.setAttribute("contato", contato);
            request.getRequestDispatcher("/formContato.jsp").include(request, response);
        } else {

            List<Contato> contatos = new ArrayList<Contato>();
            contatos.add(new Contato("Caio", "123", "caio@inf"));
            contatos.add(new Contato("João da Silva", "456", "joao@inf"));
            contatos.add(new Contato("Maria", "789", "maria@inf"));

            request.setAttribute("lista", contatos);
            request.getRequestDispatcher("/listaContato.jsp").include(request, response);
        }
    }
}
