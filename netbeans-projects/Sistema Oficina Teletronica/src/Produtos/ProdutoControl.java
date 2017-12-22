package Produtos;

import java.sql.Connection;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import Modelo.Produto;

public class ProdutoControl {

    private List<Produto> listaProdutos;
    private ProdutoDAO produtoDAO;

    public ProdutoControl(Connection con) {
        produtoDAO = new ProdutoDAO(con);
    }

    public boolean insertProduto(Produto produto) throws Exception {
        return produtoDAO.insertProduto(produto);
    }

    public boolean updateProduto(Produto produto) throws Exception {
        return produtoDAO.updateProduto(produto);
    }

    public Produto selectProduto(String nome) throws Exception {
        return produtoDAO.selectProduto(nome);
    }

    public boolean deleteProduto(String nome) throws Exception {
        return produtoDAO.deleteProduto(nome);
    }

    public boolean isProdVazio() throws Exception {
        return produtoDAO.isProdVazio();
    }

    public int getProxCodProd() throws Exception {
        return produtoDAO.getProxCodProd();
    }

    public int getQtdadeProdCadas() {
        return listaProdutos.size();
    }

    public Object conteudoTableModel(int linha, int coluna) {
        Produto produto = listaProdutos.get(linha);
        switch (coluna) {
            case 0:
                return produto.getCodigo();
            case 1:
                return produto.getNome();
            case 2:
                return produto.getDescricao();
            case 3:
                return produto.getMarca();
            case 4:
                return produto.getModelo();
            case 5:
                return produto.getCor();
            case 6:
                return produto.getNumeroSerie();
            case 7:
                return produto.getMesGarantia();
            case 8:
                return produto.getQuantidade();
            case 9:
                return NumberFormat.getCurrencyInstance().format(produto.getPrecoUnitCompra());
            case 10:
                return NumberFormat.getCurrencyInstance().format(produto.getPrecoUnitVenda());
            default:
                return NumberFormat.getCurrencyInstance().format(produto.getValorTotal());
        }
    }

    public void listarProdutos() throws Exception {
        listaProdutos = produtoDAO.listProdutos();
    }

    public void limparLista() {
        listaProdutos.clear();
    }

    public Produto getListaPosicao(int posicao) {
        return listaProdutos.get(posicao);
    }

    public void getListaCod(int codigo) {
        for (Produto produto : listaProdutos) {
            if (codigo == produto.getCodigo()) {
                listaProdutos.clear();
                listaProdutos.add(produto);
                return;
            }
        }
    }

    public void getListaNome(String nome) {
        for (Produto produto : listaProdutos) {
            if (nome.equalsIgnoreCase(produto.getNome())) {
                listaProdutos.clear();
                listaProdutos.add(produto);
                return;
            }
        }
    }

    public void getListaDescr(String descricao) {
        List<Produto> listaAux = new ArrayList<Produto>();
        for (Produto produto : listaProdutos) {
            if (descricao.equalsIgnoreCase(produto.getDescricao())) {
                listaAux.add(produto);
            }
        }
        if (!listaAux.isEmpty()) {
            listaProdutos.clear();
            listaProdutos = listaAux;
        }
    }

    public void getListaMarca(String marca) {
        List<Produto> listaAux = new ArrayList<Produto>();
        for (Produto produto : listaProdutos) {
            if (marca.equalsIgnoreCase(produto.getMarca())) {
                listaAux.add(produto);
            }
        }
        if (!listaAux.isEmpty()) {
            listaProdutos.clear();
            listaProdutos = listaAux;
        }
    }

    public void getListaMod(String modelo) {
        List<Produto> listaAux = new ArrayList<Produto>();
        for (Produto produto : listaProdutos) {
            if (modelo.equalsIgnoreCase(produto.getModelo())) {
                listaAux.add(produto);
            }
        }
        if (!listaAux.isEmpty()) {
            listaProdutos.clear();
            listaProdutos = listaAux;
        }
    }

    public void getListaCor(String cor) {
        List<Produto> listaAux = new ArrayList<Produto>();
        for (Produto produto : listaProdutos) {
            if (cor.equalsIgnoreCase(produto.getCor())) {
                listaAux.add(produto);
            }
        }
        if (!listaAux.isEmpty()) {
            listaProdutos.clear();
            listaProdutos = listaAux;
        }
    }

    public void getListaNum(int numeroSerie) {
        List<Produto> listaAux = new ArrayList<Produto>();
        for (Produto produto : listaProdutos) {
            if (numeroSerie == produto.getNumeroSerie()) {
                listaAux.add(produto);
            }
        }
        if (!listaAux.isEmpty()) {
            listaProdutos.clear();
            listaProdutos = listaAux;
        }
    }

    public void getListaMesGar(int mesGarantia) {
        List<Produto> listaAux = new ArrayList<Produto>();
        for (Produto produto : listaProdutos) {
            if (mesGarantia == produto.getMesGarantia()) {
                listaAux.add(produto);
            }
        }
        if (!listaAux.isEmpty()) {
            listaProdutos.clear();
            listaProdutos = listaAux;
        }
    }

    public void getListaQt(int quantidade) {
        List<Produto> listaAux = new ArrayList<Produto>();
        for (Produto produto : listaProdutos) {
            if (quantidade == produto.getQuantidade()) {
                listaAux.add(produto);
            }
        }
        if (!listaAux.isEmpty()) {
            listaProdutos.clear();
            listaProdutos = listaAux;
        }
    }

    public void getListaPrCom(double precoCompra) {
        List<Produto> listaAux = new ArrayList<Produto>();
        for (Produto produto : listaProdutos) {
            if (precoCompra == produto.getPrecoUnitCompra()) {
                listaAux.add(produto);
            }
        }
        if (!listaAux.isEmpty()) {
            listaProdutos.clear();
            listaProdutos = listaAux;
        }
    }

    public void getListaPrVen(double precoVenda) {
        List<Produto> listaAux = new ArrayList<Produto>();
        for (Produto produto : listaProdutos) {
            if (precoVenda == produto.getPrecoUnitVenda()) {
                listaAux.add(produto);
            }
        }
        if (!listaAux.isEmpty()) {
            listaProdutos.clear();
            listaProdutos = listaAux;
        }
    }

    public void getListaValTot(double valorTotal) {
        List<Produto> listaAux = new ArrayList<Produto>();
        for (Produto produto : listaProdutos) {
            if (valorTotal == produto.getValorTotal()) {
                listaAux.add(produto);
            }
        }
        if (!listaAux.isEmpty()) {
            listaProdutos.clear();
            listaProdutos = listaAux;
        }
    }
}
