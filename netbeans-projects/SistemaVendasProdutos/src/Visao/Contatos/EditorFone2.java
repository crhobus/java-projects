package Visao.Contatos;

import java.awt.*;
import java.text.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;

public class EditorFone2 extends AbstractCellEditor implements TableCellEditor {

    private JFormattedTextField ftfCampo;
    private JPanel painel;

    public EditorFone2() throws ParseException {
        painel = new JPanel(new GridLayout());
        ftfCampo = new JFormattedTextField(new MaskFormatter("(##)####-####"));
        painel.add(ftfCampo);

    }

    public Object getCellEditorValue() {
        return ftfCampo.getText();
    }

    public Component getTableCellEditorComponent(JTable tabela, Object valor, boolean selecionado, int linha, int coluna) {
        ftfCampo.setText("");
        return painel;
    }

    @Override
    public boolean stopCellEditing() {
        fireEditingStopped();
        return false;
    }
}
