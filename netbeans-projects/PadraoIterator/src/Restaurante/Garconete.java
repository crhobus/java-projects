package Restaurante;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Garconete {

    private List<Menu> menus;

    public Garconete() {
        this.menus = new ArrayList<Menu>();
    }

    public void imprimirMenu() {
        Iterator menuIterator = menus.iterator();
        while (menuIterator.hasNext()) {
            Menu menu = (Menu) menuIterator.next();
            imprimirMenuAux(menu.criarIterator());
        }
    }

    private void imprimirMenuAux(Iterator iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.println(menuItem.getNome() + ", " + menuItem.getPreco() + ", " + menuItem.getDescricao() + "\n");
        }
    }

    public void setMenu(Menu menu) {
        menus.add(menu);
    }
}
