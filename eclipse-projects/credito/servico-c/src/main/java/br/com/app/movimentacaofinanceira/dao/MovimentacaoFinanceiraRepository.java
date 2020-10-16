package br.com.app.movimentacaofinanceira.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.app.infra.database.RepositoryBase;

@Repository
public interface MovimentacaoFinanceiraRepository extends RepositoryBase<MovimentacaoFinanceiraEntity>, MovimentacaoFinanceiraRepositoryCustom {

    public Optional<MovimentacaoFinanceiraEntity> findByCpfAndId(String cpf, long id);

    public List<MovimentacaoFinanceiraEntity> findByCpf(String cpf);
}
