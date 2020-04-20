package br.com.app.common.builder;

import br.com.app.empresa.dto.EmpresaDto;

public class EmpresaDtoBuilder {

    private EmpresaDto dto;

    public static EmpresaDtoBuilder create() {
        EmpresaDtoBuilder builder = new EmpresaDtoBuilder();
        builder.dto = new EmpresaDto();
        return builder;
    }

    public EmpresaDtoBuilder withId(long id) {
        dto.setId(id);
        return this;
    }

    public EmpresaDtoBuilder withNome(String nome) {
        dto.setNome(nome);
        return this;
    }

    public EmpresaDtoBuilder withCnpj(String cnpj) {
        dto.setCnpj(cnpj);
        return this;
    }

    public EmpresaDto build() {
        return dto;
    }

}
