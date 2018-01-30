package Principal;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class MostraCalculo extends JDialog {

    private static final long serialVersionUID = 1L;
    private JTextArea taResult;

    public MostraCalculo(String str) {
        initComponents();
        taResult.setText(str);
    }

    private void initComponents() {
        setTitle("Média, Mediana e Moda");

        taResult = new JTextArea();
        taResult.setEditable(false);
        JScrollPane scroll = new JScrollPane(taResult);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scroll);

        setSize(550, 500);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
