package View;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Dialogo extends JDialog {

    private static final long serialVersionUID = 1L;

    public Dialogo(String saida) {
        this.setTitle("Dados referentes ao cadastro");
        JTextArea textArea = new JTextArea(saida);
        textArea.setEditable(false);
        textArea.setBackground(Color.WHITE);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent evento) {
                System.exit(0);
            }
        });
        this.add(new JScrollPane(textArea));
        this.setSize(480, 300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
