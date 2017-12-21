
public class UsaProjetoCalculos {

    public static void main(String[] args) {
        int area1, area2;
        Quadrado grande = new Quadrado(7);
        Quadrado maior = new Quadrado(9);
        Retangulo bonito = new Retangulo(4, 6);
        Circulo redondo = new Circulo(64);
        Triangulo_Equilatero triangulo = new Triangulo_Equilatero(6);


        System.out.println("Quadrado//Perimetro do Grande = " + grande.perimetro());
        System.out.println("Quadrado//Área do maior = " + maior.area());
        area1 = grande.area();
        area2 = maior.area();
        if (area1 > area2) {
            System.out.println("Area de grande eh maior");
        } else {
            System.out.println("Area de maior eh maior");
        }

        System.out.println("Area do meu retangulo = " + bonito.area());
        System.out.println("Perimetro do meu retangulo =" + bonito.perimetro());
        System.out.println("Area do Circulo =" + redondo.area());
        System.out.println("Perimetro do Circulo =" + redondo.perimetro());
        System.out.println("Altura do Triangulo Equilatero =" + triangulo.altura());
        System.out.println("Area do Triangulo Equilatero = " + triangulo.area());
        System.out.println("Perimetro do Triangulo Equilatero =" + triangulo.perimetro());
    }
}
