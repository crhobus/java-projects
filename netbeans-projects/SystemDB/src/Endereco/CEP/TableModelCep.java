package Endereco.CEP;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelCep extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private CepControl controle;

    public TableModelCep(CepControl controle) {
        this.controle = controle;
        try {
            controle.listarCeps();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeCepCadas();
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoTableModel(linha, coluna);
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Código";
            case 1:
                return "CEP";
            case 2:
                return "Cidade";
            case 3:
                return "Estado";
            case 4:
                return "Região";
            default:
                return "País";
        }
    }
}
