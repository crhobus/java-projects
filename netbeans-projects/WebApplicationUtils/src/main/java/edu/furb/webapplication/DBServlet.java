package edu.furb.webapplication;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DBServlet", urlPatterns = {"/db/*"})
public class DBServlet extends HttpServlet {

    private Connection conn;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "sysuser", "keyuser");
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DBServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PreparedStatement ps = null;
        try {
            String caminho = request.getRequestURI().replace("/WebApplicationUtils/db/", "");
            String[] parametros = caminho.split("/");
            if (parametros.length > 1) {
                ps = conn.prepareStatement("select * from " + parametros[0] + " where nr_sequencia = ?");
                ps.setString(1, parametros[1]);
            } else {
                ps = conn.prepareStatement("select * from " + parametros[0]);
            }

            PrintWriter out = response.getWriter();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if (!rs.isFirst()) {
                    out.print(",");
                }
                out.print("{");
                int colunas = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= colunas; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    out.print(columnName);
                    out.print(":");
                    out.print(rs.getString(columnName));
                    if (i < colunas) {
                        out.print(",");
                    }
                }
                out.print("}");
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PreparedStatement ps = null;
        try {
            String caminho = request.getRequestURI().replace("/WebApplicationUtils/db/", "");
            String[] parametros = caminho.split("/");
            if (parametros.length > 1) {
                ps = conn.prepareStatement("update " + parametros[0] + " set nome = ?, telefone = ?, email = ? where nr_sequencia = ?");
                ps.setString(1, request.getParameter("nome"));
                ps.setString(2, request.getParameter("telefone"));
                ps.setString(3, request.getParameter("email"));
                ps.setString(4, parametros[1]);
            } else {
                ps = conn.prepareStatement("insert into " + parametros[0] + " (nr_sequencia, nome, telefone, email) values (contato_seq.nextval, ?, ?, ?)");
                ps.setString(1, request.getParameter("nome"));
                ps.setString(2, request.getParameter("telefone"));
                ps.setString(3, request.getParameter("email"));
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("/WebApplicationUtils/cadastro.html");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PreparedStatement ps = null;
        try {
            String caminho = request.getRequestURI().replace("/WebApplicationUtils/db/", "");
            String[] parametros = caminho.split("/");
            if (parametros.length > 1) {
                ps = conn.prepareStatement("delete from " + parametros[0] + " where nr_sequencia = ?");
                ps.setString(1, parametros[1]);
                ps.executeUpdate();
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DBServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("/WebApplicationUtils/cadastro.html");
        }
    }

    @Override
    public void destroy() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.destroy();
    }
}
