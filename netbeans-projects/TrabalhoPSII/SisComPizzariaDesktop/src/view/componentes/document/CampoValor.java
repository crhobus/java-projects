package view.componentes.document;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class CampoValor extends PlainDocument {

    private boolean comCasasDecimais;
    private boolean aceitaNegativo;
    private boolean keyPressed = true;

    public CampoValor(boolean comCasasDecimais, boolean aceitaNegativo) {
        this.comCasasDecimais = comCasasDecimais;
        this.aceitaNegativo = aceitaNegativo;
    }

    public void setKeyPressed(boolean keyPressed) {
        this.keyPressed = keyPressed;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (keyPressed) {
            String texto = getText(0, getLength());
            char c;
            for (int i = 0; i < str.length(); i++) {
                c = str.charAt(i);
                if (aceitaNegativo) {
                    if (offs == 0
                            && texto.contains("-")) {
                        return;
                    }
                    if (c == '-'
                            && offs == 0
                            && !texto.contains("-")) {
                        break;
                    }
                }
                if (comCasasDecimais
                        && c == ','
                        && offs != 0) {
                    if (Character.isDigit(texto.charAt(0))
                            && !texto.contains(",")) {
                        break;
                    } else if (aceitaNegativo
                            && texto.charAt(0) == '-'
                            && offs > 1
                            && !texto.contains(",")) {
                        break;
                    }
                }
                if (!Character.isDigit(c)) {
                    return;
                }
            }
            super.insertString(offs, str, a);
        } else {
            super.insertString(offs, str, a);
            keyPressed = true;
        }
    }
}
