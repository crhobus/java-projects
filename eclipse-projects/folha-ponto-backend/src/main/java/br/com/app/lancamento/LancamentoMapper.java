package br.com.app.lancamento;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.lancamento.dao.LancamentoEntity;
import br.com.app.lancamento.dto.LancamentoDto;
import br.com.app.lancamento.dto.LancamentoFuncOutput;

@Component
public class LancamentoMapper {

    public LancamentoEntity toEntity(LancamentoDto dto, FuncionarioEntity funcionario) {
        LancamentoEntity entity = new LancamentoEntity();

        merge(entity, funcionario, dto);

        return entity;
    }

    public void merge(LancamentoEntity entity, FuncionarioEntity funcionario, LancamentoDto dto) {
        LocalDateTime data = LocalDateTime.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        entity.setData(data.toInstant(ZoneOffset.UTC));
        entity.setDescricao(dto.getDescricao());
        entity.setLocalizacao(dto.getLocalizacao());
        entity.setTipo(dto.getTipo());
        entity.setFuncionario(funcionario);
    }

    public LancamentoDto toDto(LancamentoEntity entity) {
        LancamentoDto dto = new LancamentoDto();

        LocalDateTime ldt = entity.getData().atOffset(ZoneOffset.UTC).toLocalDateTime();
        String data = ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        dto.setData(data);

        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setLocalizacao(entity.getLocalizacao());
        dto.setTipo(entity.getTipo());
        dto.setFuncionarioId(entity.getFuncionario().getId());

        return dto;
    }

    public List<LancamentoFuncOutput> toListLancamentoFuncOutput(List<LancamentoEntity> lancamentos) {
        if (CollectionUtils.isEmpty(lancamentos)) {
            return Collections.emptyList();
        }

        return lancamentos.stream().map(lancamento -> {
            LocalDateTime ldt = lancamento.getData().atOffset(ZoneOffset.UTC).toLocalDateTime();
            String data = ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            LancamentoFuncOutput output = new LancamentoFuncOutput();
            output.setData(data);
            output.setDescricao(lancamento.getDescricao());
            output.setLocalizacao(lancamento.getLocalizacao());
            output.setTipo(lancamento.getTipo());
            return output;
        }).collect(Collectors.toList());
    }
}
