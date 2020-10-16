package br.com.app.bempessoafisica.dao;

import java.math.BigDecimal;

import br.com.app.infra.database.RepositoryCustomBase;

public interface BemPessoaFisicaRepositoryCustom extends RepositoryCustomBase<BemPessoaFisicaEntity> {

    public BigDecimal somaValoresBensCpf(String cpf);
}
