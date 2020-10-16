package br.com.app.movimentacaofinanceira.dao;

import java.time.LocalDate;
import java.util.List;

import com.querydsl.core.Tuple;

import br.com.app.infra.database.RepositoryCustomBase;

public interface MovimentacaoFinanceiraRepositoryCustom extends RepositoryCustomBase<MovimentacaoFinanceiraEntity> {

    public List<Tuple> findUltimasMovimentacoesFinanceiras(String cpf, LocalDate dataReferencia, int quantidadeMeses);
}
