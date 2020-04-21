package br.com.app.funcionario.dao;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.app.empresa.dao.QEmpresaEntity;
import br.com.app.infra.database.RepositoryBaseImpl;
import br.com.app.lancamento.dao.QLancamentoEntity;

public class FuncionarioRepositoryImpl extends RepositoryBaseImpl<FuncionarioEntity> implements FuncionarioRepositoryCustom {

    @Override
    public List<Tuple> findByEmpresaWithIncorrectLancamentos(String cnpj, LocalDate referenceDate) {
        QFuncionarioEntity funcionario = QFuncionarioEntity.funcionarioEntity;
        QEmpresaEntity empresa = QEmpresaEntity.empresaEntity;
        QLancamentoEntity lancamento = QLancamentoEntity.lancamentoEntity;
        QLancamentoEntity lancamentoSub = new QLancamentoEntity("subLancamentoEntity");

        Instant dataIni = referenceDate.with(TemporalAdjusters.firstDayOfMonth()).atTime(0, 0, 0).atOffset(ZoneOffset.UTC).toInstant();
        Instant dataFim = referenceDate.with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59).atOffset(ZoneOffset.UTC).toInstant();

        JPAQuery<Long> subQuery = select(lancamentoSub.count()) //
                .from(lancamentoSub) //
                .where(lancamentoSub.data.between(dataIni, dataFim) //
                        .and(lancamentoSub.funcionario.id.eq(funcionario.id)) //
                        .and(lancamentoSub.data.dayOfMonth().eq(lancamento.data.dayOfMonth())));

        JPAQuery<Tuple> query = select(funcionario.cpf, funcionario.nome, lancamento.data.dayOfMonth(), lancamento.count()) //
                .from(funcionario) //
                .innerJoin(empresa).on(empresa.id.eq(funcionario.empresa.id)) //
                .innerJoin(lancamento).on(lancamento.funcionario.id.eq(funcionario.id)) //
                .where(empresa.cnpj.eq(cnpj) //
                        .and(lancamento.data.between(dataIni, dataFim)) //
                        .and(subQuery.ne(4l))) //
                .groupBy(funcionario.cpf, funcionario.nome, lancamento.data.dayOfMonth()) //
                .orderBy(lancamento.data.dayOfMonth().asc());

        return query.fetch();
    }

}
