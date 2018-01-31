package view.componentes.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CampoMaiusculoMinusculo extends PlainDocument {

    private boolean maiusculo;

    public CampoMaiusculoMinusculo(boolean maiusculo) {
        this.maiusculo = maiusculo;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }
        char[] letra = str.toCharArray();
        for (int i = 0; i < letra.length; i++) {
            if (maiusculo) {
                letra[i] = Character.toUpperCase(letra[i]);
            } else {
                letra[i] = Character.toLowerCase(letra[i]);
            }
        }
        super.insertString(offs, new String(letra), a);
    }
}
