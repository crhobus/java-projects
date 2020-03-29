package br.com.app.lancamento;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.app.funcionario.FuncionarioService;
import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.lancamento.dao.LancamentoEntity;
import br.com.app.lancamento.dao.LancamentoRepository;
import br.com.app.lancamento.dto.LancamentoDto;
import br.com.app.lancamento.dto.LancamentoFuncOutput;

@Service
public class LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Autowired
    private LancamentoMapper mapper;

    @Autowired
    private FuncionarioService funcionarioService;

    @Transactional
    public long create(LancamentoDto dto) throws Exception {
        FuncionarioEntity funcionario = funcionarioService.getFuncionario(dto.getFuncionarioId());
        if (funcionario == null) {
            throw new Exception("O funcionário informado não existe");
        }

        LancamentoEntity lancamento = mapper.toEntity(dto, funcionario);

        repository.save(lancamento);

        return lancamento.getId();
    }

    @Transactional
    public boolean update(LancamentoDto dto) throws Exception {
        if (dto.getId() < 1) {
            throw new Exception("ID do lançamento inválido");
        }

        Optional<LancamentoEntity> lancamentoOpt = repository.findById(dto.getId());
        if (lancamentoOpt.isPresent()) {
            LancamentoEntity lancamento = lancamentoOpt.get();

            FuncionarioEntity funcionario = funcionarioService.getFuncionario(dto.getFuncionarioId());
            if (funcionario == null) {
                throw new Exception("O funcionário informado não existe");
            }

            mapper.merge(lancamento, funcionario, dto);

            repository.save(lancamento);

            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public LancamentoDto retrieve(long id) {
        Optional<LancamentoEntity> lancamento = repository.findById(id);

        if (lancamento.isPresent()) {
            return mapper.toDto(lancamento.get());
        }

        return null;
    }

    @Transactional
    public boolean delete(long id) throws Exception {
        if (id < 1) {
            throw new Exception("ID do lançamento inválido");
        }

        repository.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public List<LancamentoDto> list() {
        List<LancamentoEntity> lancamentos = repository.findAll();

        if (CollectionUtils.isEmpty(lancamentos)) {
            return new ArrayList<>();
        }

        return lancamentos.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<LancamentoFuncOutput> toListLancamentoFuncOutput(List<LancamentoEntity> lancamentos) {
        return mapper.toListLancamentoFuncOutput(lancamentos);
    }
}
