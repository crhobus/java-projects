package View;

import java.util.Scanner;

public class Exe8 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Produtos:");
        String produtos = "Código\tProduto\t\t\tMarca\t\tQuantidade\tValor (unitário)\n"
                + "1\tLápis 6B\t\tFaber Castell\t45\t\tR$ 1,50\n"
                + "2\tCaderno Capa Dura	Tilibra\t\t38\t\tR$ 8,65\n"
                + "3\tBorracha\t\tFaber Castell\t15\t\tR$2,15\n"
                + "4\tLapiseira\t\tPilot\t\t8\t\tR$7,25\n"
                + "5\tGrafite\t0.5\t\tPilot\t\t15\t\tR$3,60\n"
                + "6\tCaderno de Desenho\tArtBras\t\t60\t\tR$3,90\n"
                + "7\tLápis de Cor\t\tFaber Castell\t21\t\tR$16,95\n"
                + "8\tPenal\t\t\tPlasthaus\t30\t\tR$5,95\n"
                + "9\tApontador\t\tFaber Castell\t25\t\tR$0,75\n"
                + "10\tTesoura\t\t\tTesourex\t16\t\tR$4,75\n"
                + "11\tCaneta - Preta\t\tPilot\t\t13\t\tR$3,00\n"
                + "12\tCaneta – Azul\t\tBic\t\t26\t\tR$1,10\n"
                + "13\tCola\t\t\tTenaz\t\t48\t\tR$3,30\n"
                + "14\tCartolina\t\tCartex\t\t78\t\tR$0,35\n"
                + "15\tPapel A4 – 300 folhas\tChamex\t\t62\t\tR$8,95";
        System.out.println(produtos);
        System.out.println("Entre com o código do produto para realizar a busca");
        int codProduto = scan.nextInt();
        if (codProduto >= 1 && codProduto <= 15) {
            String[] str = produtos.split("\n");
            System.out.println("\nCódigo\tProduto\t\t\tMarca\t\tQuantidade\tValor (unitário)");
            System.out.println(str[codProduto]);
        } else {
            System.err.println("Código não existe");
        }
    }
}
