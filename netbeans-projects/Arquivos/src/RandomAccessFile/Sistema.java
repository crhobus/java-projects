package RandomAccessFile;

import javax.swing.JOptionPane;

public class Sistema {

    public static void main(String[] args) {
        boolean erro = true;
        do {
            String resposta = JOptionPane.showInputDialog(null, "1 - Para RandomClientes.dat ou 2 - Para RandomClientesAux.dat", "Opção", JOptionPane.QUESTION_MESSAGE);
            if (resposta == null) {
                erro = false;
            } else {
                try {
                    int num = Integer.parseInt(resposta);
                    if (num == 1 || num == 2) {
                        if (num == 1) {
                            ProcessoTransacao aplicacao = new ProcessoTransacao();
                            aplicacao.processar();
                            erro = false;
                        } else {
                            LerEscreverRandomArquivo escrever = new LerEscreverRandomArquivo();
                            escrever.addRegistros();
                            escrever.lerRegistros();
                            erro = false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Entre com uma opção válida", "Erro", JOptionPane.ERROR_MESSAGE);
                        erro = true;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, tente novamente", "Erro", JOptionPane.ERROR_MESSAGE);
                    erro = true;
                }
            }
        } while (erro);
    }
}
