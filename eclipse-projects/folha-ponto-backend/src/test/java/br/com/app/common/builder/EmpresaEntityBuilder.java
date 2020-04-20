package br.com.app.common.builder;

import br.com.app.empresa.dao.EmpresaEntity;

public class EmpresaEntityBuilder {

    private EmpresaEntity entity;

    public static EmpresaEntityBuilder create(long id) {
        EmpresaEntityBuilder builder = new EmpresaEntityBuilder();
        builder.entity = new EmpresaEntity();
        builder.entity.setId(id);
        return builder;
    }

    public static EmpresaEntityBuilder create() {
        EmpresaEntityBuilder builder = new EmpresaEntityBuilder();
        builder.entity = new EmpresaEntity();
        return builder;
    }

    public EmpresaEntityBuilder withNome(String nome) {
        entity.setNome(nome);
        return this;
    }

    public EmpresaEntityBuilder withCnpj(String cnpj) {
        entity.setCnpj(cnpj);
        return this;
    }

    public EmpresaEntity build() {
        return entity;
    }
}
