package ProdutorConsumidor;

import java.util.ArrayList;
import java.util.List;

public class Fila {

    private List<String> lista;

    public Fila() {
        lista = new ArrayList<String>();
    }

    public synchronized void insere(String str) {
        while (getQuantidade() > 19) {
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        lista.add(str);
        System.out.println("Insere: " + getQuantidade());
        notifyAll();
    }

    public synchronized void retira() {
        while (isVazia()) {
            try {
                wait();
            } catch (InterruptedException ex) {}
        }
        lista.remove(lista.size() - 1);
        System.out.println("Retirou: " + getQuantidade());
        if (getQuantidade() < 10) {
            notifyAll();
        }
    }

    public boolean isVazia() {
        return lista.isEmpty();
    }

    public int getQuantidade() {
        return lista.size();
    }
}
