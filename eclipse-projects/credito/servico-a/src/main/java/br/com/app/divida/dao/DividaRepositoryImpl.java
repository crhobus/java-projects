package br.com.app.divida.dao;

import java.util.List;
import java.util.Optional;

import com.querydsl.jpa.impl.JPAQuery;

import br.com.app.divida.dto.QuitadoEnum;
import br.com.app.infra.database.RepositoryBaseImpl;
import br.com.app.pessoafisica.dao.QPessoaFisicaEntity;

public class DividaRepositoryImpl extends RepositoryBaseImpl<DividaEntity> implements DividaRepositoryCustom {

    @Override
    public Optional<DividaEntity> findByIdAndCpf(long id, String cpf) {
        QPessoaFisicaEntity pessoaFisica = QPessoaFisicaEntity.pessoaFisicaEntity;
        QDividaEntity divida = QDividaEntity.dividaEntity;

        JPAQuery<DividaEntity> query = select(divida) //
                .from(divida) //
                .innerJoin(divida.pessoaFisica, pessoaFisica) //
                .where(divida.id.eq(id) //
                        .and(pessoaFisica.cpf.eq(cpf)));

        return Optional.ofNullable(query.fetchOne());
    }

    @Override
    public List<DividaEntity> findAllByCpfAndNotQuitado(String cpf) {
        QPessoaFisicaEntity pessoaFisica = QPessoaFisicaEntity.pessoaFisicaEntity;
        QDividaEntity divida = QDividaEntity.dividaEntity;

        JPAQuery<DividaEntity> query = select(divida) //
                .from(divida) //
                .innerJoin(divida.pessoaFisica, pessoaFisica) //
                .where(pessoaFisica.cpf.eq(cpf) //
                        .and(divida.quitado.eq(QuitadoEnum.NAO)));

        return query.fetch();
    }

    @Override
    public boolean existsDividaByCpf(String cpf) {
        QPessoaFisicaEntity pessoaFisica = QPessoaFisicaEntity.pessoaFisicaEntity;
        QDividaEntity divida = QDividaEntity.dividaEntity;

        JPAQuery<DividaEntity> query = select(divida) //
                .from(divida) //
                .innerJoin(divida.pessoaFisica, pessoaFisica) //
                .where(pessoaFisica.cpf.eq(cpf) //
                        .and(divida.quitado.eq(QuitadoEnum.NAO)));

        return query.fetchCount() > 0;
    }

}
