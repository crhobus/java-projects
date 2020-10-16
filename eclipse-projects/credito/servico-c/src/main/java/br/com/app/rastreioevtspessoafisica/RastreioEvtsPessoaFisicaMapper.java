package br.com.app.rastreioevtspessoafisica;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.app.movimentacaofinanceira.dto.MovimentacaoFinanceiraOutput;
import br.com.app.rastreioevtspessoafisica.dao.RastreioEvtsPessoaFisicaEntity;
import br.com.app.rastreioevtspessoafisica.dto.NovaConsultaCreditoInput;
import br.com.app.rastreioevtspessoafisica.dto.NovaConsultaCreditoOutput;
import br.com.app.rastreioevtspessoafisica.dto.PessoaFisicaDto;
import br.com.app.rastreioevtspessoafisica.dto.PossuiDividaEnum;
import br.com.app.rastreioevtspessoafisica.dto.RendaPessoaFisicaDto;

@Component
public class RastreioEvtsPessoaFisicaMapper {

    public RastreioEvtsPessoaFisicaEntity toEntity(NovaConsultaCreditoInput input, PossuiDividaEnum possuiDivida) {
        RastreioEvtsPessoaFisicaEntity entity = new RastreioEvtsPessoaFisicaEntity();

        entity.setCpf(input.getCpf());
        entity.setDataUltimaConsultaCredito(Instant.now());
        entity.setLocalUltimaConsultaCredito(input.getLocalUltimaConsultaCredito());
        entity.setPossuiDivida(possuiDivida);

        return entity;
    }

    public NovaConsultaCreditoOutput toNovaConultaCreditoOutput(PessoaFisicaDto pessoaFisica, PossuiDividaEnum possuiDivida, List<RendaPessoaFisicaDto> ultimosRegistrosRenda, BigDecimal valorEmBens,
                                                                Map<String, List<MovimentacaoFinanceiraOutput>> ultimasMovimentacoesFinanceiras, Map<String, BigDecimal> ultimosGastosCartaoCredito) {
        NovaConsultaCreditoOutput dto = new NovaConsultaCreditoOutput();

        dto.setCpf(pessoaFisica.getCpf());
        dto.setNome(pessoaFisica.getNome());
        dto.setEndereco(pessoaFisica.getEndereco());
        dto.setDataNascimento(pessoaFisica.getDataNascimento());
        dto.setPossuiDivida(possuiDivida);
        dto.setUltimosRegistrosRenda(ultimosRegistrosRenda);
        dto.setValorEmBens(valorEmBens);
        dto.setUltimasMovimentacoesFinanceiras(ultimasMovimentacoesFinanceiras);
        dto.setUltimosGastosCartaoCredito(ultimosGastosCartaoCredito);

        return dto;
    }
}
