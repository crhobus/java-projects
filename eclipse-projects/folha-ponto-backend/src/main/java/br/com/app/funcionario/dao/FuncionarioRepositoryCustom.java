package br.com.app.funcionario.dao;

import java.time.LocalDate;
import java.util.List;

import com.querydsl.core.Tuple;

import br.com.app.infra.database.RepositoryCustomBase;

public interface FuncionarioRepositoryCustom extends RepositoryCustomBase<FuncionarioEntity> {

    public List<Tuple> findByEmpresaWithIncorrectLancamentos(String cnpj, LocalDate referenceDate);

    public List<Object[]> findVlSalarioByFuncionario();
}
