package Pilha;

import javax.swing.*;

public class PilhaMain {

    public static void main(String[] args) {
        PilhaLista pilhaLis = new PilhaLista();
        try {
            System.out.println("Pilha com Lista");
            pilhaLis.push(1);
            pilhaLis.push(2);
            float a = pilhaLis.pop();
            float b = pilhaLis.pop();
            float c = b - a;
            pilhaLis.push(c);
            pilhaLis.push(4);
            pilhaLis.push(5);
            float d = pilhaLis.pop();
            float e = pilhaLis.pop();
            float f = d + e;
            pilhaLis.push(f);
            float g = pilhaLis.pop();
            float h = pilhaLis.pop();
            float i = g * h;
            pilhaLis.push(i);
            System.out.println("Resultado pilha lista: " + i);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        PilhaLista pilhaLis2 = new PilhaLista();
        try {
            pilhaLis2.push(87);
            pilhaLis2.push(7);
            pilhaLis2.push(3);
            pilhaLis2.push(19);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println("Pilha Lista: " + pilhaLis2.toString());
        try {
            System.out.println("Copia pilha lista: " + pilhaLis2.copia().toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try {
            System.out.println("Copia Invertido pilha lista: " + pilhaLis2.copiaInvertido().toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        try {
            System.out.println("FiltraMenores pilha lista: " + pilhaLis2.filtraMenores(7).toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        System.out.println("Pilha com Vetor");
        PilhaLista pilhaVet = new PilhaLista();
        try {
            pilhaVet.push(1);
            pilhaVet.push(2);
            float a = pilhaVet.pop();
            float b = pilhaVet.pop();
            float c = b - a;
            pilhaVet.push(c);
            pilhaVet.push(4);
            pilhaVet.push(5);
            float d = pilhaVet.pop();
            float e = pilhaVet.pop();
            float f = d + e;
            pilhaVet.push(f);
            float g = pilhaVet.pop();
            float h = pilhaVet.pop();
            float i = g * h;
            pilhaVet.push(i);
            System.out.println("Resultado pilha vetor: " + i);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
