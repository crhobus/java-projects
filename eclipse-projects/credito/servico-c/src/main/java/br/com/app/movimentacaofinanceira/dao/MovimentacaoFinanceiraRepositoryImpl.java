package br.com.app.movimentacaofinanceira.dao;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.app.infra.database.RepositoryBaseImpl;

public class MovimentacaoFinanceiraRepositoryImpl extends RepositoryBaseImpl<MovimentacaoFinanceiraEntity> implements MovimentacaoFinanceiraRepositoryCustom {

    @Override
    public List<Tuple> findUltimasMovimentacoesFinanceiras(String cpf, LocalDate dataReferencia, int quantidadeMeses) {
        Instant dataIni = dataReferencia.minusMonths(quantidadeMeses).atStartOfDay().atOffset(ZoneOffset.UTC).toInstant();
        Instant dataFim = dataReferencia.atTime(23, 59, 59).atOffset(ZoneOffset.UTC).toInstant();

        QMovimentacaoFinanceiraEntity movimentacaoFinanceira = QMovimentacaoFinanceiraEntity.movimentacaoFinanceiraEntity;

        JPAQuery<Tuple> query = select(movimentacaoFinanceira.valor, movimentacaoFinanceira.dataMovimentacao, movimentacaoFinanceira.operacao) //
                .from(movimentacaoFinanceira) //
                .where(movimentacaoFinanceira.cpf.eq(cpf) //
                        .and(movimentacaoFinanceira.dataMovimentacao.between(dataIni, dataFim))) //
                .orderBy(movimentacaoFinanceira.dataMovimentacao.desc());

        return query.fetch();
    }

}
