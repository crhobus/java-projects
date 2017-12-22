import javax.swing.*;
import java.awt.*;
import java.util.*;

public class VerUsuario extends JDialog {

    ArrayList<ArrayUsuario> lista;

    VerUsuario(ArrayList lista) {
        this.lista = lista;
        this.setSize(350, 300);
        this.setLocationRelativeTo(null);//Posiciona no centro da tela
        this.setResizable(false);
        this.criar();
        this.setVisible(true);
    }

    private void criar() {
        Color cor = new Color(200, 200, 200);
        Container tela = getContentPane();
        JLabel label = new JLabel("Abaixo Todos Usuarios cadastrados");
        String texto = "";
        for (int i = 0; i < lista.size(); i++) {
            ArrayUsuario n = lista.get(i);
            texto += i + 1 + ". Codigo: " + n.getCodigo() + "\n";
            texto += i + 1 + ". Nome: " + n.getNome() + "\n";
            texto += i + 1 + ". Permissao: " + n.getPermissao() + "\n";
            texto += "" + "\n";
        }
        JTextArea area = new JTextArea(texto);
        area.setEditable(false);//Não editavel
        area.setBackground(cor);//cor fundo
        JScrollPane rolagem = new JScrollPane(area);//barra de rolagem
        tela.add(label, BorderLayout.NORTH);//borderlayout mostra label em cima
        tela.add(rolagem, BorderLayout.CENTER);
    }
}

