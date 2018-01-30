package Principal;

import javax.swing.table.AbstractTableModel;

import LogTela.VariaveisOrdTabela;

public class TableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private VariaveisOrdTabela controleTabela;

    public TableModel(VariaveisOrdTabela controle) {
        this.controleTabela = controle;//containner da tabela
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return controleTabela.getTamanhoLista();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controleTabela.conteudoTableModel(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Variável";
            case 1:
                return "Expressão";
            default:
                return "Resultado";
        }
    }
}
