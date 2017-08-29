package Fila;

import javax.swing.*;

public class FilaMain {

    public static void main(String[] args) {
        System.out.println("Fila com Vetor:");
        FilaVetor vetor1 = new FilaVetor(20);
        try {
            vetor1.insere(2);
            vetor1.insere(4);
            vetor1.insere(6);
            vetor1.insere(8);
            vetor1.insere(10);
            vetor1.insere(23);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Fila Vetor1: " + vetor1.toString());
        FilaVetor vetor2 = new FilaVetor(20);
        try {
            vetor2.insere(12);
            vetor2.insere(4);
            vetor2.insere(7);
            vetor2.insere(50);
            vetor2.insere(14);
            vetor2.insere(3);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Fila Vetor2: " + vetor2.toString());
        try {
            vetor1.retira();
            vetor1.retira();
            vetor2.retira();
            vetor2.retira();
            vetor2.retira();
            vetor2.retira();
            vetor2.retira();
            vetor2.insere(78);
            vetor1.insere(2000);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try {
            System.out.println("Fila vetor1 concatenado com vetor2: " + vetor1.concatena(vetor2));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try {
            System.out.println("Fila vetor1 merge com vetor2: " + vetor1.merge(vetor2));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        vetor1.libera();
        System.out.println("Vetor1 vazio: " + vetor1.vazia());
        System.out.println("Vetor1 vazio: " + vetor2.vazia());
        FilaVetor vetor = new FilaVetor(20);
        try {
            vetor.insere(5);
            vetor.insere(67);
            vetor.insere(42);
            vetor.insere(1);
            vetor.insere(79);
            vetor.insere(234);
            vetor.insere(2);
            vetor.insere(29);
            vetor.insere(34);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Nova fila vetor criado: " + vetor.toString());
        try {
            System.out.println("Copia fila vetor: " + vetor.copia().toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try {
            System.out.println("Copia Invertido fila vetor: " + vetor.copiaInvertido().toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try {
            System.out.println("fila vetor usando metodo filtraMenores: " + vetor.filtraMenores(42).toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Nova fila vetor original: " + vetor.toString());

        System.out.println();
        System.out.println("Fila com Lista");
        FilaLista lista = new FilaLista();
        System.out.println("Lista vazia: " + lista.vazia());
        try {
            lista.insere(2);
            lista.insere(4);
            lista.insere(6);
            lista.insere(8);
            lista.insere(10);
            lista.insere(23);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Lista vazio: " + lista.vazia());
        System.out.println("Fila Lista: " + lista.toString());
        try {
            lista.retira();
            lista.retira();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Fila Lista após retirada: " + lista.toString());
        lista.libera();
        System.out.println("Lista vazia: " + lista.vazia());
        try {
            lista.insere(3);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Lista vazia: " + lista.vazia());
        System.out.println("Fila lista: " + lista.toString());
    }
}
