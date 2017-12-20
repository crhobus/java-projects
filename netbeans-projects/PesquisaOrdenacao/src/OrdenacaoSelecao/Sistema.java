package OrdenacaoSelecao;

public class Sistema {

    public static void main(String[] args) {
        SelecaoSort selecaoSort = new SelecaoSort(10);
        System.out.println("Vetor:");
        System.out.println(selecaoSort);
        selecaoSort.sort();
        System.out.println("Vetor Ordenado:");
        System.out.println(selecaoSort);
    }
}
