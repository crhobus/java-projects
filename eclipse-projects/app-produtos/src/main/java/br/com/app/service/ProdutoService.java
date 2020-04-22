package br.com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.app.dto.ProdutoDto;
import br.com.app.infra.utils.ModelBean;
import br.com.app.repository.ProdutoEntity;
import br.com.app.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ModelBean modelBean;

    @Transactional
    public long create(ProdutoDto dto) {
        ProdutoEntity produto = new ProdutoEntity();
        produto.setNome(dto.getNome());
        produto.setMarca(dto.getMarca());
        produto.setPreco(dto.getPreco());

        modelBean.get(ProdutoRepository.class).save(produto);

        return produto.getCodigo();
    }

    @Transactional
    public boolean update(ProdutoDto dto) throws Exception {
        if (dto.getCodigo() < 1) {
            throw new Exception("Código inválido");
        }

        ProdutoRepository repository = modelBean.get(ProdutoRepository.class);

        Optional<ProdutoEntity> produtoOpt = repository.findById(dto.getCodigo());
        if (produtoOpt.isPresent()) {
            ProdutoEntity produto = produtoOpt.get();

            produto.setNome(dto.getNome());
            produto.setMarca(dto.getMarca());
            produto.setPreco(dto.getPreco());

            repository.save(produto);

            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public ProdutoDto retrieve(long codigo) {
        Optional<ProdutoEntity> produtoOpt = modelBean.get(ProdutoRepository.class).findById(codigo);

        if (!produtoOpt.isPresent()) {
            return null;
        }

        ProdutoEntity produto = produtoOpt.get();

        ProdutoDto dto = new ProdutoDto();
        dto.setCodigo(produto.getCodigo());
        dto.setNome(produto.getNome());
        dto.setMarca(produto.getMarca());
        dto.setPreco(produto.getPreco());

        return dto;
    }

    @Transactional
    public boolean delete(long codigo) throws Exception {
        if (codigo < 1) {
            throw new Exception("Código inválido");
        }

        modelBean.get(ProdutoRepository.class).deleteById(codigo);
        return true;
    }

    @Transactional(readOnly = true)
    public List<ProdutoDto> list() {
        List<ProdutoEntity> produtos = modelBean.get(ProdutoRepository.class).findAll();

        if (CollectionUtils.isEmpty(produtos)) {
            return new ArrayList<>();
        }

        return produtos.stream().map(produto -> {
            ProdutoDto dto = new ProdutoDto();
            dto.setCodigo(produto.getCodigo());
            dto.setNome(produto.getNome());
            dto.setMarca(produto.getMarca());
            dto.setPreco(produto.getPreco());
            return dto;
        }).collect(Collectors.toList());
    }
}
