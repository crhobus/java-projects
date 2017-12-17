package OrdenacaoDecrescente;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrdenacaoDecrescente {

    private static final String cores[] = {"Verde", "Azul", "Roxo", "Preto", "Amarelo", "Branco", "Ciano", "Marrom", "Azul", "Prata", "Rosa", "Vermelho", "Liláz", "Cinza", "Azul Claro"};

    public void imprimirElementos() {
        List<String> lista = Arrays.asList(cores);//o método Arrays asList(cores) retorna uma visualização, para visualizar um array como uma coleção
        System.out.printf("Lista: %s\n", lista);
        Collections.sort(lista, Collections.reverseOrder());//o método static Collections reverseOrder() retorna um objeto Comparator que ordena os elementos da coleção na ordem inversa
        System.out.printf("Lista ordenada em ordem decrescente: %s\n", lista);
    }

    public static void main(String[] args) {
        OrdenacaoDecrescente sort = new OrdenacaoDecrescente();
        sort.imprimirElementos();
    }
}
