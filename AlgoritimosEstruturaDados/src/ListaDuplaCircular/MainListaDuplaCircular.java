package ListaDuplaCircular;

public class MainListaDuplaCircular {

    public static void main(String[] args) {
        ListaDuplaCircular l1 = new ListaDuplaCircular();
        l1.insere(4);
        l1.insere(75);
        l1.insere(15);
        l1.insere(1);
        l1.insere(18);
        l1.insere(42);
        l1.insere(89);
        l1.insere(64);
        System.out.println("Verifica se a lista esta vazia: " + l1.vazia());
        if (l1.vazia() == false) {
            System.out.print("Conteúdo da lista l1 duplamente encadeada circular: ");
            l1.imprime();
            System.out.println();
            l1.insereFim(7);
            l1.insereFim(9);
            l1.insereFim(88);
            System.out.print("Conteúdo da lista l1 duplamente encadeada circular após ter adicionado no fim: ");
            l1.imprime();
            System.out.println();
            l1.retira(9);
            int n1 = 7;
            l1.retira(n1);
            System.out.print("Conteúdo da lista l1 duplamente encadeada circular após ter retirado o número " + n1 + ": ");
            l1.imprime();
            System.out.println();
            System.out.println("Busca um valor da lista: " + l1.busca(7));
            System.out.println("Busca pelo índice: " + l1.buscaIndice(8));
            l1.libera();
            System.out.print("Conteúdo da lista l1 duplamente encadeada circular apos usar o método libera: ");
            l1.imprime();
            System.out.println();
            l1.insere(2);
            l1.insere(7);
            l1.insere(8);
            l1.insere(19);
            l1.insere(4);
            l1.insere(6);
            l1.insere(5);
            l1.insere(80);
            l1.insere(21);
            if (l1.vazia() == false) {
                System.out.print("Novo conteúdo da lista l1 Lista Duplamente encadeada circular: ");
                l1.imprime();
                System.out.println();
                ListaDuplaCircular l2 = new ListaDuplaCircular();
                l2.insere(2);
                l2.insere(7);
                l2.insere(8);
                l2.insere(19);
                l2.insere(4);
                l2.insere(6);
                l2.insere(5);
                l2.insere(80);
                l2.insere(21);
                if (l2.vazia() == false) {
                    System.out.print("Conteúdo da nova Lista l2 Duplamente encadeada circular: ");
                    l2.imprime();
                    System.out.println();
                    System.out.println("Verifica se Lista l1 e l2 são iguais: " + l2.igual(l1));
                    System.out.println();
                    ListaDuplaCircular l3 = new ListaDuplaCircular();
                    l3.insere(6);
                    l3.insere(5);
                    l3.insere(80);
                    l3.insere(21);
                    l3.insere(26);
                    if (l3.vazia() == false) {
                        System.out.print("Conteúdo da Lista l3 Duplamente encadeada circular: ");
                        l3.imprime();
                    }
                    System.out.println();
                    ListaDuplaCircular l4 = l2.merge(l3);
                    System.out.print("Conteúdo do método merge da Lista l2 com l3 criando uma nova lista l4 Duplamente encadeada circular: ");
                    l4.imprime();
                    System.out.println();
                    ListaDuplaCircular l5 = new ListaDuplaCircular();
                    l5.insere(3);
                    l5.insere(10);
                    l5.insere(30);
                    l5.insere(25);
                    l5.insere(28);
                    l5.insere(61);
                    l5.insere(533);
                    l5.insere(801);
                    l5.insere(23);
                    l5.insere(2);
                    if (l5.vazia() == false) {
                        System.out.print("Conteúdo da Lista l5: ");
                        l5.imprime();
                        System.out.println();
                        int n2 = 533;
                        ListaDuplaCircular l6 = l5.separa(n2);
                        System.out.print("Conteúdo da lista l5 após usar o método separa até o nó com o número " + n2 + ": ");
                        l5.imprime();
                        System.out.println();
                        System.out.print("Conteúdo da nova lista l6 após o número " + n2 + " usado no metodo separa lista l6: ");
                        l6.imprime();
                        System.out.println();
                    }
                }
            }
        }
    }
}
