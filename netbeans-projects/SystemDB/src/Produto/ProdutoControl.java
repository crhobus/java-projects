package Produto;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;

import Modelo.Produto;

public class ProdutoControl {

    private List<Produto> listaProd;
    private ProdutoDAO produtoDAO;

    public ProdutoControl(Connection con) {
        produtoDAO = new ProdutoDAO(con);
    }

    public boolean insertProduto(Produto produto) throws SQLException {
        return produtoDAO.insertProduto(produto);
    }

    public boolean updateProduto(Produto produto) throws SQLException {
        return produtoDAO.updateProduto(produto);
    }

    public int getQtdadeProd(int codProd) throws SQLException {
        return produtoDAO.getQtdadeProd(codProd);
    }

    public void updateQtdadeProduto(int codProd, int novaQtdade) throws SQLException {
        produtoDAO.updateQtdadeProduto(codProd, novaQtdade);
    }

    public boolean isProdutoCadastrado(int codProd) throws SQLException {
        return produtoDAO.isProdutoCadastrado(codProd);
    }

    public int getProdCadastrado(String nomeProduto, String marca, String modelo) throws SQLException {
        return produtoDAO.getProdCadastrado(nomeProduto, marca, modelo);
    }

    public int getProdCadastrado(String identificacao) throws SQLException {
        return produtoDAO.getProdCadastrado(identificacao);
    }

    public boolean isProdVazio() throws SQLException {
        return produtoDAO.isProdVazio();
    }

    public int getProxCodProd() throws SQLException {
        return produtoDAO.getProxCodProd();
    }

    public boolean deleteProd(int codProd) throws SQLException {
        return produtoDAO.deleteProd(codProd);
    }

    public int getQtdadeProdCadas() {
        return listaProd.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Produto produto = listaProd.get(linha);
        double precoUnitCom = produto.getPrecoCompra();
        precoUnitCom += precoUnitCom * (produto.getPercentualLucro() / 100);
        precoUnitCom += precoUnitCom * (produto.getIpi() / 100);
        precoUnitCom -= precoUnitCom * (produto.getDescontos() / 100);
        String precoUnitVenda = NumberFormat.getCurrencyInstance().format(precoUnitCom);
        String valorTotal = NumberFormat.getCurrencyInstance().format(precoUnitCom * produto.getQuantidade());
        switch (coluna) {
            case 0:
                return produto.getCodProduto();
            case 1:
                return produto.getProduto();
            case 2:
                return produto.getMarca();
            case 3:
                return produto.getModelo();
            case 4:
                return produto.getDescricao();
            case 5:
                return produto.getIdentificacao();
            case 6:
                return produto.getQuantidade();
            case 7:
                return precoUnitVenda;
            default:
                return valorTotal;
        }
    }

    public void limparLista() {
        listaProd.clear();
    }

    public void listarProd() throws SQLException {
        listaProd = produtoDAO.listProd();
    }

    public void getLista(final int coluna, final String s, final int n) throws SQLException {
        listaProd = produtoDAO.getLista(coluna, s, n);
    }

    public Produto getListaPosicao(int posicao) {
        return listaProd.get(posicao);
    }
}
