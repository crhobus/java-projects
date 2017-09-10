package ArrayBidimensional2;

public class UsaColecao {

    public static void main(String[] args) {
        Colecao um = new Colecao(4);
        Colecao dois = new Colecao(4);
        System.out.println("Carregar Matriz: " + um.carregar(4));
        System.out.println("Carregar Transversa: " + um.CarregarTrans(4));
        um.set(29, 1, 2);
        um.ordenar(1);
        um.mostrar("Normal");
        System.out.println("\nTRANSVERSA:\n");
        um.mostrar("Transversa");
        System.out.println("Terceira linha foi ordenada.");
        System.out.println("Elementos na coluna 4,4: " + um.get(3, 3));
        System.out.println("Menor numero: " + um.menor());
        System.out.println("Soma matriz: " + um.somatorio());
        System.out.println("Soma 3ª linha: " + um.SomaLinha(2));
        System.out.println("Soma 3ª coluna: " + um.SomaColuna(2));
        System.out.println("Soma Diagonal: " + um.SomaDiagonal());
        System.out.println("\nMATRIZ DOIS:\n");
        System.out.println("Carregar Matriz dois: " + dois.carregar(4));
        System.out.println("Carregar Transversa dois: " + dois.CarregarTrans(4));
        dois.set(29, 1, 2);
        dois.ordenar(3);
        dois.mostrar("Normal");
        System.out.println("\nTRANSVERSA:\n");
        dois.mostrar("Transversa");
        System.out.println("Terceira linha foi ordenada.");
        System.out.println("Elementos na coluna 4,4: " + dois.get(3, 3));
        System.out.println("Menor numero: " + dois.menor());
        System.out.println("Soma matriz: " + dois.somatorio());
        System.out.println("Soma 3ª linha: " + dois.SomaLinha(2));
        System.out.println("Soma 3ª coluna: " + dois.SomaColuna(2));
        System.out.println("Soma Diagonal: " + dois.SomaDiagonal());
    }
}
