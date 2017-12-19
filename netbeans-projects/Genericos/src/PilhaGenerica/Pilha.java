package PilhaGenerica;

public class Pilha<E> {

    private final int tamanho;
    private int topo;
    private E[] elemento;

    public Pilha() {
        this(10);
    }

    public Pilha(int s) {
        tamanho = s > 0 ? s : 10;
        topo = -1;
        elemento = (E[]) new Object[tamanho];
    }

    public void push(E valor) {
        if (topo == tamanho - 1) {
            throw new PilhaCheiaException(String.format("Pilha está cheia, e não pode inserir %s", valor));
        }
        elemento[++topo] = valor;
    }

    public E pop() {
        if (topo == -1) {
            throw new PilhaCheiaException("A pilha está vazia, e não pode ser retirado nenhum elemento");
        }
        return elemento[topo--];
    }
}
