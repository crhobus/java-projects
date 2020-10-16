package br.com.app.bempessoafisica;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.app.bempessoafisica.dao.BemPessoaFisicaEntity;
import br.com.app.bempessoafisica.dao.BemPessoaFisicaRepository;
import br.com.app.bempessoafisica.dto.BemPessoaFisicaDto;

@Service
public class BemPessoaFisicaService {

    @Autowired
    private BemPessoaFisicaRepository repository;

    @Autowired
    private BemPessoaFisicaMapper mapper;

    @Transactional
    public long create(BemPessoaFisicaDto dto) throws Exception {
        BemPessoaFisicaEntity bem = mapper.toEntity(dto);

        repository.save(bem);

        return bem.getId();
    }

    @Transactional
    public boolean update(BemPessoaFisicaDto dto) throws Exception {
        Optional<BemPessoaFisicaEntity> bemOpt = repository.findByCpfAndId(dto.getCpf(), dto.getId());
        if (bemOpt.isPresent()) {
            BemPessoaFisicaEntity bem = bemOpt.get();

            mapper.merge(bem, dto);

            repository.save(bem);

            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public BemPessoaFisicaDto retrieve(String cpf, long id) {
        Optional<BemPessoaFisicaEntity> bemOpt = repository.findByCpfAndId(cpf, id);
        if (bemOpt.isPresent()) {
            BemPessoaFisicaEntity bem = bemOpt.get();
            return mapper.toDto(bem);
        }

        return null;
    }

    @Transactional
    public boolean delete(String cpf, long id) throws Exception {
        Optional<BemPessoaFisicaEntity> bemOpt = repository.findByCpfAndId(cpf, id);
        if (bemOpt.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<BemPessoaFisicaDto> list(String cpf) {
        List<BemPessoaFisicaEntity> bens = repository.findByCpf(cpf);

        if (CollectionUtils.isEmpty(bens)) {
            return new ArrayList<>();
        }

        return bens.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BigDecimal getValorEmBensPessoaFisica(String cpf) {
        return repository.somaValoresBensCpf(cpf);
    }
}
