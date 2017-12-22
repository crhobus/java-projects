package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class PanelComponentes extends JPanel {

    private static final long serialVersionUID = 1L;

    public PanelComponentes(int x, int y, int w, int z) {
        this.setLayout(null);
        this.setBounds(x, y, w, z);
        this.setBackground(new Color(248, 248, 248));
        this.setBorder(BorderFactory.createEtchedBorder());
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

    public void addJLabelImg(String img, int x, int y, int w, int z) {
        Icon icon = new ImageIcon(img);
        JLabel label = new JLabel(icon);
        label.setBounds(x, y, w, z);
        this.add(label);
    }

    public JTextField addJTextField(int x, int y, int w, int z) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, w, z);
        this.add(textField);
        return textField;
    }

    public JTextField addJTextFieldNaoEdit(int x, int y, int w, int z) {
        JTextField textField = addJTextField(x, y, w, z);
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        return textField;
    }

    public JButton addJButton(String titulo, String toolTipText, int x, int y, int w, int z) {
        JButton button = new JButton(titulo);
        button.setBounds(x, y, w, z);
        button.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        button.setToolTipText(toolTipText);
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
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }

    public JButton addJButtonExcuir(int x, int y, int w, int z) {
        Icon icExcluir = new ImageIcon("Excluir.png");
        JButton button = addJButton("Excluir", "Excluir Registro", x, y, w, z);
        button.setIcon(icExcluir);
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }

    public JButton addJButtonSair(int x, int y, int w, int z) {
        Icon icSair = new ImageIcon("Sair.png");
        JButton button = addJButton("Sair", "Fechar", x, y, w, z);
        button.setIcon(icSair);
        return button;
    }

    public JButton addJButtonNovo(int x, int y, int w, int z) {
        Icon icNovo = new ImageIcon("Novo.png");
        JButton button = addJButton("Novo", "Novo", x, y, w, z);
        button.setIcon(icNovo);
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }

    public JFormattedTextField addJFormattedTextField(String mascara, int x, int y, int w, int z) throws ParseException {
        final JFormattedTextField formatted = new JFormattedTextField(new MaskFormatter(mascara));
        formatted.setBounds(x, y, w, z);
        formatted.setFocusLostBehavior(JFormattedTextField.COMMIT);
        formatted.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent evento) {
                if (formatted.getText().split(" ").length > 1 || formatted.getText().trim().length() < formatted.getText().length()) {
                    formatted.setText("");
                }
            }
        });
        this.add(formatted);
        return formatted;
    }

    public JComboBox addJComboBox(String[] itens, int x, int y, int w, int z) {
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Selecione");
        for (String s : itens) {
            comboBox.addItem(s);
        }
        comboBox.setBounds(x, y, w, z);
        this.add(comboBox);
        return comboBox;
    }

    public JSeparator addJSeparator(int horizontalVertical, int x, int y, int w, int z) {
        JSeparator separator = new JSeparator(horizontalVertical);
        separator.setBounds(x, y, w, z);
        this.add(separator);
        return separator;
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

    public JTextArea addJTextArea(int x, int y, int w, int z) {
        JTextArea textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createEtchedBorder());
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(x, y, w, z);
        this.add(scrollPane);
        return textArea;
    }
}
