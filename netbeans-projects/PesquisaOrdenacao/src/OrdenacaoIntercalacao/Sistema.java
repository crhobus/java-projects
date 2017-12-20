package OrdenacaoIntercalacao;

public class Sistema {

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort(1000);
        System.out.println("Vetor: " + mergeSort + "\n");
        mergeSort.sort();
        System.out.println("Vetor Ordenado: " + mergeSort);
    }
}
