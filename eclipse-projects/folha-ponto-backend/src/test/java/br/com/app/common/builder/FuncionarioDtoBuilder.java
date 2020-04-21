package br.com.app.common.builder;

import java.math.BigDecimal;

import br.com.app.funcionario.dto.FuncionarioDto;

public class FuncionarioDtoBuilder {

    private FuncionarioDto dto;

    public static FuncionarioDtoBuilder create() {
        FuncionarioDtoBuilder builder = new FuncionarioDtoBuilder();
        builder.dto = new FuncionarioDto();
        return builder;
    }

    public FuncionarioDtoBuilder withId(long id) {
        dto.setId(id);
        return this;
    }

    public FuncionarioDtoBuilder withNome(String nome) {
        dto.setNome(nome);
        return this;
    }

    public FuncionarioDtoBuilder withCpf(String cpf) {
        dto.setCpf(cpf);
        return this;
    }

    public FuncionarioDtoBuilder withRg(String rg) {
        dto.setRg(rg);
        return this;
    }

    public FuncionarioDtoBuilder withValorHora(BigDecimal valorHora) {
        dto.setValorHora(valorHora);
        return this;
    }

    public FuncionarioDtoBuilder withQtHorasTrabalhoDia(BigDecimal qtHorasTrabalhoDia) {
        dto.setQtHorasTrabalhoDia(qtHorasTrabalhoDia);
        return this;
    }

    public FuncionarioDtoBuilder withQtHorasAlmoco(BigDecimal qtHorasAlmoco) {
        dto.setQtHorasAlmoco(qtHorasAlmoco);
        return this;
    }

    public FuncionarioDtoBuilder withEmpresaId(Long empresaId) {
        dto.setEmpresaId(empresaId);
        return this;
    }

    public FuncionarioDtoBuilder withEmail(String email) {
        dto.setEmail(email);
        return this;
    }

    public FuncionarioDtoBuilder withSenha(String senha) {
        dto.setSenha(senha);
        return this;
    }

    public FuncionarioDtoBuilder withSituacao(String situacao) {
        dto.setSituacao(situacao);
        return this;
    }

    public FuncionarioDtoBuilder withPerfil(String perfil) {
        dto.setPerfil(perfil);
        return this;
    }

    public FuncionarioDto build() {
        return dto;
    }
}
