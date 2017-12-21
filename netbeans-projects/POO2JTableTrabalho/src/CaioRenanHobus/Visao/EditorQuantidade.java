package CaioRenanHobus.Visao;

import javax.swing.*;

public class EditorQuantidade extends DefaultCellEditor {

    public EditorQuantidade() {
        super(new JTextField());
    }

    @Override
    public boolean stopCellEditing() {
        String s = (String) getCellEditorValue();
        boolean existe = false;
        try {
            double qtdade = Double.parseDouble(s);
            if (qtdade > 0) {
                existe = true;
                fireEditingStopped();
            }
        } catch (NumberFormatException e) {
        }
        return existe;
    }
}
