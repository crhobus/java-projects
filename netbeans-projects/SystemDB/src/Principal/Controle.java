package Principal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;

public class Controle extends JDialog {

    private static final long serialVersionUID = 1L;

    public String calculoIdade(JFormattedTextField ftf, SimpleDateFormat formatDate) {
        if (!"  /  /    ".equals(ftf.getText())) {
            try {
                Calendar calendar = Calendar.getInstance();

                calendar.setTime(new Date());
                int dia1 = calendar.get(Calendar.DAY_OF_YEAR);
                int ano1 = calendar.get(Calendar.YEAR);

                calendar.setTime(formatDate.parse(ftf.getText()));
                int dia2 = calendar.get(Calendar.DAY_OF_YEAR);
                int ano2 = calendar.get(Calendar.YEAR);

                int idade = ano1 - ano2;

                if (dia1 < dia2) {
                    idade--; // Ainda não completou aniversario esse ano.
                }
                return Integer.toString(idade);
            } catch (ParseException ex) {
                return "";
            }
        }
        return "";
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

    public void validaJFormattedTextField(JFormattedTextField ftf) {
        if (ftf.getText().split(" ").length > 1 || ftf.getText().trim().length() < ftf.getText().length()) {
            ftf.setText("");
        }
    }

    public boolean validaHorario(JFormattedTextField ftf) {
        if (!"  :  ".equals(ftf.getText())) {
            int hora = Integer.parseInt(ftf.getText().substring(0, 2));
            int minuto = Integer.parseInt(ftf.getText().substring(3, 5));
            if (hora >= 0 && hora <= 23) {
                if (minuto >= 0 && minuto <= 59) {
                    return true;
                }
            }
            return false;
        }
        return false;
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

    public void validaInteger(JTextField tf, String msgErro, String msgCondicao, int condicao) throws Exception {
        try {
            if (Integer.parseInt(tf.getText()) < condicao) {
                tf.grabFocus();
                throw new Exception(msgCondicao);
            }
        } catch (NumberFormatException ex) {
            tf.grabFocus();
            throw new Exception(msgErro);
        }
    }
}
