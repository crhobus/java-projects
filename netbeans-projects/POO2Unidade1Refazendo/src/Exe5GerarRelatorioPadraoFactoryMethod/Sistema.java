package Exe5GerarRelatorioPadraoFactoryMethod;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class Sistema {

    public static void main(String[] args) {
        String titulo = "Titulo";
        List<String> listaConteudo = new ArrayList<String>();
        listaConteudo.add("Nome1");
        listaConteudo.add("Nome2");
        listaConteudo.add("Nome3");
        listaConteudo.add("Nome4");
        listaConteudo.add("Nome5");
        listaConteudo.add("Nome6");
        listaConteudo.add("Nome7");
        listaConteudo.add("Nome8");

        RelatorioFactory[] factory = {new RelatorioTextoFactory(), new RelatorioXMLFactory(), new RelatorioHTMLFactory()};
        boolean erro = true;
        do {
            String str = JOptionPane.showInputDialog(null, "1: Texto, 2: XML, 3: HTML", "Entre com um número", JOptionPane.INFORMATION_MESSAGE);
            if (str == null) {
                erro = false;
            } else {
                try {
                    int num = Integer.parseInt(str);
                    if (num < 1 || num > 3) {
                        JOptionPane.showMessageDialog(null, "Entre com um número válido", "Erro", JOptionPane.ERROR_MESSAGE);
                        erro = true;
                    } else {
                        RelatorioFactory fabrica = factory[num - 1];
                        fabrica.fazerRelatorio(titulo, listaConteudo);
                        erro = false;
                    }
                } catch (Exception ex) {
                    erro = true;
                    JOptionPane.showMessageDialog(null, "Entre com um valor numérico", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } while (erro);
    }
}
