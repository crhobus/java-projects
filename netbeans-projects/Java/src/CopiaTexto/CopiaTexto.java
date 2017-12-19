package CopiaTexto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CopiaTexto extends JFrame {

    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton copiaJButton;

    public CopiaTexto() {
        super("TextArea Demo");
        Box box = Box.createHorizontalBox();
        String demo = "This is a demo string to\n" + "illustrate copying text\nfrom one textArea to \n" + "another textarea using an\nextternal event\n";
        textArea1 = new JTextArea(demo, 10, 15);
        box.add(new JScrollPane(textArea1));
        copiaJButton = new JButton("Copy >>>");
        box.add(copiaJButton);
        copiaJButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                textArea2.setText(textArea1.getSelectedText());
            }
        });
        textArea2 = new JTextArea(10, 15);
        textArea2.setEditable(false);
        box.add(new JScrollPane(textArea2));
        add(box);
    }
}
