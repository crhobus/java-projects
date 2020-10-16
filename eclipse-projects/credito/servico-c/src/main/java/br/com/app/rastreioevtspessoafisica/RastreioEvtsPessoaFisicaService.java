package br.com.app.rastreioevtspessoafisica;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.app.gastoscartaocredito.GastosCartaoCreditoService;
import br.com.app.movimentacaofinanceira.MovimentacaoFinanceiraService;
import br.com.app.movimentacaofinanceira.dto.MovimentacaoFinanceiraOutput;
import br.com.app.rastreioevtspessoafisica.dao.RastreioEvtsPessoaFisicaEntity;
import br.com.app.rastreioevtspessoafisica.dao.RastreioEvtsPessoaFisicaRepository;
import br.com.app.rastreioevtspessoafisica.dto.NovaConsultaCreditoInput;
import br.com.app.rastreioevtspessoafisica.dto.NovaConsultaCreditoOutput;
import br.com.app.rastreioevtspessoafisica.dto.PessoaFisicaDto;
import br.com.app.rastreioevtspessoafisica.dto.PossuiDividaEnum;
import br.com.app.rastreioevtspessoafisica.dto.RendaPessoaFisicaDto;

@Service
public class RastreioEvtsPessoaFisicaService {

    @Autowired
    private RastreioEvtsPessoaFisicaRepository repository;

    @Autowired
    private RastreioEvtsPessoaFisicaMapper mapper;

    @Autowired
    private MovimentacaoFinanceiraService movimentacaoFinanceiraService;

    @Autowired
    private GastosCartaoCreditoService gastosCartaoCreditoService;

    @Transactional
    public NovaConsultaCreditoOutput gerarNovaConultaCredito(NovaConsultaCreditoInput input, PessoaFisicaDto pessoaFisica, PossuiDividaEnum possuiDivida,
                                                             List<RendaPessoaFisicaDto> ultimosRegistrosRenda, BigDecimal valorEmBens) {
        Map<String, List<MovimentacaoFinanceiraOutput>> ultimasMovimentacoesFinanceiras = movimentacaoFinanceiraService.getUltimasMovimentacoesFinanceiras(pessoaFisica.getCpf(), LocalDate.now(), 3);
        Map<String, BigDecimal> ultimosGastosCartaoCredito = gastosCartaoCreditoService.getUltimosGastosCartaoCredito(pessoaFisica.getCpf(), LocalDate.now(), 3);

        RastreioEvtsPessoaFisicaEntity rastreioEvts = mapper.toEntity(input, possuiDivida);

        repository.save(rastreioEvts);

        return mapper.toNovaConultaCreditoOutput(pessoaFisica, possuiDivida, ultimosRegistrosRenda, valorEmBens, ultimasMovimentacoesFinanceiras, ultimosGastosCartaoCredito);
    }
}
