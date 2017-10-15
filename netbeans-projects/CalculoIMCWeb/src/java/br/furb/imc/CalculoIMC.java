package br.furb.imc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculoIMC extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=ISO-8859-1");
        double peso = Double.parseDouble(request.getParameter("peso"));
        double altura = Double.parseDouble(request.getParameter("altura"));
        double imc = peso / (altura * altura);
        String txt = "";
        if (imc <= 18.49) {
            txt = request.getParameter("nome") + " o seu IMC está abaixo do normal: " + imc;
        } else if (imc >= 18.5 && imc <= 24.99) {
            txt = request.getParameter("nome") + " o seu IMC está normal: " + imc;
        } else if (imc >= 25) {
            txt = request.getParameter("nome") + " o seu IMC está acima do normal: " + imc;
        }

        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Exercício JavaScript - Cálculo Índice de Massa Corpórea</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cálculo Índice de Massa Corpórea</h1>");
            out.println("<h2>" + txt + "</h2>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
