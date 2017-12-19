package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

// Classe panel com métodos para criar componentes gráficos 
public class PanelComponentes extends JPanel {

    private static final long serialVersionUID = 1L;

    public PanelComponentes(int x, int y, int w, int z) {
        this.setLayout(null);
        this.setBounds(x, y, w, z);
        this.setBackground(new Color(248, 248, 248));
        this.setBorder(BorderFactory.createEtchedBorder());
    }

    public PanelComponentes(String titulo, int x, int y, int w, int z) {
        this.setLayout(null);
        this.setBounds(x, y, w, z);
        this.setBackground(new Color(248, 248, 248));
        this.setBorder(BorderFactory.createTitledBorder(titulo));
    }

    public void addJLabel(String titulo, int x, int y, int w, int z) {
        JLabel label = new JLabel(titulo);
        label.setBounds(x, y, w, z);
        this.add(label);
    }

    public JLabel addJLabelRED(int x, int y, int w, int z) {
        JLabel label = new JLabel("");
        label.setBounds(x, y, w, z);
        label.setForeground(Color.RED);
        this.add(label);
        return label;
    }

    public JTextField addJTextField(int x, int y, int w, int z) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, w, z);
        this.add(textField);
        return textField;
    }

    public JComboBox addJComboBox(String[] itens, int x, int y, int w, int z) {
        JComboBox comboBox = new JComboBox();
        for (String s : itens) {
            comboBox.addItem(s);
        }
        comboBox.setBounds(x, y, w, z);
        this.add(comboBox);
        return comboBox;
    }

    public JPasswordField addJPasswordField(int x, int y, int w, int z) {
        JPasswordField password = new JPasswordField();
        password.setBounds(x, y, w, z);
        this.add(password);
        return password;
    }

    public JCheckBox addJCheckBox(int x, int y, int w, int z) {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setBounds(x, y, w, z);
        checkBox.setBackground(new Color(248, 248, 248));
        this.add(checkBox);
        return checkBox;
    }

    public JRadioButton addJRadioButton(String titulo, int x, int y, int w, int z) {
        JRadioButton radioButton = new JRadioButton(titulo);
        radioButton.setBounds(x, y, w, z);
        radioButton.setBackground(new Color(248, 248, 248));
        this.add(radioButton);
        return radioButton;
    }

    public JSeparator addJSeparator(int x, int y, int w, int z) {
        JSeparator separator = new JSeparator();
        separator.setBounds(x, y, w, z);
        this.add(separator);
        return separator;
    }

    public JButton addJButton(String titulo, String toolTipText, int x, int y, int w, int z) {
        JButton button = new JButton(titulo);
        button.setBounds(x, y, w, z);
        button.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        button.setToolTipText(toolTipText);
        button.setMargin(new Insets(0, 0, 0, 0));
        this.add(button);
        return button;
    }

    public JButton addJButtonOK(int x, int y, int w, int z) {
        Icon icOk = new ImageIcon("OK.png");
        JButton button = addJButton("OK", "Confirma Operação", x, y, w, z);
        button.setIcon(icOk);
        return button;
    }

    public JButton addJButtonCancelar(int x, int y, int w, int z) {
        Icon icCancelar = new ImageIcon("Cancelar.png");
        JButton button = addJButton("Cancelar", "Limpar os Campos", x, y, w, z);
        button.setIcon(icCancelar);
        return button;
    }

    public JButton addJButtonExcuir(int x, int y, int w, int z) {
        Icon icExcluir = new ImageIcon("Excluir.png");
        JButton button = addJButton("Excluir", "Excluir Registro", x, y, w, z);
        button.setIcon(icExcluir);
        return button;
    }

    public JButton addJButtonSair(int x, int y, int w, int z) {
        Icon icSair = new ImageIcon("Sair.png");
        JButton button = addJButton("Sair", "Fechar", x, y, w, z);
        button.setIcon(icSair);
        return button;
    }

    public JEditorPane addJEditorPane(int x, int y, int w, int z) {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setBorder(BorderFactory.createEtchedBorder());
        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setBounds(x, y, w, z);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);// Dixa a barra de rolagem sempre visível na vertical
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);// Deixa a barra de rolagem sempre visível na horizontal
        this.add(scrollPane);
        return editorPane;
    }
}
