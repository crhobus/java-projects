package Operacoes;


public class main {

    public static void main(String[] args) {
        int i;
        int media = 0;
        int somatorio = 0;
        int somatoriope = 0;
        int somaRetArea = 0;
        int mediaRetArea = 0;
        int mediaRet = 0;
        Quadrado[] meusQuad = new Quadrado[10];
        Retangulo[] meusRet = new Retangulo[10];
        for (i = 0; i < 10; i++) {
            meusQuad[i] = new Quadrado(i * 2 + 1);
            meusRet[i] = new Retangulo(i + 1, i + 2);
        }
        System.out.println("Area do meu Quadrado = " + meusQuad[0].area());
        System.out.println("Perimetro do meu Quadrado = " + meusQuad[0].perimetro());
        System.out.println("Area do meu Retangulo = " + meusRet[0].perimetro());
        for (i = 0; i < 10; i++) {
            somatorio += meusQuad[i].area();
            somaRetArea += meusRet[i].area();
            somatoriope += meusQuad[i].perimetro();
            media = somatorio / 10;
            mediaRet = somaRetArea / 10;
        }
        System.out.println("media do meu Quadrado = " + media);
        System.out.println("Somatorio do meu perimetro do meu Quadrado = " + somatoriope);
        System.out.println("Somatorio do meu perimetro do meu Retangulo = " + somaRetArea);
        System.out.println("Media do meu Retangulo = " + mediaRet);
    }
}
