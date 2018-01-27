package Produto;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelProduto extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private ProdutoControl controle;

    public TableModelProduto(ProdutoControl controle) {
        this.controle = controle;
        try {
            controle.listarProd();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public int getRowCount() {
        return controle.getQtdadeProdCadas();
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
                return "Produto";
            case 2:
                return "Marca";
            case 3:
                return "Modelo";
            case 4:
                return "Descrição";
            case 5:
                return "Identificação";
            case 6:
                return "Quantidade";
            case 7:
                return "Preço Unit. Venda";
            default:
                return "Valor Total";
        }
    }
}
