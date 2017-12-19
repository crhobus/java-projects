package view.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

//Este é o Autenticador 
public class Autenticador extends Authenticator {

    private String user, password;

    public Autenticador(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }
}
