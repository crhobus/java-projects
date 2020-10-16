package br.com.app.rendapessoafisica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.querydsl.core.Tuple;

import br.com.app.rendapessoafisica.dao.RendaPessoaFisicaEntity;
import br.com.app.rendapessoafisica.dto.RendaPessoaFisicaDto;
import br.com.app.rendapessoafisica.dto.RendaPessoaFisicaOutput;

@Component
public class RendaPessoaFisicaMapper {

    public RendaPessoaFisicaEntity toEntity(RendaPessoaFisicaDto dto) {
        RendaPessoaFisicaEntity entity = new RendaPessoaFisicaEntity();

        merge(entity, dto);

        return entity;
    }

    public void merge(RendaPessoaFisicaEntity entity, RendaPessoaFisicaDto dto) {
        LocalDate dataRenda = LocalDate.parse(dto.getDataRenda(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        entity.setCpf(dto.getCpf());
        entity.setIdade(dto.getIdade());
        entity.setRenda(dto.getRenda());
        entity.setFonte(dto.getFonte());
        entity.setDataRenda(dataRenda);
    }

    public RendaPessoaFisicaDto toDto(RendaPessoaFisicaEntity entity) {
        RendaPessoaFisicaDto dto = new RendaPessoaFisicaDto();

        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setIdade(entity.getIdade());
        dto.setRenda(entity.getRenda());
        dto.setFonte(entity.getFonte());
        dto.setDataRenda(entity.getDataRenda().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        return dto;
    }

    public RendaPessoaFisicaOutput toRendaPessoaFisicaOutput(Tuple rendaPessoaFisica) {
        RendaPessoaFisicaOutput output = new RendaPessoaFisicaOutput();

        LocalDate dataRenda = rendaPessoaFisica.get(2, LocalDate.class);

        output.setRenda(rendaPessoaFisica.get(0, BigDecimal.class));
        output.setIdade(rendaPessoaFisica.get(1, Integer.class));
        output.setDataRenda(dataRenda.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        return output;
    }
}
