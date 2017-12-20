package Restaurante;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuPanquecaCasa implements Menu {

    private List<MenuItem> listaMenuItem;

    public MenuPanquecaCasa() {
        listaMenuItem = new ArrayList<MenuItem>();
        addItem("Jantar de Panqueca", "Panquecas com ovos mexidos e torradas", true, 2.99);
        addItem("Pequeno almoço de panqueca", "Panquecas com ovos fritos e salsichas", false, 2.99);
        addItem("Panquecas de milho", "Panquecas feitas de milho fresco", true, 2.49);
        addItem("Panqueca da casa", "Panquecas feita de frango com salsichas", false, 4.12);
    }

    public void addItem(String nome, String descricao, boolean vegetariano, double preco) {
        MenuItem menuItem = new MenuItem(nome, descricao, vegetariano, preco);
        listaMenuItem.add(menuItem);
    }

    public Iterator criarIterator() {
        return new MenuPanquecaCasaIterator(listaMenuItem);
    }
}
