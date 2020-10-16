package br.com.app.pessoafisica.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import br.com.app.infra.database.RepositoryBase;

@Repository
public interface PessoaFisicaRepository extends RepositoryBase<PessoaFisicaEntity> {

    public Optional<PessoaFisicaEntity> findByCpf(String cpf);

    @Modifying
    public void deleteByCpf(String cpf);

    public default boolean existsByCpf(String cpf) {
        QPessoaFisicaEntity pessoaFisica = QPessoaFisicaEntity.pessoaFisicaEntity;
        return exists(pessoaFisica.cpf.eq(cpf));
    }
}
