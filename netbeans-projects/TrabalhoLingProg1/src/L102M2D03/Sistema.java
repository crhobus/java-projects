package L102M2D03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sistema {

    public static void main(String[] args) {
        System.out.println("L102M2D03 - Caio Renan Hobus");
        FilaLista filaLista = new FilaLista();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            boolean condicao = false;
            String info;
            do {
                System.out.print("Informe o nome de uma cidade: ");
                info = in.readLine();
                if (info.equals("")) {
                    condicao = false;
                } else {
                    filaLista.insere(info);
                    condicao = true;
                }
            } while (condicao);
            if (!filaLista.vazio()) {
                condicao = false;
                do {
                    try {
                        System.out.print("Informe o numero para ser listado os xsPrimeiros: ");
                        System.out.println(filaLista.xsPrimeiros(Integer.parseInt(in.readLine())));
                        condicao = false;
                    } catch (NumberFormatException ex) {
                        System.err.println("Informe um numero valido");
                        condicao = true;
                    }
                } while (condicao);
                condicao = false;
                do {
                    try {
                        System.out.print("Informe o numero para ser listado os xsUltimos: ");
                        System.out.println(filaLista.xsUltimos(Integer.parseInt(in.readLine())));
                        condicao = false;
                    } catch (NumberFormatException ex) {
                        System.err.println("Informe um numero valido");
                        condicao = true;
                    }
                } while (condicao);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        filaLista.libera();
        System.out.println("FIM DA LISTAGEM");
    }
}
