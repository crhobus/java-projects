package L102M2G03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Sistema {

    public static int getEntrada(BufferedReader in, String mensagem) {
        String info = "";
        do {
            System.out.print(mensagem);
            try {
                info = in.readLine();
            } catch (IOException ex) {
                System.out.println("\nErro na leitura da informação\n");
            }
            if ("".equals(info)) {
                return 0;
            } else {
                try {
                    return Integer.parseInt(info);
                } catch (NumberFormatException ex) {
                    System.out.println("\nInforme um número válido\n");
                }
            }
        } while (true);
    }

    public static void main(String[] args) throws InterruptedException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("L102M2G03 - Caio Renan Hobus");
        int numProd;
        while (true) {
            numProd = getEntrada(in, "Informe nro. de produtores : ");
            if (numProd >= 1) {
                break;
            }
        }
        int numArmaProd;
        while (true) {
            numArmaProd = getEntrada(in, "Informe nro de áreas para armazenar produtos enviados pelos prod. : ");
            if (numArmaProd >= 1) {
                break;
            }
        }

        boolean condicao = false;
        List<Integer> numConsProds = new ArrayList<Integer>();
        List<Integer> numArmaConsProds = new ArrayList<Integer>();
        int entConsProd = 0;
        int entArmaConsProd = 0;

        while (true) {
            while (true) {
                entConsProd = getEntrada(in, "Informe nro de consumidores/produtores: ");
                if (entConsProd == 0) {
                    condicao = true;
                    break;
                }
                if (entConsProd >= 1) {
                    break;
                }
            }
            if (condicao) {
                break;
            }
            numConsProds.add(entConsProd);
            while (true) {
                entArmaConsProd = getEntrada(in, "Informe nro de áreas para armazenar produtos enviados pelo cons./prod.: ");
                if (entArmaConsProd >= 1) {
                    break;
                }
            }
            numArmaConsProds.add(entArmaConsProd);
        }

        int numCons;
        while (true) {
            numCons = getEntrada(in, "Informe nro. de consumidores: ");
            if (numCons >= 1) {
                break;
            }
        }
        new InfIniciais(numProd, numArmaProd, numConsProds, numArmaConsProds, numCons);
    }
}
