package br.com.app.gastoscartaocredito.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.app.infra.database.RepositoryBase;

@Repository
public interface GastosCartaoCreditoRepository extends RepositoryBase<GastosCartaoCreditoEntity>, GastosCartaoCreditoRepositoryCustom {

    public Optional<GastosCartaoCreditoEntity> findByCpfAndId(String cpf, long id);

    public List<GastosCartaoCreditoEntity> findByCpf(String cpf);
}
