package AssistenciaTecRandom;

import javax.swing.text.*;

public class LimiteCampo extends PlainDocument {

    private int max;

    public LimiteCampo(int max) {
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
            super.insertString(offs, insert, a);
        }
    }
}
