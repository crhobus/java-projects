package br.com.app.rendapessoafisica;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.querydsl.core.Tuple;

import br.com.app.rendapessoafisica.dao.RendaPessoaFisicaEntity;
import br.com.app.rendapessoafisica.dao.RendaPessoaFisicaRepository;
import br.com.app.rendapessoafisica.dto.RendaPessoaFisicaDto;
import br.com.app.rendapessoafisica.dto.RendaPessoaFisicaOutput;

@Service
public class RendaPessoaFisicaService {

    @Autowired
    private RendaPessoaFisicaRepository repository;

    @Autowired
    private RendaPessoaFisicaMapper mapper;

    @Transactional
    public long create(RendaPessoaFisicaDto dto) throws Exception {
        RendaPessoaFisicaEntity pessoaFisica = mapper.toEntity(dto);

        repository.save(pessoaFisica);

        return pessoaFisica.getId();
    }

    @Transactional
    public boolean update(RendaPessoaFisicaDto dto) throws Exception {
        Optional<RendaPessoaFisicaEntity> pessoaFisicaOpt = repository.findByCpfAndId(dto.getCpf(), dto.getId());
        if (pessoaFisicaOpt.isPresent()) {
            RendaPessoaFisicaEntity pessoaFisica = pessoaFisicaOpt.get();

            mapper.merge(pessoaFisica, dto);

            repository.save(pessoaFisica);

            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public RendaPessoaFisicaDto retrieve(String cpf, long id) {
        Optional<RendaPessoaFisicaEntity> pessoaFisicaOpt = repository.findByCpfAndId(cpf, id);
        if (pessoaFisicaOpt.isPresent()) {
            RendaPessoaFisicaEntity pessoaFisica = pessoaFisicaOpt.get();
            return mapper.toDto(pessoaFisica);
        }

        return null;
    }

    @Transactional
    public boolean delete(String cpf, long id) throws Exception {
        Optional<RendaPessoaFisicaEntity> pessoaFisicaOpt = repository.findByCpfAndId(cpf, id);
        if (pessoaFisicaOpt.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<RendaPessoaFisicaDto> list() {
        List<RendaPessoaFisicaEntity> pessoasFisicas = repository.findAll();

        if (CollectionUtils.isEmpty(pessoasFisicas)) {
            return new ArrayList<>();
        }

        return pessoasFisicas.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<RendaPessoaFisicaOutput> getUltimosRegistrosRenda(String cpf, int quantidade) {
        List<Tuple> ultimosRegistrosRenda = repository.findUltimosRegistrosRenda(cpf, quantidade);

        if (CollectionUtils.isEmpty(ultimosRegistrosRenda)) {
            return new ArrayList<>();
        }

        return ultimosRegistrosRenda.stream().map(mapper::toRendaPessoaFisicaOutput).collect(Collectors.toList());
    }
}
