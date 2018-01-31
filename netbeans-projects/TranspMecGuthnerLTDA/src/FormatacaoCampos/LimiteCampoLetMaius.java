package FormatacaoCampos;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimiteCampoLetMaius extends PlainDocument {

    private int max;

    public LimiteCampoLetMaius(int max) {
        this.max = max;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        int avail = max - getLength();
        if (avail > 0) {
            String insert = str;
            if (str.length() > avail) {
                insert = str.substring(0, avail);
            }
            super.insertString(offs, insert.toUpperCase(), a);
        }
    }
}