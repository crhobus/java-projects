package SetHashSet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algoritimo {

    private static final String cores[] = {"Vermelho", "Branco", "Azul", "Verde", "Cinza", "Laranja", "Branco", "Ciano", "Preto", "Laranja", "Marrom"};

    public Algoritimo() {
        List<String> lista = Arrays.asList(cores);
        System.out.printf("ArrayList: %s\n", lista);
        imprimirNaoDuplicadas(lista);
    }

    private void imprimirNaoDuplicadas(Collection<String> colecao) {
        Set<String> set = new HashSet<String>(colecao);//Quando HashSet é construido remove qualquer duplicata na Collection
        System.out.println("\nElementos não duplicados: ");
        for (String s : set) {
            System.out.printf("%s ", s);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new Algoritimo();
    }
}
