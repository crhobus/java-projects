package br.com.app.empresa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import br.com.app.CommonBaseTest;
import br.com.app.common.builder.EmpresaDtoBuilder;
import br.com.app.common.builder.EmpresaEntityBuilder;
import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.empresa.dto.EmpresaDto;

public class EmpresaMapperTest extends CommonBaseTest {

    @InjectMocks
    private EmpresaMapper mapper;

    @Override
    public void setUp() {}

    @Test
    public void toEntity() {
        EmpresaDto dto = EmpresaDtoBuilder.create().withNome("Empresa Teste").withCnpj("12345678000011").build();

        EmpresaEntity result = mapper.toEntity(dto);

        assertThat(result).isNotNull();
        assertThat(result.getNome()).isEqualTo(dto.getNome());
        assertThat(result.getCnpj()).isEqualTo(dto.getCnpj());
    }

    @Test
    public void merge() {
        EmpresaDto dto = EmpresaDtoBuilder.create().withNome("Empresa Teste").withCnpj("12345678000011").build();
        EmpresaEntity empresa = EmpresaEntityBuilder.create(1L).withNome("Empresa Teste X").withCnpj("12345678000012").build();

        mapper.merge(empresa, dto);

        assertThat(empresa.getNome()).isEqualTo(dto.getNome());
        assertThat(empresa.getCnpj()).isEqualTo(dto.getCnpj());
    }

    @Test
    public void toDto() {
        EmpresaEntity empresa = EmpresaEntityBuilder.create(1L).withNome("Empresa Teste X").withCnpj("12345678000012").build();

        EmpresaDto result = mapper.toDto(empresa);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(empresa.getId());
        assertThat(result.getNome()).isEqualTo(empresa.getNome());
        assertThat(result.getCnpj()).isEqualTo(empresa.getCnpj());
    }

}
