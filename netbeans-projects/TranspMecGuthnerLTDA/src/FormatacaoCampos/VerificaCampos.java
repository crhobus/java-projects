package FormatacaoCampos;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class VerificaCampos {

    public static int verificaInt(JTextField tf, String nome) throws Exception {
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

    public static Date verificaDate(JFormattedTextField ftf, String nome) throws Exception {
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

    public static String recuperaCampoDate(Date data) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
        if ("01/01/2100".equals(formatDate.format(data))) {
            return "";
        } else {
            return formatDate.format(data);
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

    public static int verificaContaFGTS(JFormattedTextField ftf)
            throws Exception {
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

    public static String recuperaCampoStr(double num) {
        if (num != 0) {
            return Double.toString(num);
        }
        return null;
    }
}
