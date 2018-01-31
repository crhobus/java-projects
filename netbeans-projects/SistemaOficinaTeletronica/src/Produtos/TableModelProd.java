package Produtos;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TableModelProd extends AbstractTableModel {

    private static final long serialVersionUID = -7234447070047510210L;
    private ProdutoControl controle;

    public TableModelProd(ProdutoControl controle) {
        this.controle = controle;
        try {
            this.controle.listarProdutos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getColumnCount() {
        return 12;
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
                return "Codigo";
            case 1:
                return "Nome";
            case 2:
                return "Descrição";
            case 3:
                return "Marca";
            case 4:
                return "Modelo";
            case 5:
                return "Cor";
            case 6:
                return "Número Série";
            case 7:
                return "Meses Garantia";
            case 8:
                return "Quantidade";
            case 9:
                return "Preço Unit. Compra";
            case 10:
                return "Preço Unit. Venda";
            default:
                return "Valor Total";
        }
    }
}
