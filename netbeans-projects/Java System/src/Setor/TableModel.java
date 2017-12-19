package Setor;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private SetorControl controle;

    public TableModel(SetorControl controle) {
        this.controle = controle;
        try {
            this.controle.listaTodos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeSetorCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoTableModel(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Codigo";
            default:
                return "Nome";
        }
    }
}
