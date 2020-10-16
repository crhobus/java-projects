package br.com.app.divida.dao;

import org.springframework.stereotype.Repository;

import br.com.app.infra.database.RepositoryBase;

@Repository
public interface DividaRepository extends RepositoryBase<DividaEntity>, DividaRepositoryCustom {

}
