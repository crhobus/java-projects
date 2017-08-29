package ListaSimples;

public class MainLista {

    public static void main(String[] args) {
        Lista aux = new Lista();
        aux.insere(45);
        aux.insere(12);
        aux.insere(50);
        aux.insere(6);
        aux.insere(77);
        aux.insere(458);
        aux.insere(4);
        aux.insere(300);
        aux.insere(120);
        aux.insere(67);
        aux.insereIndice(6, 0);
        System.out.println("Lista esta vazia? " + aux.vazia());
        if (aux.vazia() == false) {
            System.out.print("Conteúdo da Lista: ");
            aux.imprime();
            System.out.println();
            System.out.print("Conteúdo da Lista invertida: ");
            aux.imprimeInv();
            System.out.println();
            System.out.println("Valor Encontrado: " + aux.busca(300));
            System.out.println("Comprimento da lista: " + aux.comprimento());
            System.out.println("Ultimo nó da lista: " + aux.ultimo());
            int n1 = 6;
            aux.retira(n1);
            System.out.println("O Número " + n1 + " foi retirado da lista");
            System.out.print("Conteúdo da Lista após retida: ");
            aux.imprime();
            System.out.println();
            aux.libera();
            System.out.print("Conteúdo da Lista após Liberar: ");
            aux.imprime();
            System.out.println();
            aux.insere(8);
            aux.insere(7);
            aux.insere(6);
            aux.insere(5);
            aux.insere(4);
            aux.insere(3);
            aux.insere(2);
            aux.insere(1);
            System.out.println("Lista esta vazia? " + aux.vazia());
            if (aux.vazia() == false) {
                System.out.print("Novo Conteúdo da Lista: ");
                aux.imprime();
                System.out.println();
                int n2 = 3;
                aux.insereOrdenado(n2);
                System.out.print("O número " + n2 + " foi inserido na lista em ordem crescente: ");
                aux.imprime();
                System.out.println();
                System.out.println("Listas Iguais: " + aux.igual(aux));
                Lista aux2 = new Lista();
                aux2.insere(45);
                aux2.insere(12);
                aux2.insere(50);
                aux2.insere(6);
                aux2.insere(77);
                aux2.insere(458);
                aux2.insere(4);
                aux2.insere(300);
                System.out.print("Conteúdo de uma nova Lista aux 2: ");
                aux2.imprime();
                System.out.println();
                Lista aux3 = new Lista();
                aux3.insere(5);
                aux3.insere(1);
                aux3.insere(5);
                aux3.insere(96);
                aux3.insere(7);
                aux3.insere(8);
                aux3.insere(47);
                aux3.insere(300);
                System.out.print("Conteúdo de uma nova Lista aux 3: ");
                aux3.imprime();
                System.out.println();
                System.out.println("Vericando se aux2 é igual a aux3: ");
                if (aux2.igualRecursivo(aux3)) {
                    System.out.println("Listas iguais");
                } else {
                    System.out.println("Listas diferentes");
                }
                if (aux2.igual(aux3)) {
                    System.out.println("Listas iguais");
                } else {
                    System.out.println("Listas diferentes");
                }
                System.out.print("Imprime Recursivo: ");
                aux3.imprimeRecursivo();
                System.out.println();
                int n3 = 50;
                aux3.retiraRecursivo(n3);
                System.out.println("O número " + n3 + " foi retirado usando o retira recursivo: ");
                System.out.print("Imprime Recursivo: ");
                aux3.imprimeRecursivo();
                System.out.println();
                aux2.moveNo(aux3);
                System.out.println("Conteúdo aux2");
                aux2.imprime();
                System.out.println("Conteúdo aux3");
                aux3.imprime();
                System.out.println();
            }
        }
    }
}
