package br.com.app.converter;

import org.springframework.stereotype.Component;

import br.com.app.dto.ProdutoDto;
import br.com.app.repository.ProdutoEntity;

@Component
public class ProdutoConverter {

    public ProdutoEntity toEntity(ProdutoDto dto) {
        ProdutoEntity entity = new ProdutoEntity();

        merge(entity, dto);

        return entity;
    }

    public void merge(ProdutoEntity entity, ProdutoDto dto) {
        entity.setNome(dto.getNome());
        entity.setMarca(dto.getMarca());
        entity.setPreco(dto.getPreco());
    }

    public ProdutoDto toDto(ProdutoEntity entity) {
        ProdutoDto dto = new ProdutoDto();

        dto.setCodigo(entity.getCodigo());
        dto.setNome(entity.getNome());
        dto.setMarca(entity.getMarca());
        dto.setPreco(entity.getPreco());

        return dto;
    }
}
