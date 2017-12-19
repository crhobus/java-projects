package FormatacaoCampos;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LetrasMinusculas extends PlainDocument {

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }
        char[] minusculo = str.toCharArray();
        for (int i = 0; i < minusculo.length; i++) {
            minusculo[i] = Character.toLowerCase(minusculo[i]);
        }
        super.insertString(offs, new String(minusculo), a);
    }
}
