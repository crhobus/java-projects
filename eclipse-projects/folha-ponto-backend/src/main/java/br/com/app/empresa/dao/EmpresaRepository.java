package br.com.app.empresa.dao;

import org.springframework.stereotype.Repository;

import br.com.app.infra.database.RepositoryBase;

@Repository
public interface EmpresaRepository extends RepositoryBase<EmpresaEntity> {

    public EmpresaEntity findByCnpj(String cnpj);

}