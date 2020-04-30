package br.com.app.service;

import static br.com.app.infra.cache.CacheConfig.PRODUTO_CACHE;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.app.converter.ProdutoConverter;
import br.com.app.dto.ProdutoDto;
import br.com.app.infra.logger.AppLogger;
import br.com.app.repository.ProdutoEntity;
import br.com.app.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoConverter converter;

    @Autowired
    private CacheManager cacheManager;

    /*
     * Cria um objeto na base e no cache
     */
    @CachePut(value = { PRODUTO_CACHE }, key = "#result.codigo")
    @Transactional
    public ProdutoDto create(ProdutoDto dto) {
        ProdutoEntity produto = converter.toEntity(dto);

        repository.save(produto);

        AppLogger.LOGGER.info("Inserindo o produto {}", produto.getCodigo());

        return converter.toDto(produto);
    }

    /*
     * Atualiza um objeto na base e somente atualiza o cache se o retorno do método não for nulo
     */
    @CachePut(value = { PRODUTO_CACHE }, key = "#dto.codigo", condition = "#result != null")
    @Transactional
    public ProdutoDto update(ProdutoDto dto) throws Exception {
        if (dto.getCodigo() < 1) {
            throw new Exception("Código inválido");
        }

        Optional<ProdutoEntity> produtoOpt = repository.findById(dto.getCodigo());
        if (produtoOpt.isPresent()) {
            ProdutoEntity produto = produtoOpt.get();

            converter.merge(produto, dto);
            repository.save(produto);

            AppLogger.LOGGER.info("Atualizando o produto {}", produto.getCodigo());

            return converter.toDto(produto);
        }
        return null;
    }

    /*
     * Busca um objeto do cache, caso não exista, busca da base, caso a busca ocorra na base, apenas atualiza o cache se encontrar o objeto
     * 
     * unless = ao menos que - Só atualiza o cache se o retorno não for nulo
     */
    @Cacheable(value = { PRODUTO_CACHE }, key = "#codigo", unless = "#result == null")
    @Transactional(readOnly = true)
    public ProdutoDto retrieve(long codigo) {
        Optional<ProdutoEntity> produtoOpt = repository.findById(codigo);

        if (produtoOpt.isEmpty()) {
            return null;
        }

        ProdutoEntity produto = produtoOpt.get();

        AppLogger.LOGGER.info("Obtendo o produto {}", produto.getCodigo());

        return converter.toDto(produto);
    }

    /*
     * Deleta um objeto da base e do cache (somente deleta do cache caso o objeto existe)
     */
    @CacheEvict(value = { PRODUTO_CACHE }, key = "#codigo", condition = "#result != null")
    @Transactional
    public boolean delete(long codigo) throws Exception {
        if (codigo < 1) {
            throw new Exception("Código inválido");
        }

        Optional<ProdutoEntity> produtoOpt = repository.findById(codigo);
        if (produtoOpt.isEmpty()) {
            return false;
        }

        repository.deleteById(codigo);

        AppLogger.LOGGER.info("Deletando o produto {}", codigo);

        return true;
    }

    @Transactional(readOnly = true)
    public List<ProdutoDto> list() {
        List<ProdutoEntity> produtos = repository.findAll();

        if (CollectionUtils.isEmpty(produtos)) {
            return new ArrayList<>();
        }

        AppLogger.LOGGER.info("Obtendo todos os produtos");

        Cache cache = cacheManager.getCache(PRODUTO_CACHE);

        List<ProdutoDto> list = produtos.stream().map(converter::toDto).collect(Collectors.toList());

        list.forEach(dto -> {
            cache.put(dto.getCodigo(), dto);
        });

        return list;
    }
}
