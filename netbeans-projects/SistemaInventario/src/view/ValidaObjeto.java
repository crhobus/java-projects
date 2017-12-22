package view;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class ValidaObjeto {

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

    public void validaDouble(JTextField tf, String msgErro, String msgCondicao, int condicao) throws Exception {
        try {
            if (Double.parseDouble(tf.getText()) < condicao) {
                tf.grabFocus();
                throw new Exception(msgCondicao);
            }
        } catch (NumberFormatException ex) {
            tf.grabFocus();
            throw new Exception(msgErro);
        }
    }
}
