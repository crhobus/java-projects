package Exe27;

import java.util.Scanner;

public class Exe27Principal {

    public Exe27Principal() {
        Scanner scan = new Scanner(System.in);
        Calendario2010 calendario = new Calendario2010();
        System.out.println("Calendário 2010");
        System.out.println("Entre com o mês");
        calendario.setMes(scan.nextInt());
        System.out.println("Entre com o dia ou digite 0 para visualizar os feriados do mês");
        calendario.setDia(scan.nextInt());
        System.out.println(calendario.getDiaSemana());
    }
}
