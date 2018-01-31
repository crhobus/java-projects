package edu.furb.cadasproduto;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProdutoServlet", urlPatterns = {"/produtoservlet/*"})
public class ProdutoServlet extends HttpServlet {

    private DAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            dao = new DAO();
            dao.conectar();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getRequestURI().contains("excluir")) {
                dao.excluir(Long.parseLong(request.getParameter("id")));
            } else if (request.getRequestURI().contains("atualizar")) {
                Produto produto = dao.getProduto(Long.parseLong(request.getParameter("id")));
                request.setAttribute("produto", produto);
            }
            request.setAttribute("contextPath", request.getContextPath());
            request.setAttribute("produtos", dao.listar());
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/index.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = "".equals(request.getParameter("id")) ? 0l : Long.parseLong(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String unidadeMedida = request.getParameter("unidade_medida");
            BigDecimal preco = new BigDecimal(request.getParameter("preco"));

            Produto produto = new Produto(id, nome.toUpperCase(), unidadeMedida.toUpperCase(), preco);
            if (id > 0) {
                dao.alterar(produto);
            } else {
                dao.inserir(produto);
            }

        } catch (Exception ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("produtoservlet");
    }

    @Override
    public void destroy() {
        try {
            dao.desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.destroy();
    }
}
