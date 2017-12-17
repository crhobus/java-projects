package Fila;

import java.util.PriorityQueue;
import java.util.Queue;

public class Fila {

    public Fila() {
        Queue<Double> fila = new PriorityQueue<Double>();
        fila.offer(3.2);//offer(Double e) insere um elemento do tipo double na posição apropiada com base na ordem de prioridade
        fila.offer(9.8);
        fila.offer(5.4);
        fila.offer(55.4);
        fila.offer(4.0);
        fila.offer(4.0);
        fila.offer(5.3);
        fila.offer(8.3);
        imprimirFila(fila);
        fila.offer(6.8);
        fila.offer(8.9);
        fila.offer(1.5);
        fila.offer(4.7);
        imprimirFila(fila);
        fila.remove();//remove o elemento de maior prioridade da fila de prioridade
        imprimirFila(fila);
        fila.remove();
        imprimirFila(fila);
        fila.remove(4.7);//Se houver remove o elemento da fila
        imprimirFila(fila);
        fila.poll();
        imprimirFila(fila);//remove o elemento de maior prioridade da fila de
        fila.clear();//limpa a fila
        imprimirFila(fila);
        fila.offer(7.4);
        fila.offer(3.4);
        fila.offer(2.3);
        imprimirFila(fila);
        ImprimeRemoveTodosFila(fila);
        imprimirFila(fila);
    }

    private void imprimirFila(Queue<Double> fila) {
        if (fila.isEmpty()) {//verifica se pilha está vazia
            System.out.print("Fila está vazia\n\n");
        } else {
            System.out.print("Conteúdo da Fila: ");
            for (Number number : fila) {
                System.out.printf("%s ", number);
            }
            System.out.println();
        }
    }

    private void ImprimeRemoveTodosFila(Queue<Double> fila) {
        System.out.print("Polling from queue: ");
        while (fila.size() > 0) {//size() obtem o numero de elementos da fila
            System.out.printf("%.1f ", fila.peek());//peek() obtem uma referencia ao elemento de mais alta prioridade da fila de prioridade (sem remover o elemento)
            fila.poll();//poll() remove o elemento de mais alta prioridade da fila de prioridade
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Fila();
    }
}
