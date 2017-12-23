package View.Endereco.Cidade;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import Control.Endereco.Cidade.CidadeControl;

public class TableModelCidade extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private CidadeControl controle;

    public TableModelCidade(CidadeControl controle) {
        this.controle = controle;
        try {
            controle.listarCidades();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeCidadeCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoTableModel(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "C�digo";
            case 1:
                return "Cidade";
            default:
                return "Estado";
        }
    }
}
