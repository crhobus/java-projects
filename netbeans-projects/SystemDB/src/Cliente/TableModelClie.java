package Cliente;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelClie extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private ClienteControl controle;

    public TableModelClie(ClienteControl controle) {
        this.controle = controle;
        try {
            controle.listarClie();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 12;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeClieCadas();
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
                return "Nome";
            case 2:
                return "Tipo Pessoa";
            case 3:
                return "CPF / CNPJ";
            case 4:
                return "RG / IE";
            case 5:
                return "Endereço";
            case 6:
                return "Bairro";
            case 7:
                return "Número";
            case 8:
                return "CEP";
            case 9:
                return "Cidade";
            case 10:
                return "Estado";
            default:
                return "Fone";
        }
    }
}
