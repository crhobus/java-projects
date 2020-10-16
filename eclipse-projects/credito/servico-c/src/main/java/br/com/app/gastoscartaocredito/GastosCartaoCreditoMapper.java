package br.com.app.gastoscartaocredito;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import br.com.app.gastoscartaocredito.dao.GastosCartaoCreditoEntity;
import br.com.app.gastoscartaocredito.dto.GastosCartaoCreditoDto;

@Component
public class GastosCartaoCreditoMapper {

    public GastosCartaoCreditoEntity toEntity(GastosCartaoCreditoDto dto) {
        GastosCartaoCreditoEntity entity = new GastosCartaoCreditoEntity();

        merge(entity, dto);

        return entity;
    }

    public void merge(GastosCartaoCreditoEntity entity, GastosCartaoCreditoDto dto) {
        LocalDateTime data = LocalDateTime.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        entity.setCpf(dto.getCpf());
        entity.setValor(dto.getValor());
        entity.setData(data.toInstant(ZoneOffset.UTC));
        entity.setLocal(dto.getLocal());
    }

    public GastosCartaoCreditoDto toDto(GastosCartaoCreditoEntity entity) {
        GastosCartaoCreditoDto dto = new GastosCartaoCreditoDto();

        LocalDateTime ldt = entity.getData().atOffset(ZoneOffset.UTC).toLocalDateTime();
        String data = ldt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

        dto.setId(entity.getId());
        dto.setCpf(entity.getCpf());
        dto.setValor(entity.getValor());
        dto.setData(data);
        dto.setLocal(entity.getLocal());

        return dto;
    }
}
