package br.com.app.common.builder;

import br.com.app.lancamento.dto.LancamentoDto;
import br.com.app.lancamento.dto.TipoEnum;

public class LancamentoDtoBuilder {

    private LancamentoDto dto;

    public static LancamentoDtoBuilder create() {
        LancamentoDtoBuilder builder = new LancamentoDtoBuilder();
        builder.dto = new LancamentoDto();
        return builder;
    }

    public LancamentoDtoBuilder withId(long id) {
        dto.setId(id);
        return this;
    }

    public LancamentoDtoBuilder withData(String data) {
        dto.setData(data);
        return this;
    }

    public LancamentoDtoBuilder withDescricao(String descricao) {
        dto.setDescricao(descricao);
        return this;
    }

    public LancamentoDtoBuilder withLocalizacao(String localizacao) {
        dto.setLocalizacao(localizacao);
        return this;
    }

    public LancamentoDtoBuilder withTipo(TipoEnum tipo) {
        dto.setTipo(tipo);
        return this;
    }

    public LancamentoDtoBuilder withFuncionarioId(Long funcionarioId) {
        dto.setFuncionarioId(funcionarioId);
        return this;
    }

    public LancamentoDto build() {
        return dto;
    }
}
