package br.com.app.empresa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.app.CommonBaseTest;
import br.com.app.common.builder.EmpresaDtoBuilder;
import br.com.app.common.builder.EmpresaEntityBuilder;
import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.empresa.dao.EmpresaRepository;
import br.com.app.empresa.dto.EmpresaDto;

public class EmpresaServiceTest extends CommonBaseTest {

    @Mock
    private EmpresaRepository repository;

    @Mock
    private EmpresaMapper mapper;

    @InjectMocks
    private EmpresaService service;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
        EmpresaDto dto = EmpresaDtoBuilder.create().withNome("Empresa Teste").withCnpj("12345678000011").build();
        EmpresaEntity empresa = EmpresaEntityBuilder.create(1L).withNome("Empresa Teste").withCnpj("12345678000011").build();

        when(mapper.toEntity(dto)).thenReturn(empresa);

        long result = service.create(dto);

        ArgumentCaptor<EmpresaEntity> captor = ArgumentCaptor.forClass(EmpresaEntity.class);
        verify(repository).save(captor.capture());
        EmpresaEntity capturedEmpresa = captor.getValue();

        assertThat(capturedEmpresa).isNotNull();
        assertThat(capturedEmpresa.getId()).isEqualTo(empresa.getId());
        assertThat(capturedEmpresa.getNome()).isEqualTo(empresa.getNome());
        assertThat(capturedEmpresa.getCnpj()).isEqualTo(empresa.getCnpj());

        assertThat(result).isEqualTo(empresa.getId());

        verify(mapper).toEntity(dto);
        verify(repository).save(empresa);
    }

    @Test
    public void update() {
        EmpresaDto dto = EmpresaDtoBuilder.create().withId(1L).withNome("Empresa Teste").withCnpj("12345678000011").build();
        EmpresaEntity empresa = EmpresaEntityBuilder.create(1L).withNome("Empresa Teste").withCnpj("12345678000011").build();

        Optional<EmpresaEntity> empresaOpt = Optional.of(empresa);
        when(repository.findById(dto.getId())).thenReturn(empresaOpt);

        boolean result = false;
        try {
            result = service.update(dto);
        } catch (Exception e) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }

        ArgumentCaptor<EmpresaEntity> captor = ArgumentCaptor.forClass(EmpresaEntity.class);
        verify(repository).save(captor.capture());
        EmpresaEntity capturedEmpresa = captor.getValue();

        assertThat(capturedEmpresa).isNotNull();
        assertThat(capturedEmpresa.getId()).isEqualTo(empresa.getId());
        assertThat(capturedEmpresa.getNome()).isEqualTo(empresa.getNome());
        assertThat(capturedEmpresa.getCnpj()).isEqualTo(empresa.getCnpj());

        assertThat(result).isTrue();

        verify(repository).findById(dto.getId());
        verify(mapper).merge(empresa, dto);
        verify(repository).save(empresa);
    }

    @Test
    public void update_empresaNaoEncontrada() {
        EmpresaDto dto = EmpresaDtoBuilder.create().withId(1L).withNome("Empresa Teste").withCnpj("12345678000011").build();

        Optional<EmpresaEntity> empresaOpt = Optional.empty();
        when(repository.findById(dto.getId())).thenReturn(empresaOpt);

        boolean result = false;
        try {
            result = service.update(dto);
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }

        assertThat(result).isFalse();

        verify(repository).findById(dto.getId());
        verify(mapper, never()).merge(any(EmpresaEntity.class), eq(dto));
        verify(repository, never()).save(any(EmpresaEntity.class));
    }

    @Test
    public void update_idInvalido() {
        EmpresaDto dto = EmpresaDtoBuilder.create().withNome("Empresa Teste").withCnpj("12345678000011").build();

        try {
            service.update(dto);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("ID da empresa inválido");
        }

        verify(repository, never()).findById(dto.getId());
        verify(mapper, never()).merge(any(EmpresaEntity.class), eq(dto));
        verify(repository, never()).save(any(EmpresaEntity.class));
    }

    @Test
    public void retrieve() {
        String cnpj = "12345678000011";
        EmpresaEntity empresa = new EmpresaEntity();
        EmpresaDto dto = new EmpresaDto();

        when(repository.findByCnpj(cnpj)).thenReturn(empresa);
        when(mapper.toDto(empresa)).thenReturn(dto);

        EmpresaDto result = service.retrieve(cnpj);

        assertThat(result).isNotNull();
        assertThat(result).isSameAs(dto);

        verify(repository).findByCnpj(cnpj);
        verify(mapper).toDto(empresa);
    }

    @Test
    public void retrieve_empresaNaoEncontrada() {
        String cnpj = "12345678000011";

        when(repository.findByCnpj(cnpj)).thenReturn(null);

        EmpresaDto result = service.retrieve(cnpj);

        assertThat(result).isNull();

        verify(repository).findByCnpj(cnpj);
        verify(mapper, never()).toDto(any(EmpresaEntity.class));
    }

    @Test
    public void delete() {
        long id = 1L;

        boolean result = false;
        try {
            result = service.delete(id);
        } catch (Exception e) {
            failBecauseExceptionWasNotThrown(Exception.class);

        }
        assertThat(result).isTrue();

        verify(repository).deleteById(id);
    }

    @Test
    public void delete_idInvalido() {
        long id = 0L;

        try {
            service.delete(id);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("ID da empresa inválido");
        }

        verify(repository, never()).deleteById(id);
    }

    @Test
    public void list() {
        EmpresaDto dto1 = EmpresaDtoBuilder.create().withId(1L).withNome("Empresa Teste 1").withCnpj("12345678000011").build();
        EmpresaDto dto2 = EmpresaDtoBuilder.create().withId(2L).withNome("Empresa Teste 2").withCnpj("12345678000012").build();

        EmpresaEntity empresa1 = EmpresaEntityBuilder.create(1L).withNome("Empresa Teste 1").withCnpj("12345678000011").build();
        EmpresaEntity empresa2 = EmpresaEntityBuilder.create(2L).withNome("Empresa Teste 2").withCnpj("12345678000012").build();

        List<EmpresaEntity> empresas = List.of(empresa1, empresa2);
        when(repository.findAll()).thenReturn(empresas);

        when(mapper.toDto(empresa1)).thenReturn(dto1);
        when(mapper.toDto(empresa2)).thenReturn(dto2);

        List<EmpresaDto> result = service.list();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isSameAs(dto1);
        assertThat(result.get(1)).isSameAs(dto2);

        verify(repository).findAll();
        verify(mapper).toDto(empresa1);
        verify(mapper).toDto(empresa2);
    }

    @Test
    public void list_nenhumaEmpresaCadastrada() {
        List<EmpresaEntity> empresas = new ArrayList<>();
        when(repository.findAll()).thenReturn(empresas);

        List<EmpresaDto> result = service.list();

        assertThat(result).isEmpty();

        verify(repository).findAll();
        verify(mapper, never()).toDto(any(EmpresaEntity.class));
    }

    @Test
    public void getEmpresa() {
        long id = 1L;

        EmpresaEntity empresa = new EmpresaEntity();
        Optional<EmpresaEntity> empresaOpt = Optional.of(empresa);
        when(repository.findById(id)).thenReturn(empresaOpt);

        EmpresaEntity result = service.getEmpresa(id);

        assertThat(result).isNotNull();
        assertThat(result).isSameAs(empresa);

        verify(repository).findById(id);
    }

    @Test
    public void getEmpresa_empresaNaoEncontrada() {
        long id = 1L;

        Optional<EmpresaEntity> empresaOpt = Optional.empty();
        when(repository.findById(id)).thenReturn(empresaOpt);

        EmpresaEntity result = service.getEmpresa(id);

        assertThat(result).isNull();

        verify(repository).findById(id);
    }

}
