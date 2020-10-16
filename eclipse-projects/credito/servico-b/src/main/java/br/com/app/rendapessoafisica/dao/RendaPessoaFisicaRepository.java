package br.com.app.rendapessoafisica.dao;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.app.infra.database.RepositoryBase;

@Repository
public interface RendaPessoaFisicaRepository extends RepositoryBase<RendaPessoaFisicaEntity>, RendaPessoaFisicaRepositoryCustom {

    public Optional<RendaPessoaFisicaEntity> findByCpfAndId(String cpf, long id);
}
