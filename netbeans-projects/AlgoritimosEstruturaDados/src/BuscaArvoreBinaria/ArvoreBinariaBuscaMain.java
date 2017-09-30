package BuscaArvoreBinaria;

public class ArvoreBinariaBuscaMain {

    public static void main(String[] args) {
        ArvoreBinariaBusca arvBinBus = new ArvoreBinariaBusca();
        arvBinBus.insere(43);
        arvBinBus.insere(3);
        arvBinBus.insere(28);
        arvBinBus.insere(12);
        arvBinBus.insere(43);
        arvBinBus.insere(65);
        arvBinBus.insere(32);
        arvBinBus.insere(36);
        System.out.println("Árvore Binária de busca: " + arvBinBus.toString());
        NoArvoreBinaria noArvoreBinaria = arvBinBus.busca(43);
        System.out.println("Valor recuperado da arvore: " + noArvoreBinaria);
        int n = 36;
        arvBinBus.retira(n);
        System.out.println("Arvore Binária de busca apos ter retirado o valor " + n + ": " + arvBinBus.toString());
        System.out.print("toString Descrescente: ");
        arvBinBus.toStringDecrescente();
        System.out.println();
        int x = 32;
        System.out.println("Soma do valores maiores que " + x + ": " + arvBinBus.maioresX(x));
        System.out.println("Soma folhas " + arvBinBus.somaFolhas());
    }
}
