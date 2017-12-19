package Exe22;

import java.util.Scanner;

public class Exe22Principal {

    public Exe22Principal() {
        Scanner scan = new Scanner(System.in);
        VelocidadeMedia velocidadeMedia = new VelocidadeMedia();
        System.out.println("Calculo Velocidade Média");
        System.out.println("Entre com o espaço percorrido em metros");
        double espaco = scan.nextDouble();
        System.out.println("Tempo utilizado para percorrer o espaço informado em segundos");
        double tempo = scan.nextDouble();
        System.out.println(velocidadeMedia.getVelocidadeMedia(espaco, tempo));
    }
}
