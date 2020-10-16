package br.com.app.bempessoafisica.dao;

import java.math.BigDecimal;

import com.querydsl.jpa.impl.JPAQuery;

import br.com.app.infra.database.RepositoryBaseImpl;

public class BemPessoaFisicaRepositoryImpl extends RepositoryBaseImpl<BemPessoaFisicaEntity> implements BemPessoaFisicaRepositoryCustom {

    @Override
    public BigDecimal somaValoresBensCpf(String cpf) {
        QBemPessoaFisicaEntity bemPessoaFisica = QBemPessoaFisicaEntity.bemPessoaFisicaEntity;

        JPAQuery<BigDecimal> query = select(bemPessoaFisica.valor.sum()) //
                .from(bemPessoaFisica) //
                .where(bemPessoaFisica.cpf.eq(cpf));

        return query.fetchOne();
    }

}
