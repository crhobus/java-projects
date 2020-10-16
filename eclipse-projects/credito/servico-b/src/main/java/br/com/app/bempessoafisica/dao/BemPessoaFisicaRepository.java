package br.com.app.bempessoafisica.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.app.infra.database.RepositoryBase;

@Repository
public interface BemPessoaFisicaRepository extends RepositoryBase<BemPessoaFisicaEntity>, BemPessoaFisicaRepositoryCustom {

    public Optional<BemPessoaFisicaEntity> findByCpfAndId(String cpf, long id);

    public List<BemPessoaFisicaEntity> findByCpf(String cpf);

}
