package br.com.app.rendapessoafisica.dao;

import java.util.List;

import com.querydsl.core.Tuple;

import br.com.app.infra.database.RepositoryCustomBase;

public interface RendaPessoaFisicaRepositoryCustom extends RepositoryCustomBase<RendaPessoaFisicaEntity> {

    public List<Tuple> findUltimosRegistrosRenda(String cpf, int quantidade);
}
