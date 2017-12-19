package Exe8;

import java.util.Scanner;

public class Exe8Principal {

    public Exe8Principal() {
        Scanner scan = new Scanner(System.in);
        Controle controle = new Controle();
        controle.addProduto(new Produto(1, "Lápis 6B", "Faber Castell", 45, 1.50));
        controle.addProduto(new Produto(2, "Caderno Capa Dura", "Tilibra", 38, 8.65));
        controle.addProduto(new Produto(3, "Borracha", "Faber Castell", 15, 2.15));
        controle.addProduto(new Produto(4, "Lapiseira", "Pilot", 8, 7.25));
        controle.addProduto(new Produto(5, "Grafite 0.5", "Pilot", 15, 3.60));
        controle.addProduto(new Produto(6, "Caderno de Desenho", "ArtBras", 60, 3.90));
        controle.addProduto(new Produto(7, "Lápis de Cor", "Faber Castell", 21, 16.95));
        controle.addProduto(new Produto(8, "Penal", "Plasthaus", 30, 5.95));
        controle.addProduto(new Produto(9, "Apontador", "Faber Castell", 25, 0.75));
        controle.addProduto(new Produto(10, "Tesoura", "Tesourex", 16, 4.75));
        controle.addProduto(new Produto(11, "Caneta - Preta", "Pilot", 13, 3.00));
        controle.addProduto(new Produto(12, "Caneta – Azul", "Bic", 26, 1.10));
        controle.addProduto(new Produto(13, "Cola", "Tenaz", 48, 3.30));
        controle.addProduto(new Produto(14, "Cartolina", "Cartex", 78, 0.35));
        controle.addProduto(new Produto(15, "Papel A4 – 300 folhas", "Chamex", 62, 8.95));
        System.out.println("Produtos:");
        System.out.println(controle.getProdutos());
        System.out.println("Entre com o código do produto para realizar a busca");
        int codProduto = scan.nextInt();
        if (codProduto >= 1 && codProduto <= 15) {
            System.out.println(controle.getProduto(codProduto));
        } else {
            System.err.println("Código não existe");
        }
    }
}
