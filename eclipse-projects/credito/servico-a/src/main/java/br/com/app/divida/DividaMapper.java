package br.com.app.divida;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import br.com.app.divida.dao.DividaEntity;
import br.com.app.divida.dto.DividaDto;
import br.com.app.pessoafisica.dao.PessoaFisicaEntity;

@Component
public class DividaMapper {

    public DividaEntity toEntity(DividaDto dto, PessoaFisicaEntity pessoaFisica) {
        DividaEntity entity = new DividaEntity();

        merge(entity, dto, pessoaFisica);

        return entity;
    }

    public void merge(DividaEntity entity, DividaDto dto, PessoaFisicaEntity pessoaFisica) {
        LocalDate dataVencimento = LocalDate.parse(dto.getDataVencimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());
        entity.setDataVencimento(dataVencimento);
        entity.setQuitado(dto.getQuitado());
        entity.setPessoaFisica(pessoaFisica);
    }

    public DividaDto toDto(DividaEntity entity) {
        DividaDto dto = new DividaDto();

        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setValor(entity.getValor());
        dto.setDataVencimento(entity.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        dto.setQuitado(entity.getQuitado());
        dto.setCpf(entity.getPessoaFisica().getCpf());

        return dto;
    }
}
