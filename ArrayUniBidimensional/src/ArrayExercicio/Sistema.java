package ArrayExercicio;

public class Sistema {

    public static void main(String[] args) {
        ArrayExe meu = new ArrayExe(99);
        System.out.print("\nNumero Gerados = " + meu.carregar(10));
        meu.mostrar();
        System.out.println("\nSomatório: " + meu.somatorio());
        System.out.println("\nQuantidade Primos: " + meu.qtdPrimos());
    }
}
