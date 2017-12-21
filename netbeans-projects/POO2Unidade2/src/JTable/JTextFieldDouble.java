package JTable;

import javax.swing.JTextField;

public class JTextFieldDouble {

    public void validaCampo(JTextField Numero) throws Exception {
        double valor;
        if (Numero.getText().length() != 0) {
            try {
                valor = Double.parseDouble(Numero.getText());
            } catch (NumberFormatException ex) {
                Numero.grabFocus();
                throw new Exception("Caracter inválido certifique se que o campo foi digitado corretamente");
            }
        }
    }
}
