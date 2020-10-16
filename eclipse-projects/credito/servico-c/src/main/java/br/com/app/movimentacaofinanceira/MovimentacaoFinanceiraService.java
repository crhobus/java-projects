package br.com.app.movimentacaofinanceira;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.querydsl.core.Tuple;

import br.com.app.movimentacaofinanceira.dao.MovimentacaoFinanceiraEntity;
import br.com.app.movimentacaofinanceira.dao.MovimentacaoFinanceiraRepository;
import br.com.app.movimentacaofinanceira.dto.MovimentacaoFinanceiraDto;
import br.com.app.movimentacaofinanceira.dto.MovimentacaoFinanceiraOutput;

@Service
public class MovimentacaoFinanceiraService {

    @Autowired
    private MovimentacaoFinanceiraRepository repository;

    @Autowired
    private MovimentacaoFinanceiraMapper mapper;

    @Transactional
    public long create(MovimentacaoFinanceiraDto dto) throws Exception {
        MovimentacaoFinanceiraEntity movimentacaoFinanceira = mapper.toEntity(dto);

        repository.save(movimentacaoFinanceira);

        return movimentacaoFinanceira.getId();
    }

    @Transactional
    public boolean update(MovimentacaoFinanceiraDto dto) throws Exception {
        Optional<MovimentacaoFinanceiraEntity> movimentacaoFinanceiraOpt = repository.findByCpfAndId(dto.getCpf(), dto.getId());
        if (movimentacaoFinanceiraOpt.isPresent()) {
            MovimentacaoFinanceiraEntity movimentacaoFinanceira = movimentacaoFinanceiraOpt.get();

            mapper.merge(movimentacaoFinanceira, dto);

            repository.save(movimentacaoFinanceira);

            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public MovimentacaoFinanceiraDto retrieve(String cpf, long id) {
        Optional<MovimentacaoFinanceiraEntity> movimentacaoFinanceiraOpt = repository.findByCpfAndId(cpf, id);
        if (movimentacaoFinanceiraOpt.isPresent()) {
            MovimentacaoFinanceiraEntity movimentacaoFinanceira = movimentacaoFinanceiraOpt.get();
            return mapper.toDto(movimentacaoFinanceira);
        }

        return null;
    }

    @Transactional
    public boolean delete(String cpf, long id) throws Exception {
        Optional<MovimentacaoFinanceiraEntity> movimentacaoFinanceiraOpt = repository.findByCpfAndId(cpf, id);
        if (movimentacaoFinanceiraOpt.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<MovimentacaoFinanceiraDto> list(String cpf) {
        List<MovimentacaoFinanceiraEntity> movimentacoesFinanceiras = repository.findByCpf(cpf);

        if (CollectionUtils.isEmpty(movimentacoesFinanceiras)) {
            return new ArrayList<>();
        }

        return movimentacoesFinanceiras.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Map<String, List<MovimentacaoFinanceiraOutput>> getUltimasMovimentacoesFinanceiras(String cpf, LocalDate dataReferencia, int quantidadeMeses) {
        List<Tuple> movimentacoes = repository.findUltimasMovimentacoesFinanceiras(cpf, dataReferencia, quantidadeMeses);
        if (CollectionUtils.isEmpty(movimentacoes)) {
            return new HashMap<>();
        }

        Map<String, List<Tuple>> map = movimentacoes.stream().collect(Collectors.groupingBy(tuple -> {
            Instant dataMovimentacao = tuple.get(1, Instant.class);
            ZonedDateTime data = ZonedDateTime.ofInstant(dataMovimentacao, ZoneOffset.UTC);
            return data.getMonth().getValue() + "/" + data.getYear();
        }, Collectors.toList()));

        Map<String, List<MovimentacaoFinanceiraOutput>> mapDto = new TreeMap<>(Collections.reverseOrder());

        mapDto.putAll(map.keySet().stream().collect(Collectors.toMap(Function.identity(), key -> map.get(key).stream().map(mapper::toMovimentacaoFinanceiraOutput).collect(Collectors.toList()))));

        return mapDto;
    }

}
