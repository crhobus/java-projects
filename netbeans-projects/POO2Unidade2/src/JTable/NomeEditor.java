package JTable;

import java.io.*;
import javax.swing.*;

public class NomeEditor extends DefaultCellEditor {

    public NomeEditor() {
        super(new JTextField());
    }

    @Override
    public boolean stopCellEditing() {
        File arq = new File(getCellEditorValue() + ".jpg");
        boolean existe = arq.exists();
        if (existe) {
            fireEditingStopped();
        }
        return existe;
    }
}
