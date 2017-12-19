package Exe2;

import java.util.Scanner;

public class Exe2Principal {

    public Exe2Principal() {
        Scanner scan = new Scanner(System.in);
        Maio maio = new Maio();
        System.out.println("Maio 2010");
        System.out.println("Entre com o dia para verificar qual é o dia da semana ou digite feriado para mostrar os feriados do mes");
        maio.setDia(scan.next());
        System.out.println(maio.diaSemana());
    }
}
