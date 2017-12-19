package Aula3;

import java.io.*;
import javax.swing.*;

public class FileNameEditor extends DefaultCellEditor {

    public FileNameEditor() {
        super(new JTextField());
    }

    @Override
    public boolean stopCellEditing() {
        File arq = new File(getCellEditorValue() + ".png");
        boolean existe = arq.exists();
        if (existe) {
            fireEditingStopped(); // notifica os listeners
            // for�a a chamada do setValueAt
        }
        return existe;
    }
}





