package edu.furb.webapplication;

import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Caio
 */
@WebServlet(name = "ImagemServlet", urlPatterns = {"/imagemservlet/*"})
public class ImagemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpg");
        String fileName = request.getRequestURI().replace("/WebApplication2/imagemservlet/", "");

        FileInputStream is = new FileInputStream("C:/Users/Caio/Documents/Arquivos/img/" + fileName);
        byte[] bytes = new byte[1024];
        while (is.read(bytes) > 0) {
            response.getOutputStream().write(bytes);
        }
        response.getOutputStream().flush();
        is.close();
    }
}
