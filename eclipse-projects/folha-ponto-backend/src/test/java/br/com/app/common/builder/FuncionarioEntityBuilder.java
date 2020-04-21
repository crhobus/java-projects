package br.com.app.common.builder;

import java.math.BigDecimal;

import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.funcionario.dao.FuncionarioEntity;
import br.com.app.usuario.dao.UsuarioEntity;

public class FuncionarioEntityBuilder {

    private FuncionarioEntity entity;

    public static FuncionarioEntityBuilder create(long id) {
        FuncionarioEntityBuilder builder = new FuncionarioEntityBuilder();
        builder.entity = new FuncionarioEntity();
        builder.entity.setId(id);
        return builder;
    }

    public static FuncionarioEntityBuilder create() {
        FuncionarioEntityBuilder builder = new FuncionarioEntityBuilder();
        builder.entity = new FuncionarioEntity();
        return builder;
    }

    public FuncionarioEntityBuilder withNome(String nome) {
        entity.setNome(nome);
        return this;
    }

    public FuncionarioEntityBuilder withCpf(String cpf) {
        entity.setCpf(cpf);
        return this;
    }

    public FuncionarioEntityBuilder withRg(String rg) {
        entity.setRg(rg);
        return this;
    }

    public FuncionarioEntityBuilder withValorHora(BigDecimal valorHora) {
        entity.setValorHora(valorHora);
        return this;
    }

    public FuncionarioEntityBuilder withQtHorasTrabalhoDia(BigDecimal qtHorasTrabalhoDia) {
        entity.setQtHorasTrabalhoDia(qtHorasTrabalhoDia);
        return this;
    }

    public FuncionarioEntityBuilder withQtHorasAlmoco(BigDecimal qtHorasAlmoco) {
        entity.setQtHorasAlmoco(qtHorasAlmoco);
        return this;
    }

    public FuncionarioEntityBuilder withEmpresa(EmpresaEntity empresa) {
        entity.setEmpresa(empresa);
        return this;
    }

    public FuncionarioEntityBuilder withUsuario(UsuarioEntity usuario) {
        entity.setUsuario(usuario);
        return this;
    }

    public FuncionarioEntity build() {
        return entity;
    }
}
