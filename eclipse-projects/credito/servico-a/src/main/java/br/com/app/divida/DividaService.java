package br.com.app.divida;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.app.divida.dao.DividaEntity;
import br.com.app.divida.dao.DividaRepository;
import br.com.app.divida.dto.DividaDto;
import br.com.app.divida.dto.QuitadoEnum;
import br.com.app.pessoafisica.PessoaFisicaService;
import br.com.app.pessoafisica.dao.PessoaFisicaEntity;

@Service
public class DividaService {

    @Autowired
    private DividaRepository repository;

    @Autowired
    private DividaMapper mapper;

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @Transactional
    public long create(DividaDto dto) throws Exception {
        PessoaFisicaEntity pessoaFisica = pessoaFisicaService.getPessoaFisica(dto.getCpf());
        if (pessoaFisica == null) {
            throw new Exception("Pessoa física não encontrada");
        }
        DividaEntity divida = mapper.toEntity(dto, pessoaFisica);

        repository.save(divida);

        return divida.getId();
    }

    @Transactional
    public boolean update(DividaDto dto) throws Exception {
        Optional<DividaEntity> dividaOpt = repository.findByIdAndCpf(dto.getId(), dto.getCpf());
        if (dividaOpt.isPresent()) {
            DividaEntity divida = dividaOpt.get();

            mapper.merge(divida, dto, divida.getPessoaFisica());

            repository.save(divida);

            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public DividaDto retrieve(String cpf, long id) {
        Optional<DividaEntity> dividaOpt = repository.findByIdAndCpf(id, cpf);
        if (dividaOpt.isPresent()) {
            DividaEntity divida = dividaOpt.get();
            return mapper.toDto(divida);
        }

        return null;
    }

    @Transactional
    public boolean delete(String cpf, long id) throws Exception {
        Optional<DividaEntity> dividaOpt = repository.findByIdAndCpf(id, cpf);
        if (dividaOpt.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public List<DividaDto> list(String cpf) {
        List<DividaEntity> dividas = repository.findAllByCpfAndNotQuitado(cpf);

        if (CollectionUtils.isEmpty(dividas)) {
            return new ArrayList<>();
        }

        return dividas.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public boolean possuiDivida(String cpf) throws Exception {
        if (!pessoaFisicaService.existsPessoaFisica(cpf)) {
            throw new Exception("Pessoa física não encontrada");
        }

        return repository.existsDividaByCpf(cpf);
    }

    @Transactional
    public boolean quitarDivida(String cpf, long id) throws Exception {
        Optional<DividaEntity> dividaOpt = repository.findByIdAndCpf(id, cpf);
        if (dividaOpt.isPresent()) {
            DividaEntity divida = dividaOpt.get();
            if (divida.getQuitado() == QuitadoEnum.SIM) {
                throw new Exception("A dívida desta pessoa física já se encontra quitada");
            }
            divida.setQuitado(QuitadoEnum.SIM);

            repository.save(divida);
            return true;
        }
        return false;
    }
}
