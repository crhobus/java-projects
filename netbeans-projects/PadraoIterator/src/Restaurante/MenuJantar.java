package Restaurante;

import java.util.Iterator;

public class MenuJantar implements Menu {

    private final int itensMax = 6;
    private int numeroItens;
    private MenuItem[] menuItems;

    public MenuJantar() {
        menuItems = new MenuItem[itensMax];
        addItem("Vegetariano BLT", "Alface e tomate", true, 2.99);
        addItem("BLT", "Bacon com alface e tomate", false, 3.87);
        addItem("Sopa do dia", "Sopa do dia, com um lado de salada de batata", false, 4.50);
        addItem("Cachorro-quente", "Um cachorro-quente, com batata palha, cebola, coberto com queijo", false, 5.50);
    }

    public void addItem(String nome, String descricao, boolean vegetariano, double preco) {
        MenuItem menuItem = new MenuItem(nome, descricao, vegetariano, preco);
        if (numeroItens >= itensMax) {
            System.err.println("Menu está cheio! Não é possível adicionar itens ao menu");
        } else {
            menuItems[numeroItens] = menuItem;
            numeroItens++;
        }
    }

    public Iterator criarIterator() {
        return new MenuJantarIterator(menuItems);
    }
}
