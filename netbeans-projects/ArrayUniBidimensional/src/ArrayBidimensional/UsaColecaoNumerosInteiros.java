package ArrayBidimensional;

public class UsaColecaoNumerosInteiros {

    public static void main(String[] args) {
        ColecaoNumerosInteiros meu = new ColecaoNumerosInteiros(100);
        meu.carregar();
        meu.mostrar();
        System.out.println("O elemento do conjunto retornado é:  = " + meu.get(1, 2));
        System.out.println("Um novo elemento foi colocado nesta posição:  = " + meu.set(1, 3, 8));
        meu.mostrar();
        System.out.println("Soma da MATRIZ:  = " + meu.getSoma());
        System.out.println("O maior valor da MATRIZ:  = " + meu.getMaior());
        System.out.println("A soma desta linha da MATRIZ:  = " + meu.getSomaLinha(2));
        System.out.println("A soma desta coluna da MATRIZ:  = " + meu.getSomaColuna(0));
    }
}
