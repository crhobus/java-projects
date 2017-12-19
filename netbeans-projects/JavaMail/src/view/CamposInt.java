package view;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//Classe que permite que apenas números sejam digitados num determinado campo
public class CamposInt extends PlainDocument {

    private static final long serialVersionUID = -6821774421402909062L;

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        char digito;
        boolean numerico = true;
        for (int i = 0; i < str.length(); i++) {
            digito = str.charAt(i);
            if (!Character.isDigit(digito)) {
                numerico = false;
                break;
            }
        }
        if (numerico) {
            super.insertString(offs, str, a);
        }
    }
}
