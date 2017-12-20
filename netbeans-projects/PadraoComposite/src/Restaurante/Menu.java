package Restaurante;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Menu extends MenuComponente {

    private List<MenuComponente> menuComponentes = new ArrayList<MenuComponente>();
    private String nome, descricao;

    public Menu(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Override
    public void add(MenuComponente menuComponente) {
        menuComponentes.add(menuComponente);
    }

    @Override
    public void remove(MenuComponente menuComponente) {
        menuComponente.remove(menuComponente);
    }

    @Override
    public MenuComponente getFilho(int i) {
        return (MenuComponente) menuComponentes.get(i);
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void imprimir() {
        System.out.println("\n" + getNome() + ", " + getDescricao());
        Iterator iterator = menuComponentes.iterator();
        while (iterator.hasNext()) {
            MenuComponente menuComponente = (MenuComponente) iterator.next();
            menuComponente.imprimir();
        }
    }
}
