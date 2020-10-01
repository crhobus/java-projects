package br.com.app.methodreference;

interface Figura {

    Quadrado desenha(Double largura, Double altura);
}

class Quadrado {

    public Quadrado(Double largura, Double altura) {
        System.out.println("Desenha quadrado de Largura: " + largura + " e Altura: " + altura);
    }
}

public class Exemplo04 {

    public static void main(String[] args) {

        //Method reference. Referência a um construtor
        Figura fig1 = Quadrado::new;

        fig1.desenha(10.5, 7.0);
    }
}
