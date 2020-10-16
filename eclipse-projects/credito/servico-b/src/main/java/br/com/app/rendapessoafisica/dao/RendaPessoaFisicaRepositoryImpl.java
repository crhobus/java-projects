package br.com.app.rendapessoafisica.dao;

import java.util.List;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.app.infra.database.RepositoryBaseImpl;

public class RendaPessoaFisicaRepositoryImpl extends RepositoryBaseImpl<RendaPessoaFisicaEntity> implements RendaPessoaFisicaRepositoryCustom {

    @Override
    public List<Tuple> findUltimosRegistrosRenda(String cpf, int quantidade) {
        QRendaPessoaFisicaEntity rendaPessoaFisica = QRendaPessoaFisicaEntity.rendaPessoaFisicaEntity;

        JPAQuery<Tuple> query = select(rendaPessoaFisica.renda, rendaPessoaFisica.idade, rendaPessoaFisica.dataRenda) //
                .from(rendaPessoaFisica) //
                .where(rendaPessoaFisica.cpf.eq(cpf)) //
                .orderBy(rendaPessoaFisica.dataRenda.desc()) //
                .limit(quantidade);

        return query.fetch();
    }

}
