package br.com.app.bempessoafisica;

import org.springframework.stereotype.Component;

import br.com.app.bempessoafisica.dao.BemPessoaFisicaEntity;
import br.com.app.bempessoafisica.dto.BemPessoaFisicaDto;

@Component
public class BemPessoaFisicaMapper {

    public BemPessoaFisicaEntity toEntity(BemPessoaFisicaDto dto) {
        BemPessoaFisicaEntity entity = new BemPessoaFisicaEntity();

        merge(entity, dto);

        return entity;
    }

    public void merge(BemPessoaFisicaEntity entity, BemPessoaFisicaDto dto) {
        entity.setCpf(dto.getCpf());
        entity.setNome(dto.getNome());
        entity.setValor(dto.getValor());
        entity.setEndereco(dto.getEndereco());
    }

    public BemPessoaFisicaDto toDto(BemPessoaFisicaEntity entity) {
        BemPessoaFisicaDto dto = new BemPessoaFisicaDto();

        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setNome(entity.getNome());
        dto.setValor(entity.getValor());
        dto.setEndereco(entity.getEndereco());

        return dto;
    }
}
