package edu.furb.web.view;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Caio
 */
@ManagedBean
public class SiteBean {

    private String nome;
    private final String botaoLabel = "Executar";
    private static final List<String> listaNomes = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBotaoLabel() {
        return botaoLabel;
    }

    public void add() {
        listaNomes.add(nome);
    }

    public List<String> getListaNomes() {
        return listaNomes;
    }
}
