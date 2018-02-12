package edu.furb.webapplication;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

@WebServlet(name = "ContatoServlet", urlPatterns = {"/contatoservlet"})
public class ContatoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pagSendRedirect;

        String smtpServer = request.getParameter("smtpServer");
        String to = request.getParameter("to");
        String nameTo = request.getParameter("nameTo");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String from = request.getParameter("from");
        String namefrom = request.getParameter("nameFrom");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        SimpleEmail mail = new SimpleEmail();
        try {
            mail.setHostName(smtpServer);
            mail.setAuthenticator(new DefaultAuthenticator(username, password));
            //mail.setStartTLSEnabled(true);
            mail.setSmtpPort(465);
            mail.setSSLOnConnect(true);

            mail.setFrom(from, namefrom);
            mail.addTo(to, nameTo);
            mail.addReplyTo(from, nameTo);
            mail.setSubject(subject);
            mail.setMsg(message);
            mail.send();
            pagSendRedirect = "contato.html";
        } catch (EmailException ex) {
            pagSendRedirect = "error.html";
            System.err.println("Problemas no envio de email!!!");
            Logger.getLogger(DBServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect(pagSendRedirect);
    }
}
