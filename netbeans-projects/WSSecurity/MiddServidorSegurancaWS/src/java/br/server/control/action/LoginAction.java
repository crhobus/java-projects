package br.server.control.action;

/*
 * Document   : LoginAction.java
 * Created on : 15/07/2013, 20:12:42
 * Author     : Caio
 */
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginAction implements Serializable {

    public String doLogin() throws ServletException, IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extenalContext = context.getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) extenalContext.getRequest()).getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward((ServletRequest) extenalContext.getRequest(), (ServletResponse) extenalContext.getResponse());
        context.responseComplete();
        return null;
    }

    public void loginError(ComponentSystemEvent evt) throws AbortProcessingException {
        Map<String, Object> atributos = evt.getComponent().getAttributes();
        String error = (String) atributos.get("error");
        if (error != null
                && !"".equals(error)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login", "Usuário/Senha inválido"));
        }
    }
}