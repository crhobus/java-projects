package br.com.app.funcionario.dao;

import org.springframework.stereotype.Repository;

import br.com.app.infra.database.RepositoryBase;

@Repository
public interface FuncionarioRepository extends RepositoryBase<FuncionarioEntity>, FuncionarioRepositoryCustom {

    public FuncionarioEntity findByCpf(String cpf);
}
