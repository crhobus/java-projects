package br.server.model.util;

/*
 * Document   : ConexaoUtil.java
 * Created on : 25/05/2013, 08:58:32
 * Author     : Caio
 */
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

public class ConexaoUtil {

    public EntityManager getEntityManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
        return manager;
    }
}