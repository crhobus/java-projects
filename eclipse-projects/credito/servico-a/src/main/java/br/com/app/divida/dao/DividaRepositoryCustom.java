package br.com.app.divida.dao;

import java.util.List;
import java.util.Optional;

import br.com.app.infra.database.RepositoryCustomBase;

public interface DividaRepositoryCustom extends RepositoryCustomBase<DividaEntity> {

    public Optional<DividaEntity> findByIdAndCpf(long id, String cpf);

    public List<DividaEntity> findAllByCpfAndNotQuitado(String cpf);

    public boolean existsDividaByCpf(String cpf);
}
