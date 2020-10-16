package br.com.app.movimentacaofinanceira;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.querydsl.core.Tuple;

import br.com.app.movimentacaofinanceira.dao.MovimentacaoFinanceiraEntity;
import br.com.app.movimentacaofinanceira.dto.MovimentacaoFinanceiraDto;
import br.com.app.movimentacaofinanceira.dto.MovimentacaoFinanceiraOutput;
import br.com.app.movimentacaofinanceira.dto.OperacaoEnum;

@Component
public class MovimentacaoFinanceiraMapper {

    public MovimentacaoFinanceiraEntity toEntity(MovimentacaoFinanceiraDto dto) {
        MovimentacaoFinanceiraEntity entity = new MovimentacaoFinanceiraEntity();

        merge(entity, dto);

        return entity;
    }

    public void merge(MovimentacaoFinanceiraEntity entity, MovimentacaoFinanceiraDto dto) {
        LocalDateTime dataMovimentacao = LocalDateTime.parse(dto.getDataMovimentacao(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        entity.setCpf(dto.getCpf());
        entity.setValor(dto.getValor());
        entity.setDescricao(dto.getDescricao());
        entity.setDataMovimentacao(dataMovimentacao.toInstant(ZoneOffset.UTC));
        entity.setOperacao(dto.getOperacao());
    }

    public MovimentacaoFinanceiraDto toDto(MovimentacaoFinanceiraEntity entity) {
        MovimentacaoFinanceiraDto dto = new MovimentacaoFinanceiraDto();

        LocalDateTime ldt = entity.getDataMovimentacao().atOffset(ZoneOffset.UTC).toLocalDateTime();
        String dataMovimentacao = ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setValor(entity.getValor());
        dto.setDescricao(entity.getDescricao());
        dto.setDataMovimentacao(dataMovimentacao);
        dto.setOperacao(entity.getOperacao());

        return dto;
    }

    public MovimentacaoFinanceiraOutput toMovimentacaoFinanceiraOutput(Tuple movimentacaoFinanceira) {
        MovimentacaoFinanceiraOutput output = new MovimentacaoFinanceiraOutput();

        output.setValor(movimentacaoFinanceira.get(0, BigDecimal.class));
        output.setOperacao(movimentacaoFinanceira.get(2, OperacaoEnum.class));

        return output;
    }
}
