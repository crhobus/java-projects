package FormatacaoCampos;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LetrasMaiusculas extends PlainDocument {

    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }
        char[] maiusculo = str.toCharArray();
        for (int i = 0; i < maiusculo.length; i++) {
            maiusculo[i] = Character.toUpperCase(maiusculo[i]);
        }
        super.insertString(offs, new String(maiusculo), a);
    }
}
