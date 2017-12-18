package exercicio;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Sistema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre com o nome da tabela");
        String nomeTab = scanner.next();
        System.out.println("Entre com a quantidade de registros");
        int qtdadeCampos = scanner.nextInt();
        try {
            TableSpace tableSpace = new TableSpace(qtdadeCampos, nomeTab);
            String str;
            for (int i = 0; i < qtdadeCampos; i++) {
                System.out.println("Entre com o nome do " + (i + 1) + "° campo");
                str = scanner.next();
                tableSpace.addRotulo(str);
            }
            tableSpace.gravarRotulo();
            str = "s";
            while (true) {
                if ("s".equalsIgnoreCase(str)) {
                    for (int i = 0; i < qtdadeCampos; i++) {
                        System.out.println("Entre com um registro para o campo " + tableSpace.getNomeRotulo(i));
                        str = scanner.next();
                        tableSpace.insereRegistro(str);
                    }
                    tableSpace.gravarRegistro();
                } else if ("n".equalsIgnoreCase(str)) {
                    break;
                } else {
                    System.out.println("Entre com uma opção válida");
                }
                System.out.println("Deseja adicionar um novo registro S / N");
                str = scanner.next();
            }
            tableSpace.fecharArquivo();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
