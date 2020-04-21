package br.com.app.common.builder;

import java.time.Instant;

import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.lancamento.dao.LancamentoEntity;
import br.com.app.lancamento.dto.TipoEnum;

public class LancamentoEntityBuilder {

    private LancamentoEntity entity;

    public static LancamentoEntityBuilder create(long id) {
        LancamentoEntityBuilder builder = new LancamentoEntityBuilder();
        builder.entity = new LancamentoEntity();
        builder.entity.setId(id);
        return builder;
    }

    public static LancamentoEntityBuilder create() {
        LancamentoEntityBuilder builder = new LancamentoEntityBuilder();
        builder.entity = new LancamentoEntity();
        return builder;
    }

    public LancamentoEntityBuilder withData(Instant data) {
        entity.setData(data);
        return this;
    }

    public LancamentoEntityBuilder withDescricao(String descricao) {
        entity.setDescricao(descricao);
        return this;
    }

    public LancamentoEntityBuilder withLocalizacao(String localizacao) {
        entity.setLocalizacao(localizacao);
        return this;
    }

    public LancamentoEntityBuilder withTipo(TipoEnum tipo) {
        entity.setTipo(tipo);
        return this;
    }

    public LancamentoEntityBuilder withFuncionario(FuncionarioEntity funcionario) {
        entity.setFuncionario(funcionario);
        return this;
    }

    public LancamentoEntity build() {
        return entity;
    }
}
