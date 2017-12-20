package Restaurante;

import java.util.Hashtable;
import java.util.Iterator;

public class MenuCafe implements Menu {

    private Hashtable<String, MenuItem> menuItens = new Hashtable<String, MenuItem>();

    public MenuCafe() {
        addItem("Café da Casa", "Café com leite, pão, apresuntato, mussarela", false, 2.78);
        addItem("Café da Casa com frutas", "Café com leite, uva, banana, maça", true, 3.00);
        addItem("Café da Casa com Misto Quente", "Café co leite, tomate, apresuntado, mussarela, maionese", true, 4.00);
    }

    public void addItem(String nome, String descricao, boolean vegetariano, double preco) {
        MenuItem menuItem = new MenuItem(nome, descricao, vegetariano, preco);
        menuItens.put(menuItem.getNome(), menuItem);
    }

    public Iterator criarIterator() {
        return menuItens.values().iterator();
    }
}
