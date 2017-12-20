package OrdenacaoInsercao;

public class Sistema {

    public static void main(String[] args) {
        InsercaoSort insercaoSort = new InsercaoSort(10);
        System.out.println("Vetor:");
        System.out.println(insercaoSort);
        insercaoSort.sort();
        System.out.println("Vetor Ordenado:");
        System.out.println(insercaoSort);
    }
}
