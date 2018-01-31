package view.desktop.produto;

import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import model.entidades.Cliente;
import model.entidades.Produto;

import view.componentes.table.TableModel;

public class TableModelProduto extends TableModel {

    private List<Produto> produtos;
    private int index;

    public TableModelProduto(CadasProduto produtoSis) {
        try {
            this.produtos = produtoSis.getServidor().getProdutoAction().getProdutos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Servidor", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 9;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Produto produto = produtos.get(linha);
        switch (coluna) {
            case 0:
                return produto.getCdProduto();
            case 1:
                return produto.getDsProduto();
            case 2:
                return produto.getVlEstoque();
            case 3:
                return produto.getVlProduto();
            case 4:
                return produto.getDsMarca();
            case 5:
                return produto.getDsTipo();
            case 6:
                return produto.getDsObservacao();
            case 7:
                return produto.getDtValidade();
            default:
                return produto.getDtCadastro();
        }
    }

    @Override
    public String getColumnName(int coluna) {
        switch (coluna) {
            case 0:
                return "Produto";
            case 1:
                return "Descrição";
            case 2:
                return "Estoque";
            case 3:
                return "Preço";
            case 4:
                return "Marca";
            case 5:
                return "Tipo";
            case 6:
                return "Observação";
            case 7:
                return "Validade";
            default:
                return "Data Cadastro";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return Double.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            case 6:
                return String.class;
            case 7:
                return Date.class;
            default:
                return Date.class;
        }
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
        index = produtos.size() - 1;
    }

    public Produto getProduto(int linha) {
        index = linha;
        return produtos.get(linha);
    }

    public Produto removerProduto(int linha) {
        index = linha;
        return produtos.remove(linha);
    }

    public Produto removerProduto(Produto produto) {
        Produto pro;
        for (int i = 0; i < produtos.size(); i++) {
            pro = produtos.get(i);
            if (pro.getCdProduto() == produto.getCdProduto()) {
                produtos.remove(pro);
                index = i;
                return pro;
            }
        }
        return null;
    }

    public boolean possuiRegistros() {
        return !produtos.isEmpty();
    }

    public void atualizaTabela() {
        fireTableDataChanged();
    }

    public int getLinhaSelecionada() {
        if (index > (produtos.size() - 1)) {
            index--;
            if (index < 0) {
                index = 0;
            }
            return index;
        }
        return index;
    }
}
