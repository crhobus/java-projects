package br.com.app.empresa;

import org.springframework.stereotype.Component;

import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.empresa.dto.EmpresaDto;

@Component
public class EmpresaMapper {

    public EmpresaEntity toEntity(EmpresaDto dto) {
        EmpresaEntity entity = new EmpresaEntity();

        merge(entity, dto);

        return entity;
    }

    public void merge(EmpresaEntity entity, EmpresaDto dto) {
        entity.setNome(dto.getNome());
        entity.setCnpj(dto.getCnpj());
    }

    public EmpresaDto toDto(EmpresaEntity entity) {
        EmpresaDto dto = new EmpresaDto();

        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCnpj(entity.getCnpj());

        return dto;
    }

}
