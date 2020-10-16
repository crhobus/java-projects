package br.com.app.pessoafisica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import br.com.app.pessoafisica.dao.PessoaFisicaEntity;
import br.com.app.pessoafisica.dto.PessoaFisicaDto;

@Component
public class PessoaFisicaMapper {

    public PessoaFisicaEntity toEntity(PessoaFisicaDto dto) {
        PessoaFisicaEntity entity = new PessoaFisicaEntity();

        merge(entity, dto);

        return entity;
    }

    public void merge(PessoaFisicaEntity entity, PessoaFisicaDto dto) {
        LocalDate dataNascimento = LocalDate.parse(dto.getDataNascimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        entity.setCpf(dto.getCpf());
        entity.setNome(dto.getNome());
        entity.setEndereco(dto.getEndereco());
        entity.setDataNascimento(dataNascimento);
    }

    public PessoaFisicaDto toDto(PessoaFisicaEntity entity) {
        PessoaFisicaDto dto = new PessoaFisicaDto();

        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setNome(entity.getNome());
        dto.setEndereco(entity.getEndereco());
        dto.setDataNascimento(entity.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        return dto;
    }
}
