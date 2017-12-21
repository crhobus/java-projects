package Trabalho1;

import java.util.*;
import javax.swing.*;

public class Sistema {

    public static void main(String[] args) {
        String op = JOptionPane.showInputDialog(null, "1-PDF ou 2-HTML");
        TipoRelatorio tipo = null;

        if (op.equals("1")) {
            tipo = new PDF();
        } else {
            if (op.equals("2")) {
                tipo = new HTML();
            } else {
                JOptionPane.showMessageDialog(null, "Número invalido", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        ArrayList<String> linhas = new ArrayList<String>();
        linhas.add("Linha 1");
        linhas.add("Linha 2");
        linhas.add("Linha 3");
        linhas.add("Linha 4");
        linhas.add("Linha 5");
        linhas.add("Linha 6");
        linhas.add("Linha 7");
        linhas.add("Linha 8");
        linhas.add("Linha 9");
        linhas.add("Linha 10");

        Relatorio r = new Relatorio("Titulo Relatorio", linhas, "Rodape Relatorio", tipo);
        r.imprimir();
    }
}
