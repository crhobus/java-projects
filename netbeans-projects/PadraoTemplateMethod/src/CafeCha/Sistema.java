package CafeCha;

public class Sistema {

    public static void main(String[] args) {
        Cha meuCha = new Cha();
        meuCha.prepareReceita();

        System.out.println();

        Cafe meuCafe = new Cafe();
        meuCafe.prepareReceita();


        ChaComGancho chaGancho = new ChaComGancho();
        CafeComGancho cafeGancho = new CafeComGancho();

        System.out.println("\nFazer café...");
        cafeGancho.prepareReceita();

        System.out.println("\nFazer chá...");
        chaGancho.prepareReceita();
    }
}
