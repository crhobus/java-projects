package br.com.app.gastoscartaocredito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.querydsl.core.Tuple;

import br.com.app.gastoscartaocredito.dao.GastosCartaoCreditoEntity;
import br.com.app.gastoscartaocredito.dao.GastosCartaoCreditoRepository;
import br.com.app.gastoscartaocredito.dto.GastosCartaoCreditoDto;

@Service
public class GastosCartaoCreditoService {

    @Autowired
    private GastosCartaoCreditoRepository repository;

    @Autowired
    private GastosCartaoCreditoMapper mapper;

    @Transactional
    public long create(GastosCartaoCreditoDto dto) throws Exception {
        GastosCartaoCreditoEntity gastosCartaoCredito = mapper.toEntity(dto);

        repository.save(gastosCartaoCredito);

        return gastosCartaoCredito.getId();
    }

    @Transactional
    public boolean update(GastosCartaoCreditoDto dto) throws Exception {
        Optional<GastosCartaoCreditoEntity> gastosCartaoCreditoOpt = repository.findByCpfAndId(dto.getCpf(), dto.getId());
        if (gastosCartaoCreditoOpt.isPresent()) {
            GastosCartaoCreditoEntity gastosCartaoCredito = gastosCartaoCreditoOpt.get();

            mapper.merge(gastosCartaoCredito, dto);

            repository.save(gastosCartaoCredito);

            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public GastosCartaoCreditoDto retrieve(String cpf, long id) {
        Optional<GastosCartaoCreditoEntity> gastosCartaoCreditoOpt = repository.findByCpfAndId(cpf, id);
        if (gastosCartaoCreditoOpt.isPresent()) {
            GastosCartaoCreditoEntity gastosCartaoCredito = gastosCartaoCreditoOpt.get();
            return mapper.toDto(gastosCartaoCredito);
        }

        return null;
    }

    @Transactional
    public boolean delete(String cpf, long id) throws Exception {
        Optional<GastosCartaoCreditoEntity> gastosCartaoCreditoOpt = repository.findByCpfAndId(cpf, id);
        if (gastosCartaoCreditoOpt.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<GastosCartaoCreditoDto> list(String cpf) {
        List<GastosCartaoCreditoEntity> gastosCartaoCredito = repository.findByCpf(cpf);

        if (CollectionUtils.isEmpty(gastosCartaoCredito)) {
            return new ArrayList<>();
        }

        return gastosCartaoCredito.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Map<String, BigDecimal> getUltimosGastosCartaoCredito(String cpf, LocalDate dataReferencia, int quantidadeMeses) {
        List<Tuple> gastosCartaoCredito = repository.findUltimosGastosCartaoCredito(cpf, dataReferencia, quantidadeMeses);
        if (CollectionUtils.isEmpty(gastosCartaoCredito)) {
            return new HashMap<>();
        }

        Map<String, BigDecimal> mapDto = new TreeMap<>(Collections.reverseOrder());

        mapDto.putAll(gastosCartaoCredito.stream().collect(Collectors.toMap(t -> t.get(0, String.class), t -> t.get(1, BigDecimal.class))));

        return mapDto;
    }

}
