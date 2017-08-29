package ArvoreNumeroVariavelFilhos;

public class ArvoreMain {

    public static void main(String[] args) {
        Arvore arvo = new Arvore();
        NoArvore n1 = arvo.criaNo(1);
        NoArvore n2 = arvo.criaNo(2);
        NoArvore n3 = arvo.criaNo(3);
        NoArvore n4 = arvo.criaNo(4);
        NoArvore n5 = arvo.criaNo(5);
        NoArvore n6 = arvo.criaNo(6);
        NoArvore n7 = arvo.criaNo(7);
        NoArvore n8 = arvo.criaNo(8);
        NoArvore n9 = arvo.criaNo(9);
        NoArvore n10 = arvo.criaNo(10);

        arvo.insereFilho(n3, n4);
        arvo.insereFilho(n2, n5);
        arvo.insereFilho(n2, n3);
        arvo.insereFilho(n9, n10);
        arvo.insereFilho(n7, n9);
        arvo.insereFilho(n7, n8);
        arvo.insereFilho(n1, n7);
        arvo.insereFilho(n1, n6);
        arvo.insereFilho(n1, n2);
        int n = 9;
        System.out.println("Arvore com número variável de filhos" + arvo.toString());
        System.out.println("Verifica se " + n + " pertence a árvore: " + arvo.pertence(n));
        System.out.println("Altura da árvore: " + arvo.altura());
        System.out.println("Quantidade de números pares da árvore: " + arvo.pares());
        System.out.println("Quantidade de árvores folhas: " + arvo.folhas());

        Arvore arvo2 = new Arvore();
        NoArvore n11 = arvo2.criaNo(1);
        NoArvore n22 = arvo2.criaNo(2);
        NoArvore n33 = arvo2.criaNo(3);
        NoArvore n44 = arvo2.criaNo(4);
        NoArvore n55 = arvo2.criaNo(5);
        NoArvore n66 = arvo2.criaNo(6);
        NoArvore n77 = arvo2.criaNo(7);
        NoArvore n88 = arvo2.criaNo(8);
        NoArvore n99 = arvo2.criaNo(9);
        NoArvore n100 = arvo2.criaNo(10);

        arvo2.insereFilho(n33, n44);
        arvo2.insereFilho(n22, n55);
        arvo2.insereFilho(n22, n33);
        arvo2.insereFilho(n99, n100);
        arvo2.insereFilho(n77, n99);
        arvo2.insereFilho(n77, n88);
        arvo2.insereFilho(n11, n77);
        arvo2.insereFilho(n11, n66);
        arvo2.insereFilho(n11, n22);
        System.out.println("Verifica se as arvores são iguais: " + arvo.igual(arvo2));
        System.out.println("Arvore original" + arvo.toString());
        System.out.println("Copia árvore: " + arvo.copia().toString());
    }
}
