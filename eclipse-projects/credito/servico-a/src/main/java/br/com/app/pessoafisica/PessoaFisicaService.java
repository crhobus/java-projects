package br.com.app.pessoafisica;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.app.pessoafisica.dao.PessoaFisicaEntity;
import br.com.app.pessoafisica.dao.PessoaFisicaRepository;
import br.com.app.pessoafisica.dto.PessoaFisicaDto;

@Service
public class PessoaFisicaService {

    @Autowired
    private PessoaFisicaRepository repository;

    @Autowired
    private PessoaFisicaMapper mapper;

    @Transactional
    public long create(PessoaFisicaDto dto) throws Exception {
        if (existsPessoaFisica(dto.getCpf())) {
            throw new Exception("Esta pessoa física já existe");
        }
        PessoaFisicaEntity pessoaFisica = mapper.toEntity(dto);

        repository.save(pessoaFisica);

        return pessoaFisica.getId();
    }

    @Transactional
    public boolean update(PessoaFisicaDto dto) throws Exception {
        Optional<PessoaFisicaEntity> pessoaFisicaOpt = repository.findByCpf(dto.getCpf());
        if (pessoaFisicaOpt.isPresent()) {
            PessoaFisicaEntity pessoaFisica = pessoaFisicaOpt.get();

            mapper.merge(pessoaFisica, dto);

            repository.save(pessoaFisica);

            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public PessoaFisicaDto retrieve(String cpf) {
        Optional<PessoaFisicaEntity> pessoaFisicaOpt = repository.findByCpf(cpf);
        if (pessoaFisicaOpt.isPresent()) {
            PessoaFisicaEntity pessoaFisica = pessoaFisicaOpt.get();
            return mapper.toDto(pessoaFisica);
        }

        return null;
    }

    @Transactional
    public boolean delete(String cpf) throws Exception {
        repository.deleteByCpf(cpf);
        return true;
    }

    @Transactional(readOnly = true)
    public List<PessoaFisicaDto> list() {
        List<PessoaFisicaEntity> pessoasFisicas = repository.findAll();

        if (CollectionUtils.isEmpty(pessoasFisicas)) {
            return new ArrayList<>();
        }

        return pessoasFisicas.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PessoaFisicaEntity getPessoaFisica(String cpf) {
        return repository.findByCpf(cpf).orElse(null);
    }

    @Transactional(readOnly = true)
    public int getIdadePessoaFisica(String cpf, String dataReferencia) throws Exception {
        PessoaFisicaEntity pessoaFisica = getPessoaFisica(cpf);
        if (pessoaFisica == null) {
            throw new Exception("Pessoa física não encontrada");
        }

        LocalDate data = LocalDate.parse(dataReferencia, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return Period.between(pessoaFisica.getDataNascimento(), data).getYears();
    }

    @Transactional(readOnly = true)
    public boolean existsPessoaFisica(String cpf) {
        return repository.existsByCpf(cpf);
    }
}
