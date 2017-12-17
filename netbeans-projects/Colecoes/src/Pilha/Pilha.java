package Pilha;

import java.util.EmptyStackException;
import java.util.Stack;

public class Pilha {

    public Pilha() {
        Stack<Number> pilha = new Stack<Number>();
        long longNumero = 12L;//Valor com sufixo L representa long
        Integer intNumero = 34567;//Valor sem sufixo representa um int
        Float floatNumero = 1.0F;//Valor com sufixo F representa float
        Double doubleNumero = 1234.5678;//Valor sem sufixo de ponto flutuante representa um double
        pilha.push(longNumero);//push(Number num)adiciona um objeto no topo da pilha
        imprimirPilha(pilha);
        pilha.push(intNumero);
        imprimirPilha(pilha);
        pilha.push(floatNumero);
        imprimirPilha(pilha);
        pilha.push(doubleNumero);
        imprimirPilha(pilha);
        try {
            Number removerObjeto = null;
            removerObjeto = pilha.pop();//pop()remove um elemento do topo da pilha
            System.out.printf("%s removido\n", removerObjeto);
            imprimirPilha(pilha);
        } catch (EmptyStackException ex) {
            ex.printStackTrace();
        }
    }

    private void imprimirPilha(Stack<Number> pilha) {
        if (pilha.isEmpty()) {//verifica se pilha está vazia
            System.out.print("Pilha está vazia\n\n");
        } else {
            System.out.print("Conteúdo da pilha: ");
            for (Number number : pilha) {
                System.out.printf("%s ", number);
            }
            System.out.print("(Topo) \n\n");
        }
    }

    public static void main(String[] args) {
        new Pilha();
    }
}
