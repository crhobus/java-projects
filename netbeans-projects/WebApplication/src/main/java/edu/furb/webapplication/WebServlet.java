package edu.furb.webapplication;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Caio
 */
public class WebServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("nome");
        System.out.println("URL:" + request.getRequestURL());
        System.out.println("Metodo:" + request.getMethod());
        System.out.println("Map:" + request.getParameterMap());
        System.out.println("Header:" + request.getHeader("TesteCaio"));

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WebServlet</title>");
            out.println("<script type='text/javascript' src='https://code.jquery.com/jquery-2.1.4.js'></script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WebServlet at " + request.getContextPath() + "</h1>");
            out.println("<h1>Olá " + nome + "</h1>");
            out.println("<h1>Utilizando o método: " + request.getMethod() + "</h1>");
            out.println("<h2>Chave customizada: " + request.getHeader("CustomKey") + "</h2>");
            out.println("<h2>User agent: " + request.getHeader("User-Agent") + "</h2>");
            out.println("</body>");
            out.println("</html>");
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
