package Clientes;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

    private ClienteControl controle;

    public TableModel(ClienteControl controle) {
        this.controle = controle;
        try {
            this.controle.listaTodos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 14;
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
                return "Codigo";
            case 1:
                return "Nome / Razão Social";
            case 2:
                return "Tipo Pessoa";
            case 3:
                return "CPF / CNPJ";
            case 4:
                return "RG";
            case 5:
                return "IE";
            case 6:
                return "Endereço";
            case 7:
                return "Bairro";
            case 8:
                return "Número";
            case 9:
                return "Cidade";
            case 10:
                return "UF";
            case 11:
                return "Fone";
            case 12:
                return "Celular";
            default:
                return "E-Mail";
        }
    }
}
