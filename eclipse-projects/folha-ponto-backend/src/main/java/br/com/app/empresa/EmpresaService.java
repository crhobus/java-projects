package br.com.app.empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.empresa.dao.EmpresaRepository;
import br.com.app.empresa.dto.EmpresaDto;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    @Autowired
    private EmpresaMapper mapper;

    @Transactional
    public long create(EmpresaDto dto) {
        EmpresaEntity empresa = mapper.toEntity(dto);

        repository.save(empresa);

        return empresa.getId();
    }

    @Transactional
    public boolean update(EmpresaDto dto) throws Exception {
        if (dto.getId() < 1) {
            throw new Exception("ID da empresa inválido");
        }

        Optional<EmpresaEntity> empresaOpt = repository.findById(dto.getId());
        if (empresaOpt.isPresent()) {
            EmpresaEntity empresa = empresaOpt.get();

            mapper.merge(empresa, dto);

            repository.save(empresa);

            return true;
        }
        return false;
    }

    @Transactional(readOnly = true)
    public EmpresaDto retrieve(String cnpj) {
        EmpresaEntity empresa = repository.findByCnpj(cnpj);

        if (empresa == null) {
            return null;
        }

        return mapper.toDto(empresa);
    }

    @Transactional
    public boolean delete(long id) throws Exception {
        if (id < 1) {
            throw new Exception("ID da empresa inválido");
        }

        repository.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public List<EmpresaDto> list() {
        List<EmpresaEntity> empresas = repository.findAll();

        if (CollectionUtils.isEmpty(empresas)) {
            return new ArrayList<>();
        }

        return empresas.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmpresaEntity getEmpresa(long id) {
        return repository.findById(id).orElse(null);
    }
}
