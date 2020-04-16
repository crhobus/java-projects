package br.com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.app.dao.ProdutoDAO;
import br.com.app.dto.Produto;
import br.com.app.logger.AppLogger;

@Service
public class ProdutoService {

    public String save(Produto produto) {
        AppLogger.LOGGER.info("Salvando o produto {}", produto.getCodigo());
        ProdutoDAO.save(produto);
        return "Produto salvo com sucesso";
    }

    public List<Produto> list() {
        AppLogger.LOGGER.info("Listando os produtos");
        return ProdutoDAO.list();
    }

    public double obterPrecoProduto(int codigo) throws Exception {
        AppLogger.LOGGER.info("Obtendo o preço do produto {}", codigo);
        Produto produto = ProdutoDAO.getProduto(codigo);
        if (produto != null) {
            return produto.getPreco().doubleValue();
        }
        throw new Exception("Produto não encontrado");
    }
}
