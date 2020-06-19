package br.com.app.lancamento;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import br.com.app.CommonBaseTest;
import br.com.app.common.builder.FuncionarioEntityBuilder;
import br.com.app.common.builder.LancamentoDtoBuilder;
import br.com.app.common.builder.LancamentoEntityBuilder;
import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.lancamento.dao.LancamentoEntity;
import br.com.app.lancamento.dto.LancamentoDto;
import br.com.app.lancamento.dto.LancamentoFuncOutput;
import br.com.app.lancamento.dto.TipoEnum;

public class LancamentoMapperTest extends CommonBaseTest {

    @InjectMocks
    private LancamentoMapper mapper;

    @Override
    public void setUp() {}

    @Test
    public void toEntity() {
        LancamentoDto dto = LancamentoDtoBuilder.create().withData("27/05/2020 21:34:15").withDescricao("Teste").withLocalizacao("Local de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionarioId(1L).build();
        FuncionarioEntity funcionario = new FuncionarioEntity();

        LancamentoEntity result = mapper.toEntity(dto, funcionario);

        assertThat(result).isNotNull();
        assertThat(result.getData()).isEqualTo(LocalDateTime.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).toInstant(ZoneOffset.UTC));
        assertThat(result.getDescricao()).isEqualTo(dto.getDescricao());
        assertThat(result.getLocalizacao()).isEqualTo(dto.getLocalizacao());
        assertThat(result.getTipo()).isEqualTo(dto.getTipo());
        assertThat(result.getFuncionario()).isSameAs(funcionario);
    }

    @Test
    public void merge() {
        LancamentoDto dto = LancamentoDtoBuilder.create().withData("27/05/2020 21:34:15").withDescricao("Teste").withLocalizacao("Local de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionarioId(1L).build();
        FuncionarioEntity funcionario = new FuncionarioEntity();
        LancamentoEntity lancamento = LancamentoEntityBuilder.create(1L).withData(LocalDateTime.of(2020, 05, 27, 21, 34, 15).toInstant(ZoneOffset.UTC)).withDescricao("Teste ABC").withLocalizacao("Local AA de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario).build();

        mapper.merge(lancamento, funcionario, dto);

        assertThat(lancamento.getData()).isEqualTo(LocalDateTime.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")).toInstant(ZoneOffset.UTC));
        assertThat(lancamento.getDescricao()).isEqualTo(dto.getDescricao());
        assertThat(lancamento.getLocalizacao()).isEqualTo(dto.getLocalizacao());
        assertThat(lancamento.getTipo()).isEqualTo(dto.getTipo());
        assertThat(lancamento.getFuncionario()).isSameAs(funcionario);
    }

    @Test
    public void toDto() {
        FuncionarioEntity funcionario = FuncionarioEntityBuilder.create(1L).build();
        LancamentoEntity lancamento = LancamentoEntityBuilder.create(1L).withData(LocalDateTime.of(2020, 05, 27, 21, 34, 15).toInstant(ZoneOffset.UTC)).withDescricao("Teste ABC").withLocalizacao("Local AA de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario).build();

        LancamentoDto result = mapper.toDto(lancamento);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(lancamento.getId());
        assertThat(result.getData()).isEqualTo(lancamento.getData().atOffset(ZoneOffset.UTC).toLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        assertThat(result.getDescricao()).isEqualTo(lancamento.getDescricao());
        assertThat(result.getLocalizacao()).isEqualTo(lancamento.getLocalizacao());
        assertThat(result.getTipo()).isEqualTo(lancamento.getTipo());
        assertThat(result.getFuncionarioId()).isSameAs(lancamento.getFuncionario().getId());
    }

    @Test
    public void toListLancamentoFuncOutput() {
        FuncionarioEntity funcionario1 = FuncionarioEntityBuilder.create(1L).build();
        LancamentoEntity lancamento1 = LancamentoEntityBuilder.create(1L).withData(LocalDateTime.of(2020, 05, 27, 8, 00, 00).toInstant(ZoneOffset.UTC)).withDescricao("Teste ABC").withLocalizacao("Local AA de teste").withTipo(TipoEnum.INICIO_TRABALHO).withFuncionario(funcionario1).build();

        FuncionarioEntity funcionario2 = FuncionarioEntityBuilder.create(1L).build();
        LancamentoEntity lancamento2 = LancamentoEntityBuilder.create(1L).withData(LocalDateTime.of(2020, 05, 27, 18, 00, 00).toInstant(ZoneOffset.UTC)).withDescricao("Teste DEF").withLocalizacao("Local BB de teste").withTipo(TipoEnum.TERMINO_TRABALHO).withFuncionario(funcionario2).build();

        List<LancamentoEntity> lancamentos = new ArrayList<>();
        lancamentos.add(lancamento1);
        lancamentos.add(lancamento2);

        List<LancamentoFuncOutput> result = mapper.toListLancamentoFuncOutput(lancamentos);

        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);

        assertThat(result.get(0).getData()).isEqualTo(lancamento1.getData().atOffset(ZoneOffset.UTC).toLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        assertThat(result.get(0).getDescricao()).isEqualTo(lancamento1.getDescricao());
        assertThat(result.get(0).getLocalizacao()).isEqualTo(lancamento1.getLocalizacao());
        assertThat(result.get(0).getTipo()).isEqualTo(lancamento1.getTipo());

        assertThat(result.get(1).getData()).isEqualTo(lancamento2.getData().atOffset(ZoneOffset.UTC).toLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        assertThat(result.get(1).getDescricao()).isEqualTo(lancamento2.getDescricao());
        assertThat(result.get(1).getLocalizacao()).isEqualTo(lancamento2.getLocalizacao());
        assertThat(result.get(1).getTipo()).isEqualTo(lancamento2.getTipo());
    }

    @Test
    public void toListLancamentoFuncOutput_nenhumLancamento() {
        List<LancamentoFuncOutput> result = mapper.toListLancamentoFuncOutput(new ArrayList<>());

        assertThat(result).isEmpty();
    }
}
