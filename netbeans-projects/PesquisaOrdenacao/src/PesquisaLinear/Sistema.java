package PesquisaLinear;

import javax.swing.JOptionPane;

public class Sistema {

    private static int getInteger(String s) {
        int n = -1;
        boolean erro = true;
        do {
            String str = JOptionPane.showInputDialog(null, s, "Entrada", JOptionPane.INFORMATION_MESSAGE);
            if (str == null) {
                erro = false;
            } else {
                try {
                    n = Integer.parseInt(str);
                    erro = false;
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entre com um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                    erro = true;
                }
            }
        } while (erro);
        return n;
    }

    public static void main(String[] args) {
        int pesquisarInt, posicao;
        PesquisaLinear pesquisaLinear = new PesquisaLinear(100);
        System.out.println(pesquisaLinear);
        pesquisarInt = getInteger("Entre com um número (-1 para sair)");
        while (pesquisarInt != -1) {
            posicao = pesquisaLinear.buscaLinear(pesquisarInt);
            if (posicao == -1) {
                System.out.println("O inteiro " + pesquisarInt + " não foi encontrado.\n");
            } else {
                System.out.println("O inteiro " + pesquisarInt + " foi encontrado na posição " + posicao + ".\n");
            }
            pesquisarInt = getInteger("Entre com um número (-1 para sair)");
        }
    }
}
