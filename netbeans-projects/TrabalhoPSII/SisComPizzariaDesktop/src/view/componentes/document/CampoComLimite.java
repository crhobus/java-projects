package view.componentes.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CampoComLimite extends PlainDocument {

    private int max;

    public CampoComLimite(int max) {
        this.max = max;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if ((max - getLength()) > 0) {
            super.insertString(offs, str, a);
        }
    }
}
