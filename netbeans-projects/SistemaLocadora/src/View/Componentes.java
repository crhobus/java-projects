package View;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public abstract class Componentes extends JDialog implements ActionListener, FocusListener {

    private static final long serialVersionUID = 1L;

    public JPanel getJPanel(int x, int y, int j, int z) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, j, z);
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEtchedBorder());
        return panel;
    }

    public JTextField getJTextField(int x, int y, int j, int z) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, j, z);
        textField.addFocusListener(this);
        return textField;
    }

    public JTextField getJTextFieldNaoEdit(int x, int y, int j, int z) {
        JTextField textField = getJTextField(x, y, j, z);
        textField.setEditable(false);
        return textField;
    }

    public JLabel getJLabel(String titulo, int x, int y, int j, int z) {
        JLabel label = new JLabel(titulo);
        label.setBounds(x, y, j, z);
        return label;
    }

    public JLabel getJLabelVermelho(int x, int y, int j, int z) {
        JLabel label = new JLabel();
        label.setBounds(x, y, j, z);
        label.setForeground(Color.RED);
        return label;
    }

    private JButton getJButton(String toolTipText, int x, int y, int j, int z) {
        JButton button = new JButton();
        button.setBounds(x, y, j, z);
        button.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        button.setToolTipText(toolTipText);
        button.addActionListener(this);
        return button;
    }

    public JButton getJButton(String titulo, String toolTipText, int x, int y, int j, int z) {
        JButton button = getJButton(toolTipText, x, y, j, z);
        button.setText(titulo);
        return button;
    }

    private JButton getJButtonImg(String titulo, Icon icon, String toolTipText, int x, int y, int j, int z) {
        JButton button = getJButton(toolTipText, x, y, j, z);
        button.setText(titulo);
        button.setIcon(icon);
        return button;
    }

    public JButton getJButtonOK(String toolTipText, int x, int y, int j, int z) {
        return getJButtonImg("OK", new ImageIcon("OK.png"), toolTipText, x, y, j, z);
    }

    public JButton getJButtonNovo(String toolTipText, int x, int y, int j, int z) {
        return getJButtonImg("Novo", new ImageIcon("Novo.png"), toolTipText, x, y, j, z);
    }

    public JButton getJButtonSair(String toolTipText, int x, int y, int j, int z) {
        return getJButtonImg("Sair", new ImageIcon("Sair.png"), toolTipText, x, y, j, z);
    }

    public JButton getJButtonCancelar(String toolTipText, int x, int y, int j, int z) {
        return getJButtonImg("Cancelar", new ImageIcon("Cancelar.png"), toolTipText, x, y, j, z);
    }

    public JButton getJButtonExcluir(String toolTipText, int x, int y, int j, int z) {
        return getJButtonImg("Excluir", new ImageIcon("Excluir.png"), toolTipText, x, y, j, z);
    }

    public JSeparator getJSeparator(int x, int y, int j, int z) {
        JSeparator separator = new JSeparator();
        separator.setBounds(x, y, j, z);
        return separator;
    }

    public JFormattedTextField getJFormattedTextField(String mascara, int x, int y, int j, int z) {
        try {
            final JFormattedTextField ftf = new JFormattedTextField(new MaskFormatter(mascara));
            ftf.setBounds(x, y, j, z);
            ftf.setFocusLostBehavior(JFormattedTextField.COMMIT);
            ftf.addFocusListener(this);
            ftf.addFocusListener(new FocusAdapter() {

                @Override
                public void focusLost(FocusEvent evento) {
                    if (ftf.getText().split(" ").length > 1 || ftf.getText().trim().length() < ftf.getText().length()) {
                        ftf.setText("");
                    }
                }
            });
            return ftf;
        } catch (ParseException ex) {
        }
        return null;
    }

    public JComboBox getJComboBox(int x, int y, int j, int z) {
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(x, y, j, z);
        comboBox.addFocusListener(this);
        return comboBox;
    }

    public boolean validaData(JFormattedTextField ftf) {
        if (!"  /  /    ".equals(ftf.getText())) {
            int dia = Integer.parseInt(ftf.getText().substring(0, 2));
            int mes = Integer.parseInt(ftf.getText().substring(3, 5));
            int ano = Integer.parseInt(ftf.getText().substring(6, 10));
            if (dia >= 1 && dia <= 31 && mes >= 1 && mes <= 12 && ano >= 1900 && ano < 2100) {
                return true;
            }
            return false;
        }
        return true;
    }
}
