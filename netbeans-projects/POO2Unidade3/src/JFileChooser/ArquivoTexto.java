package JFileChooser;

import java.io.*;
import javax.swing.*;

public class ArquivoTexto {

    public ArquivoTexto() {
        JFileChooser jf = new JFileChooser();
        int ret = jf.showOpenDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION) {
            try {
                JOptionPane.showMessageDialog(null, "Carecteres: " + qtdadeCaracteres(jf.getSelectedFile()) + " Linhas: " + qtdadeLinhas(jf.getSelectedFile()), "Informação", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private int qtdadeCaracteres(File seleciona) throws Exception {
        int qtdade = 0;
        FileReader reader = new FileReader(seleciona);
        do {
            int caracter = reader.read();//le qtdade caracteres
            if (caracter == -1) { //quando chega no fim do arquivo
                break;
            }
            qtdade++;
        } while (true);
        reader.close();
        return qtdade;
    }

    private int qtdadeLinhas(File seleciona) throws Exception {
        int qtdade = 0;
        BufferedReader reader = new BufferedReader(new FileReader(seleciona));
        do {
            String linha = reader.readLine();//reader.readLine() le uma linha
            if (linha == null) {
                break;
            }
            qtdade++;
        } while (true);
        reader.close();
        return qtdade;
    }

    public static void main(String[] args) {
        ArquivoTexto arq = new ArquivoTexto();
    }
}

