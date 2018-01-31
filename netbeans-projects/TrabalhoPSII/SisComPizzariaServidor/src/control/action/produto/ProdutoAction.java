package control.action.produto;

import control.funcoes.Dados;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.dao.ProdutoDAO;
import model.dao.ServidorDAO;
import model.entidades.Produto;

public class ProdutoAction {

    private ProdutoDAO produtoDAO;
    private ServidorDAO servidorDAO;

    public ProdutoAction(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
        this.produtoDAO = new ProdutoDAO(servidorDAO);
    }

    public List<Produto> getProdutos() throws ExceptionError {
        try {
            return produtoDAO.getProdutos();
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível obter os produtos do sistema."
                    + "\nErro no Servidor");
        }
    }

    public Produto salvar(Dados dados, Produto produto) throws ExceptionInfo, ExceptionError, Exception {
        Produto p = null;

        try {
            p = produtoDAO.getProduto(dados.getInfo("DS_PRODUTO"));
        } catch (Exception ex) {
        }
        if (p != null) {
            throw new ExceptionInfo("Já existe um produto com esta descrição!");
        }

        if (produto == null) {
            produto = new Produto();

        }

        //    produto.setCdProduto(Integer.parseInt(dados.getInfo("CD_PRODUTO")));

        produto.setDsProduto(dados.getInfo("DS_PRODUTO"));
        produto.setDsMarca(dados.getInfo("DS_MARCA"));
        
        String vl_produto = dados.getInfo("VL_PRODUTO");
        if(!vl_produto.equals("")){
            produto.setVlProduto(Double.parseDouble(vl_produto));
        }
        
        String vl_estoque = dados.getInfo("VL_ESTOQUE");
        if(!vl_estoque.equals("")){
            produto.setVlEstoque(Double.parseDouble(vl_estoque));
        }
        
        produto.setDsObservacao(dados.getInfo("DS_OBSERVACAO"));
        produto.setDsTipo(dados.getInfo("DS_TIPO"));
        
        produto.setDtCadastro(new Date());
        //o primeiro tem o padrao que será informado:  
        SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );     
        //Para converter de String para Date:    
        java.util.Date data = sdf.parse(dados.getInfo("DT_VALIDADE"));  
        produto.setDtValidade(data);
        
        try {
            servidorDAO.salvar(produto, produto.getCdProduto() == 0);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível salvar o produto no sistema."
                    + "\nErro no Servidor");
        }
        return produto;
    }

    public void excluirProduto(int cdProduto) throws ExceptionError {
        try {
            produtoDAO.excluirProduto(cdProduto);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível excluir o produto do sistema."
                    + "\nErro no Servidor");
        }
    }
}
