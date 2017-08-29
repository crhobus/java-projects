package ArvoreBinaria;

public class ArvoreBinariaMain {

    public static void main(String[] args) {
        ArvoreBinaria arvBin = new ArvoreBinaria();
        System.out.println("Verifica se esta vazia: " + arvBin.vazia());
        arvBin.insere(4,
                arvBin.insere(9,
                arvBin.insere(47, arvBin.insere(46,
                arvBin.insere(6, arvBin.insere(6, null, null),
                arvBin.insere(70, null, arvBin.insere(89, null, null))), arvBin.insere(60,
                null, arvBin.insere(75, null, null))),
                arvBin.insere(50, null, null)),
                arvBin.insere(41,
                arvBin.insere(50, null,
                arvBin.insere(71, null, null)),
                null)), arvBin.insere(42, null, null));
        System.out.println("Verifica se esta vazia: " + arvBin.vazia());
        System.out.println("Arvore Binaria" + arvBin.toString());
        System.out.println("Quantidade numeros pares: " + arvBin.pares());
        System.out.println("Quantidade folhas: " + arvBin.folhas());
        System.out.println("Verifica se número pertence ou não a arvore binária: " + arvBin.pertence(41));

        ArvoreBinaria a = new ArvoreBinaria();
        ArvoreBinaria b = new ArvoreBinaria();
        a.insere(1, a.insere(4, null, null), a.insere(3, null, null));
        b.insere(1, b.insere(4, null, null), b.insere(3, null, null));
        System.out.println("Verifica Árvore Binária a igual b: " + a.igual(b));
        System.out.println("Arvore Binaria original:" + arvBin.toString());
        ArvoreBinaria c = arvBin.copia();
        System.out.println("Copia da arvore original: " + c);
        ArvoreBinaria nova = new ArvoreBinaria();
        nova.insere(1,
                nova.insere(2,
                null,
                nova.insere(4, null, nova.insere(4, null, nova.insere(1, null, null)))),
                nova.insere(3,
                nova.insere(5, null, null),
                nova.insere(6, null, null)));
        System.out.println("Soma do maior caminho até a folha: " + nova.maiorSomaCaminho());
    }
}
