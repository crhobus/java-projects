package ListaDupla;

public class MainListaDupla {

    public static void main(String[] args) {
        ListaDupla l1 = new ListaDupla();
        l1.insere(40);
        l1.insere(400);
        l1.insere(12);
        l1.insere(76);
        l1.insere(34);
        l1.insere(16);
        l1.insere(55);
        l1.insere(8);
        l1.insere(2);
        l1.insere(5);
        l1.insere(6);
        l1.insere(18);
        l1.insere(4);
        l1.insere(90);
        System.out.println("Verifica se lista esta vazia: " + l1.vazia());
        if (l1.vazia() == false) {
            System.out.print("Conteúdo da Lista l1 Duplamente encadeada: ");
            l1.imprime();
            System.out.println();
            System.out.println("Busca um valor da lista: " + l1.busca(4));
            System.out.println("Busca pelo indice: " + l1.buscaIndice(12));
            l1.retira(90);
            System.out.print("Conteúdo da Lista l1 Duplamente encadeada após a retirada: ");
            l1.imprime();
            System.out.println();
            l1.libera();
            System.out.print("Conteúdo da Lista l1 Duplamente encadeada após liberar: ");
            l1.imprime();
            System.out.println();
            System.out.println("Verifica lista vazia: " + l1.vazia());
            if (l1.vazia() == true) {
                l1.insere(2);
                l1.insere(7);
                l1.insere(8);
                l1.insere(19);
                l1.insere(4);
                l1.insere(6);
                l1.insere(5);
                l1.insere(80);
                System.out.print("Novo Conteúdo l1 Lista Duplamente encadeada: ");
                l1.imprime();
                System.out.println();
            }
            ListaDupla l2 = new ListaDupla();
            l2.insere(2);
            l2.insere(7);
            l2.insere(8);
            l2.insere(19);
            l2.insere(4);
            l2.insere(6);
            l2.insere(5);
            l2.insere(80);
            l2.insere(21);
            System.out.print("Conteúdo l2 Lista Duplamente encadeada: ");
            l2.imprime();
            System.out.println();
            System.out.println("Verifica lista iguais: " + l2.igual(l1));
            System.out.println();
            ListaDupla l3 = new ListaDupla();
            l3.insere(4);
            l3.insere(2);
            l3.insere(5);
            l3.insere(1);
            l3.insere(10);
            l3.insere(11);
            l3.insere(12);
            if (l3.vazia() == true) {
                System.out.print("Conteúdo da Lista l3 Duplamente encadeada: ");
                l3.imprime();
            }
            System.out.println();
            ListaDupla l4 = new ListaDupla();
            l4.insere(6);
            l4.insere(5);
            l4.insere(80);
            l4.insere(21);
            l4.insere(26);
            if (l4.vazia() == false) {
                System.out.print("Conteúdo da Lista l4 Duplamente encadeada: ");
                l4.imprime();
            }
            System.out.println();
            ListaDupla l5 = l3.merge(l4);
            System.out.print("Conteúdo do método merge da Lista l3 com l4 criando uma nova lista l5 Duplamente encadeada: ");
            l5.imprime();
            System.out.println();
            ListaDupla l6 = new ListaDupla();
            l6.insere(3);
            l6.insere(10);
            l6.insere(30);
            l6.insere(25);
            l6.insere(28);
            l6.insere(61);
            l6.insere(533);
            l6.insere(801);
            l6.insere(23);
            l6.insere(2);
            if (l6.vazia() == false) {
                System.out.print("Conteúdo da Lista l6: ");
                l6.imprime();
                System.out.println();
                int n = 533;
                ListaDupla l7 = l6.separa(n);
                System.out.print("Conteúdo da lista l6 apos usar o método separa ate o nó com o número " + n + ": ");
                l6.imprime();
                System.out.println();
                System.out.print("Conteúdo da nova lista l7 após o número " + n + " usado no metodo separa lista l6: ");
                l7.imprime();
                System.out.println();
            }
            System.out.println();
        }
    }
}
