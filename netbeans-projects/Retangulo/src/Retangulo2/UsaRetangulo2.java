package Retangulo2;

public class UsaRetangulo2 {

    public static void main(String[] args) {
        Retangulo2 meu = new Retangulo2(4, 3);
        Retangulo2 teu = new Retangulo2(6, 7);
        System.out.println(meu.area());
        System.out.println(teu.area());
        System.out.println(meu.perimetro());
        System.out.println(teu.perimetro());
        if (meu.ehRetangulo() == true) {
            System.out.println("Meu eh retangulo");
        } else {
            System.out.println("Meu não eh retangulo");
        }
        if (teu.ehRetangulo() == true) {
            System.out.println("Meu eh retangulo");
        } else {
            System.out.println("Meu não eh retangulo");
        }
    }
}
