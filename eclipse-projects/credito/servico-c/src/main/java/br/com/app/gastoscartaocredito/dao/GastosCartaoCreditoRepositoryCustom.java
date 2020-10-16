package br.com.app.gastoscartaocredito.dao;

import java.time.LocalDate;
import java.util.List;

import com.querydsl.core.Tuple;

import br.com.app.infra.database.RepositoryCustomBase;

public interface GastosCartaoCreditoRepositoryCustom extends RepositoryCustomBase<GastosCartaoCreditoEntity> {

    public List<Tuple> findUltimosGastosCartaoCredito(String cpf, LocalDate dataReferencia, int quantidadeMeses);
}
