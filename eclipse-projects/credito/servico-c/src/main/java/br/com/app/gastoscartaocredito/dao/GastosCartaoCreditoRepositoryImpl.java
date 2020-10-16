package br.com.app.gastoscartaocredito.dao;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.app.infra.database.RepositoryBaseImpl;

public class GastosCartaoCreditoRepositoryImpl extends RepositoryBaseImpl<GastosCartaoCreditoEntity> implements GastosCartaoCreditoRepositoryCustom {

    @Override
    public List<Tuple> findUltimosGastosCartaoCredito(String cpf, LocalDate dataReferencia, int quantidadeMeses) {
        QGastosCartaoCreditoEntity gastosCartaoCredito = QGastosCartaoCreditoEntity.gastosCartaoCreditoEntity;

        Instant dataIni = dataReferencia.minusMonths(quantidadeMeses).atStartOfDay().atOffset(ZoneOffset.UTC).toInstant();
        Instant dataFim = dataReferencia.atTime(23, 59, 59).atOffset(ZoneOffset.UTC).toInstant();

        StringPath alias = Expressions.stringPath("_data");
        StringTemplate data = Expressions.stringTemplate("to_char({0}, {1})", gastosCartaoCredito.data, "MM/YYYY");

        JPAQuery<Tuple> query = select(data.as(alias), gastosCartaoCredito.valor.sum()) //
                .from(gastosCartaoCredito) //
                .where(gastosCartaoCredito.cpf.eq(cpf) //
                        .and(gastosCartaoCredito.data.between(dataIni, dataFim))) //
                .groupBy(alias) //
                .orderBy(alias.desc());

        return query.fetch();
    }

}
