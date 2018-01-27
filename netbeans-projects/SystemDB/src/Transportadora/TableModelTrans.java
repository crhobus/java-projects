package Transportadora;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelTrans extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private TransportadoraControl controle;

    public TableModelTrans(TransportadoraControl controle) {
        this.controle = controle;
        try {
            controle.listarTrans();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 14;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeTransCadas();
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
                return "CNPJ";
            case 3:
                return "IE";
            case 4:
                return "IM";
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
            case 11:
                return "E-Mail";
            case 12:
                return "Fone";
            default:
                return "Frete";
        }
    }
}
