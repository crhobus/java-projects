package Exe15;

import java.util.Scanner;

public class Exe15Principal {

    public Exe15Principal() {
        Scanner scan = new Scanner(System.in);
        Autor autores = new Autor();
        System.out.println("Dados Autor");
        System.out.println(autores.getAutores());
        System.out.println("Entre com o nome do autor:");
        System.out.println(autores.getDadosAutor(scan.nextLine()));
    }
}
