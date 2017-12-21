/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.furb.model;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Caio
 */
public class MinhaServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String param = request.getParameter("nome");
        String nome = ((param == null) || (param.length() == 0)) ? "estranho" : param;
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unitProjetoWeb");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = new Usuario();
            usuario.setNome("Caio");
            usuario.setSenha("123");
            em.persist(usuario);
            em.getTransaction().commit();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MinhaServlet- " + nome + "</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Criado o usuário " + usuario.getNome() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            out.close();
            em.close();
            emf.close();
        }
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
