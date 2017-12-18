package controller;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "pagina")
public class PaginaBean {

    public String getOla() {
        return "Olá Caio. Tudo Bem????";
    }
}
