package CaioRenanHobus.Visao;

import CaioRenanHobus.Controle.*;
import javax.swing.*;

public class EditorNome extends DefaultCellEditor {

    private ProdutoControl controle;

    public EditorNome(ProdutoControl controle) {
        super(new JTextField());
        this.controle = controle;
    }

    @Override
    public boolean stopCellEditing() {
        String nome = (String) getCellEditorValue();
        if ("".equals(nome)) {
            return false;
        }
        boolean existe = controle.existe(nome);
        if (existe == true) {
            JOptionPane.showMessageDialog(null, "Produto ja cadastrado", "Erro", JOptionPane.ERROR_MESSAGE);
            return !existe;
        } else {
            fireEditingStopped();
            return existe;
        }
    }
}
