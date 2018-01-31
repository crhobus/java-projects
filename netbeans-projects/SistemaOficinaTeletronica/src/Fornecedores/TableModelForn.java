package Fornecedores;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelForn extends AbstractTableModel {

    private static final long serialVersionUID = 2111976392754980873L;
    private FornecedorControl controle;

    public TableModelForn(FornecedorControl controle) {
        this.controle = controle;
        try {
            this.controle.listarFornecedores();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 18;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeFornCadas();
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
                return "Nome";
            case 2:
                return "Nome Fantasia";
            case 3:
                return "Sigla";
            case 4:
                return "Endereço";
            case 5:
                return "Bairro";
            case 6:
                return "Número";
            case 7:
                return "Cidade";
            case 8:
                return "UF";
            case 9:
                return "Fone";
            case 10:
                return "Celular";
            case 11:
                return "CNPJ";
            case 12:
                return "IE";
            case 13:
                return "E-Mail";
            case 14:
                return "Compra Mínima";
            case 15:
                return "Compra Máxima";
            case 16:
                return "Valor Frete";
            default:
                return "Juros a.m.";
        }
    }
}
