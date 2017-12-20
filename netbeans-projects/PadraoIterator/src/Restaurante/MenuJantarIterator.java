package Restaurante;

import java.util.Iterator;

public class MenuJantarIterator implements Iterator {

    private MenuItem[] itens;
    private int posicao = 0;

    public MenuJantarIterator(MenuItem[] itens) {
        this.itens = itens;
    }

    public boolean hasNext() {
        if (posicao >= itens.length || itens[posicao] == null) {
            return false;
        } else {
            return true;
        }
    }

    public Object next() {
        MenuItem menuItem = itens[posicao];
        posicao++;
        return menuItem;
    }

    public void remove() {
        if (posicao <= 0) {
            throw new IllegalStateException("Você não pode remover um item até que você tenha feito pelo menos um next ()");
        }
        if (itens[posicao - 1] != null) {
            for (int i = posicao - 1; i < itens.length - 1; i++) {
                itens[i] = itens[i + 1];
            }
            itens[itens.length - 1] = null;
        }
    }
}
