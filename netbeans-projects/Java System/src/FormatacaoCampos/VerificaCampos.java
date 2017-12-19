package FormatacaoCampos;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class VerificaCampos {

    public static int verificaInt(JTextField tf, String nome) throws Exception {
        try {
            if (!"".equals(tf.getText())) {
                if (Integer.parseInt(tf.getText()) <= 0) {
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

    public static double verificaDouble(JTextField tf, String nome) throws Exception {
        try {
            if (!"".equals(tf.getText())) {
                if (Double.parseDouble(tf.getText()) < 0) {
                    tf.grabFocus();
                    throw new Exception("Campo " + nome + " inválido");
                } else {
                    return Double.parseDouble(tf.getText());
                }
            } else {
                return 0;
            }
        } catch (Exception ex) {
            tf.grabFocus();
            throw new Exception("Campo " + nome + " inválido");
        }
    }

    public static int verificaDigitoConta(JFormattedTextField ftf) throws Exception {
        if (!" ".equals(ftf.getText())) {
            return Integer.parseInt(ftf.getText());
        } else {
            return 0;
        }
    }

    public static int verificaConta(JFormattedTextField ftf) throws Exception {
        if (!"       ".equals(ftf.getText())) {
            return Integer.parseInt(ftf.getText());
        } else {
            return 0;
        }
    }

    public static int verificaContaFGTS(JFormattedTextField ftf) throws Exception {
        if (!"      ".equals(ftf.getText())) {
            return Integer.parseInt(ftf.getText());
        } else {
            return 0;
        }
    }

    public static int verificaZona(JFormattedTextField ftf) throws Exception {
        if (!"   ".equals(ftf.getText())) {
            return Integer.parseInt(ftf.getText());
        } else {
            return 0;
        }
    }

    public static int verificaSecao(JFormattedTextField ftf) throws Exception {
        if (!"    ".equals(ftf.getText())) {
            return Integer.parseInt(ftf.getText());
        } else {
            return 0;
        }
    }

    public static String recuperaCampoStr(int num) {
        if (num != 0) {
            return Integer.toString(num);
        }
        return null;
    }
}
