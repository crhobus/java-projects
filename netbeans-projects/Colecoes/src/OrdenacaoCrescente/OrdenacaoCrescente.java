package OrdenacaoCrescente;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrdenacaoCrescente {

    private static final String cores[] = {"Verde", "Azul", "Roxo", "Preto", "Amarelo", "Branco", "Ciano", "Marrom", "Azul", "Prata", "Rosa", "Vermelho", "Liláz", "Cinza", "Azul Claro"};

    public void imprimirElementos() {
        List<String> lista = Arrays.asList(cores);
        System.out.printf("Lista: %s\n", lista);
        Collections.sort(lista);
        System.out.printf("Lista ordenada em ordem crescente: %s\n", lista);
    }

    public static void main(String[] args) {
        OrdenacaoCrescente sort = new OrdenacaoCrescente();
        sort.imprimirElementos();
    }
}
