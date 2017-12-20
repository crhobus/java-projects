package Restaurante;

import java.util.Iterator;
import java.util.List;

public class MenuPanquecaCasaIterator implements Iterator {

    private List<MenuItem> itens;
    private int posicao = 0;

    public MenuPanquecaCasaIterator(List<MenuItem> itens) {
        this.itens = itens;
    }

    public boolean hasNext() {
        if (posicao >= itens.size() || itens == null) {
            return false;
        } else {
            return true;
        }
    }

    public Object next() {
        MenuItem menuItem = itens.get(posicao);
        posicao++;
        return menuItem;
    }

    public void remove() {
        if (posicao <= 0) {
            throw new IllegalStateException("Você não pode remover um item até que você tenha feito pelo menos um next ()");
        }
        if (itens.get(posicao - 1) != null) {
            for (int i = posicao - 1; i < itens.size() - 1; i++) {
                itens.remove(i);
            }
        }
    }
}
