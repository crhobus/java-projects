package br.com.app.lancamento;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.app.CommonBaseTest;
import br.com.app.common.builder.FuncionarioEntityBuilder;
import br.com.app.common.builder.LancamentoDtoBuilder;
import br.com.app.common.builder.LancamentoEntityBuilder;
import br.com.app.funcionario.FuncionarioService;
import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.lancamento.dao.LancamentoEntity;
import br.com.app.lancamento.dao.LancamentoRepository;
import br.com.app.lancamento.dto.LancamentoDto;
import br.com.app.lancamento.dto.LancamentoFuncOutput;
import br.com.app.lancamento.dto.TipoEnum;

public class LancamentoServiceTest extends CommonBaseTest {

    @Mock
    private LancamentoRepository repository;

    @Mock
    private LancamentoMapper mapper;

    @Mock
    private FuncionarioService funcionarioService;

    @InjectMocks
    private LancamentoService service;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
        try {
            LancamentoDto dto = LancamentoDtoBuilder.create().withData("27/05/2020 21:34:15").withDescricao("Teste").withLocalizacao("Local de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionarioId(1L).build();
            FuncionarioEntity funcionario = FuncionarioEntityBuilder.create(1L).build();
            LancamentoEntity lancamento = LancamentoEntityBuilder.create(1L).withData(LocalDateTime.of(2020, 05, 27, 21, 34, 15).toInstant(ZoneOffset.UTC)).withDescricao("Teste").withLocalizacao("Local de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario).build();

            when(funcionarioService.getFuncionario(dto.getFuncionarioId())).thenReturn(funcionario);
            when(mapper.toEntity(dto, funcionario)).thenReturn(lancamento);

            long result = service.create(dto);

            ArgumentCaptor<LancamentoEntity> captor = ArgumentCaptor.forClass(LancamentoEntity.class);
            verify(repository).save(captor.capture());
            LancamentoEntity capturedLancamento = captor.getValue();

            assertThat(capturedLancamento).isNotNull();
            assertThat(capturedLancamento.getId()).isEqualTo(lancamento.getId());
            assertThat(capturedLancamento.getData()).isEqualTo(lancamento.getData());
            assertThat(capturedLancamento.getDescricao()).isEqualTo(lancamento.getDescricao());
            assertThat(capturedLancamento.getLocalizacao()).isEqualTo(lancamento.getLocalizacao());
            assertThat(capturedLancamento.getTipo()).isEqualTo(lancamento.getTipo());
            assertThat(capturedLancamento.getFuncionario()).isSameAs(lancamento.getFuncionario());

            assertThat(result).isEqualTo(lancamento.getId());

            verify(funcionarioService).getFuncionario(dto.getFuncionarioId());
            verify(mapper).toEntity(dto, funcionario);
            verify(repository).save(lancamento);
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void create_idFuncionarioInvalido() {
        LancamentoDto dto = LancamentoDtoBuilder.create().withData("27/05/2020 21:34:15").withDescricao("Teste").withLocalizacao("Local de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionarioId(1L).build();

        try {
            when(funcionarioService.getFuncionario(dto.getFuncionarioId())).thenReturn(null);
            service.create(dto);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("O funcionário informado não existe");
        }

        try {
            verify(funcionarioService).getFuncionario(dto.getFuncionarioId());
            verify(mapper, never()).toEntity(any(LancamentoDto.class), any(FuncionarioEntity.class));
            verify(repository, never()).save(any(LancamentoEntity.class));
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void update() {
        try {
            LancamentoDto dto = LancamentoDtoBuilder.create().withId(1L).withData("27/05/2020 21:34:15").withDescricao("Teste").withLocalizacao("Local de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionarioId(1L).build();
            FuncionarioEntity funcionario = FuncionarioEntityBuilder.create(1L).build();
            LancamentoEntity lancamento = LancamentoEntityBuilder.create(1L).withData(LocalDateTime.of(2020, 05, 27, 21, 34, 15).toInstant(ZoneOffset.UTC)).withDescricao("Teste X").withLocalizacao("Local de teste Y").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario).build();

            Optional<LancamentoEntity> lancamentoOpt = Optional.of(lancamento);
            when(repository.findById(dto.getId())).thenReturn(lancamentoOpt);
            when(funcionarioService.getFuncionario(dto.getFuncionarioId())).thenReturn(funcionario);

            boolean result = service.update(dto);

            ArgumentCaptor<LancamentoEntity> captor = ArgumentCaptor.forClass(LancamentoEntity.class);
            verify(repository).save(captor.capture());
            LancamentoEntity capturedLancamento = captor.getValue();

            assertThat(capturedLancamento).isNotNull();
            assertThat(capturedLancamento.getId()).isEqualTo(lancamento.getId());
            assertThat(capturedLancamento.getData()).isEqualTo(lancamento.getData());
            assertThat(capturedLancamento.getDescricao()).isEqualTo(lancamento.getDescricao());
            assertThat(capturedLancamento.getLocalizacao()).isEqualTo(lancamento.getLocalizacao());
            assertThat(capturedLancamento.getTipo()).isEqualTo(lancamento.getTipo());
            assertThat(capturedLancamento.getFuncionario()).isSameAs(lancamento.getFuncionario());

            assertThat(result).isTrue();

            verify(repository).findById(dto.getId());
            verify(funcionarioService).getFuncionario(dto.getFuncionarioId());
            verify(mapper).merge(lancamento, funcionario, dto);
            verify(repository).save(lancamento);
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void update_lancamentoNaoEncontrado() {
        try {
            LancamentoDto dto = LancamentoDtoBuilder.create().withId(1L).withData("27/05/2020 21:34:15").withDescricao("Teste").withLocalizacao("Local de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionarioId(1L).build();

            Optional<LancamentoEntity> lancamentoOpt = Optional.empty();
            when(repository.findById(dto.getId())).thenReturn(lancamentoOpt);

            boolean result = service.update(dto);

            assertThat(result).isFalse();

            verify(repository).findById(dto.getId());
            verify(funcionarioService, never()).getFuncionario(dto.getFuncionarioId());
            verify(mapper, never()).merge(any(LancamentoEntity.class), any(FuncionarioEntity.class), any(LancamentoDto.class));
            verify(repository, never()).save(any(LancamentoEntity.class));
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void update_idInvalido() {
        try {
            LancamentoDto dto = LancamentoDtoBuilder.create().withData("27/05/2020 21:34:15").withDescricao("Teste").withLocalizacao("Local de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionarioId(1L).build();
            service.update(dto);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("ID do lançamento inválido");
        }

        try {
            verify(repository, never()).findById(any(Long.class));
            verify(funcionarioService, never()).getFuncionario(any(Long.class));
            verify(mapper, never()).merge(any(LancamentoEntity.class), any(FuncionarioEntity.class), any(LancamentoDto.class));
            verify(repository, never()).save(any(LancamentoEntity.class));
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void update_funcionarioNaoEncontrado() {
        LancamentoDto dto = LancamentoDtoBuilder.create().withId(1L).withData("27/05/2020 21:34:15").withDescricao("Teste").withLocalizacao("Local de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionarioId(1L).build();
        FuncionarioEntity funcionario = FuncionarioEntityBuilder.create(1L).build();
        LancamentoEntity lancamento = LancamentoEntityBuilder.create(1L).withData(LocalDateTime.of(2020, 05, 27, 21, 34, 15).toInstant(ZoneOffset.UTC)).withDescricao("Teste X").withLocalizacao("Local de teste Y").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario).build();

        try {
            Optional<LancamentoEntity> lancamentoOpt = Optional.of(lancamento);
            when(repository.findById(dto.getId())).thenReturn(lancamentoOpt);
            when(funcionarioService.getFuncionario(dto.getFuncionarioId())).thenReturn(null);

            service.update(dto);
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (Exception ex) {
            assertThat(ex.getMessage()).isEqualTo("O funcionário informado não existe");
        }

        try {
            verify(repository).findById(dto.getId());
            verify(funcionarioService).getFuncionario(dto.getFuncionarioId());
            verify(mapper, never()).merge(any(LancamentoEntity.class), any(FuncionarioEntity.class), any(LancamentoDto.class));
            verify(repository, never()).save(any(LancamentoEntity.class));
        } catch (Exception ex) {
            failBecauseExceptionWasNotThrown(Exception.class);
        }
    }

    @Test
    public void retrieve() {
        long id = 1L;
        LancamentoEntity lancamento = new LancamentoEntity();
        LancamentoDto dto = new LancamentoDto();

        Optional<LancamentoEntity> lancamentoOpt = Optional.of(lancamento);
        when(repository.findById(id)).thenReturn(lancamentoOpt);
        when(mapper.toDto(lancamento)).thenReturn(dto);

        LancamentoDto result = service.retrieve(id);

        assertThat(result).isNotNull();
        assertThat(result).isSameAs(dto);

        verify(repository).findById(id);
        verify(mapper).toDto(lancamento);
    }

    @Test
    public void retrieve_FuncionarioNaoEncontrado() {
        long id = 2L;

        Optional<LancamentoEntity> lancamentoOpt = Optional.empty();
        when(repository.findById(id)).thenReturn(lancamentoOpt);

        LancamentoDto result = service.retrieve(id);

        assertThat(result).isNull();

        verify(repository).findById(id);
        verify(mapper, never()).toDto(any(LancamentoEntity.class));
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
            assertThat(ex.getMessage()).isEqualTo("ID do lançamento inválido");
        }

        verify(repository, never()).deleteById(id);
    }

    @Test
    public void list() {
        LancamentoDto dto1 = LancamentoDtoBuilder.create().withId(1L).withData("27/05/2020 21:34:15").withDescricao("Teste 1").withLocalizacao("Local de teste 1").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionarioId(1L).build();
        FuncionarioEntity funcionario1 = FuncionarioEntityBuilder.create(1L).build();
        LancamentoEntity lancamento1 = LancamentoEntityBuilder.create(1L).withData(LocalDateTime.of(2020, 05, 27, 21, 34, 15).toInstant(ZoneOffset.UTC)).withDescricao("Teste 1").withLocalizacao("Local de teste 1").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario1).build();

        LancamentoDto dto2 = LancamentoDtoBuilder.create().withId(2L).withData("27/05/2020 10:00:00").withDescricao("Teste 2").withLocalizacao("Local de teste 2").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionarioId(1L).build();
        FuncionarioEntity funcionario2 = FuncionarioEntityBuilder.create(2L).build();
        LancamentoEntity lancamento2 = LancamentoEntityBuilder.create(2L).withData(LocalDateTime.of(2020, 05, 27, 10, 00, 00).toInstant(ZoneOffset.UTC)).withDescricao("Teste 2").withLocalizacao("Local de teste 2").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario2).build();

        List<LancamentoEntity> lancamentos = List.of(lancamento1, lancamento2);
        when(repository.findAll()).thenReturn(lancamentos);

        when(mapper.toDto(lancamento1)).thenReturn(dto1);
        when(mapper.toDto(lancamento2)).thenReturn(dto2);

        List<LancamentoDto> result = service.list();

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
        assertThat(result.get(0)).isSameAs(dto1);
        assertThat(result.get(1)).isSameAs(dto2);

        verify(repository).findAll();
        verify(mapper).toDto(lancamento1);
        verify(mapper).toDto(lancamento2);
    }

    @Test
    public void list_nenhumLancamentoCadastrado() {
        List<LancamentoEntity> lancamentos = new ArrayList<>();
        when(repository.findAll()).thenReturn(lancamentos);

        List<LancamentoDto> result = service.list();

        assertThat(result).isEmpty();

        verify(repository).findAll();
        verify(mapper, never()).toDto(any(LancamentoEntity.class));
    }

    @Test
    public void toListLancamentoFuncOutput() {
        List<LancamentoEntity> lancamentos = new ArrayList<>();

        List<LancamentoFuncOutput> lancamentoFuncOutput = new ArrayList<>();
        when(mapper.toListLancamentoFuncOutput(lancamentos)).thenReturn(lancamentoFuncOutput);

        List<LancamentoFuncOutput> result = service.toListLancamentoFuncOutput(lancamentos);

        assertThat(result).isSameAs(lancamentoFuncOutput);

        verify(mapper).toListLancamentoFuncOutput(lancamentos);
    }
}
