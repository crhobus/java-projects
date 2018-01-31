package Visao.Produto;

import Controle.ProdutoControl;
import javax.swing.table.*;

public class TableModelProd extends AbstractTableModel {

    private ProdutoControl controle;

    public TableModelProd(ProdutoControl controle) {
        this.controle = controle;
    }

    public int getRowCount() {
        return controle.tamanho();
    }

    public int getColumnCount() {
        return 6;
    }

    public Object getValueAt(int linha, int coluna) {
        return controle.conteudoLinha(linha, coluna);
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
                return "Num. Série";
            case 4:
                return "Modelo";
            default:
                return "Valor";
        }
    }
}
