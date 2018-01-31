package Formatacao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public abstract class ObjGraficos extends JDialog {

    private static final long serialVersionUID = 4559229079950671053L;

    public JPanel getJPanelLineBorder(int x, int y, int w, int z) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, w, z);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        return panel;
    }

    public JPanel getJPanelTitledBorder(String titulo, int x, int y, int w, int z) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(x, y, w, z);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createTitledBorder(titulo));
        return panel;
    }

    public JLabel getJLabel(String s, int x, int y, int w, int z) {
        JLabel label = new JLabel(s);
        label.setBounds(x, y, w, z);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        return label;
    }

    public JTextField getJTextField(int x, int y, int w, int z) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, w, z);
        return textField;
    }

    public JButton getJButton(String s, int x, int y, int w, int z) {
        JButton button = new JButton(s);
        button.setBounds(x, y, w, z);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBackground(new Color(238, 207, 161));
        return button;
    }

    public JComboBox getJComboBox(List<String> itens, int x, int y, int w, int z) {
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

    public JFormattedTextField getJFormattedTextField(final String formato, int x, int y, int w, int z) throws ParseException {
        final JFormattedTextField formatted = new JFormattedTextField(new MaskFormatter(formato));
        formatted.setBounds(x, y, w, z);
        formatted.setFocusLostBehavior(JFormattedTextField.COMMIT);
        formatted.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent evento) {
                if (formatted.getText().split(" ").length > 1 || formatted.getText().trim().length() < formato.length()) {
                    formatted.setText("");
                }
            }
        });
        return formatted;
    }

    public JTextArea getJTextArea(JPanel panel, int x, int y, int w, int z) {
        JTextArea textArea = new JTextArea();
        textArea.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBounds(x, y, w, z);
        panel.add(scroll);
        return textArea;
    }

    public JRadioButton getJRadioButton(String s, int x, int y, int w, int z) {
        JRadioButton radioButton = new JRadioButton(s);
        radioButton.setBounds(x, y, w, z);
        radioButton.setFont(new Font("Arial", Font.BOLD, 12));
        return radioButton;
    }

    public JCheckBox getJCheckBox(int x, int y, int w, int z) {
        JCheckBox check = new JCheckBox();
        check.setBackground(new Color(248, 248, 248));
        check.setBounds(x, y, w, z);
        return check;
    }

    public JPasswordField getJPasswordField(int x, int y, int w, int z) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, w, z);
        return passwordField;
    }

    //Verificação de campos ao cadastrar
    public Date verificaDate(JFormattedTextField ftf, String nome) throws Exception {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        try {
            if ("  /  /    ".equals(ftf.getText())) {
                return formatDate.parse("01/01/2100");
            } else {
                return formatDate.parse(ftf.getText());
            }
        } catch (Exception ex) {
            ftf.grabFocus();
            throw new Exception("Campo " + nome + " inválido");
        }
    }

    public String recuperaCampoDate(Date data) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        if ("01/01/2100".equals(formatDate.format(data))) {
            return "";
        } else {
            return formatDate.format(data);
        }
    }

    public int verificaInt(JTextField tf, String nome) throws Exception {
        try {
            if (!"".equals(tf.getText())) {
                if (Integer.parseInt(tf.getText()) < 0) {
                    tf.grabFocus();
                    throw new Exception("Campo " + nome + " inválido");
                } else {
                    return Integer.parseInt(tf.getText());
                }
            } else {
                return 0;
            }
        } catch (Exception ex) {
            tf.grabFocus();
            throw new Exception("Campo " + nome + " inválido");
        }
    }

    public String recuperaCampoStr(int num) {
        if (num != 0) {
            return Integer.toString(num);
        }
        return null;
    }

    public int verificaDigitoConta(JFormattedTextField ftf) throws Exception {
        if (!" ".equals(ftf.getText())) {
            return Integer.parseInt(ftf.getText());
        } else {
            return 0;
        }
    }

    public int verificaConta(JFormattedTextField ftf) throws Exception {
        if (!"       ".equals(ftf.getText())) {
            return Integer.parseInt(ftf.getText());
        } else {
            return 0;
        }
    }

    public int verificaContaFGTS(JFormattedTextField ftf) throws Exception {
        if (!"      ".equals(ftf.getText())) {
            return Integer.parseInt(ftf.getText());
        } else {
            return 0;
        }
    }

    public int verificaZona(JFormattedTextField ftf) throws Exception {
        if (!"   ".equals(ftf.getText())) {
            return Integer.parseInt(ftf.getText());
        } else {
            return 0;
        }
    }

    public int verificaSecao(JFormattedTextField ftf) throws Exception {
        if (!"    ".equals(ftf.getText())) {
            return Integer.parseInt(ftf.getText());
        } else {
            return 0;
        }
    }

    public double verificaDouble(JTextField tf, String nome) throws Exception {
        try {
            if (!"".equals(tf.getText())) {
                if (Double.parseDouble(tf.getText()) < 0) {
                    tf.grabFocus();
                    throw new Exception("Campo " + nome + " inválido");
                } else {
                    return Double.parseDouble(tf.getText());
                }
            } else {
                return -1;
            }
        } catch (Exception ex) {
            tf.grabFocus();
            throw new Exception("Campo " + nome + " inválido");
        }
    }

    public String recuperaCampoStr(double num) {
        if (num != -1) {
            return Double.toString(num);
        }
        return null;
    }
}
