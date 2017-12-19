package Exe13;

import java.util.Scanner;

public class Exe13Principal {

    public Exe13Principal() {
        Scanner scan = new Scanner(System.in);
        Horario horario = new Horario();
        System.out.println("Mensagem horário");
        System.out.println("Entre com a hora:");
        System.out.println(horario.getMensagem(scan.nextInt()));
    }
}
