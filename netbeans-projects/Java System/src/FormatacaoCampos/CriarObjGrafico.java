package FormatacaoCampos;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class CriarObjGrafico {

    public static JLabel criarJLabel(String s, int x, int y, int w, int z) {
        JLabel label = new JLabel(s);
        label.setBounds(x, y, w, z);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }

    public static JTextField criarJTextField(int x, int y, int w, int z) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, w, z);
        return textField;
    }

    public static JPanel criarJPanel(int x, int y, int w, int z) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, w, z);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return panel;
    }

    public static JPanel criarJPanelTitulo(String titulo, int x, int y, int w, int z) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, w, z);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        return panel;
    }

    public static JButton criarJButton(String s, int x, int y, int w, int z) {
        JButton button = new JButton(s);
        button.setBounds(x, y, w, z);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(new Color(255, 255, 0));
        return button;
    }

    public static JFormattedTextField criarJFormattedTextField(String formato, int x, int y, int w, int z) {
        MaskFormatter mascara = null;
        try {
            mascara = new MaskFormatter(formato);
        } catch (ParseException ex) {
        }
        JFormattedTextField formatted = new JFormattedTextField(mascara);
        formatted.setBounds(x, y, w, z);
        formatted.setFocusLostBehavior(JFormattedTextField.COMMIT);
        return formatted;
    }

    public static JComboBox criarJComboBox(Set<String> itens, int x, int y, int w, int z) {
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(x, y, w, z);
        comboBox.addItem("Selecione");
        for (String s : itens) {
            comboBox.addItem(s);
        }
        comboBox.setBackground(Color.WHITE);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
        return comboBox;
    }

    public static JRadioButton criarJRadioButton(String s, int x, int y, int w, int z) {
        JRadioButton radioButton = new JRadioButton(s);
        radioButton.setBounds(x, y, w, z);
        radioButton.setFont(new Font("Arial", Font.BOLD, 12));
        radioButton.setBackground(new Color(248, 248, 248));
        return radioButton;
    }

    public static JPasswordField criarJPasswordField(int x, int y, int w, int z) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, w, z);
        return passwordField;
    }

    public static JTextArea criarJTextArea(JPanel panel, int x, int y, int w, int z) {
        JTextArea textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(x, y, w, z);
        panel.add(scroll);
        return textArea;
    }
}
