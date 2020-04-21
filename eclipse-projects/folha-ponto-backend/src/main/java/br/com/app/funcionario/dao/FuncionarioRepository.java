package br.com.app.funcionario.dao;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.infra.database.RepositoryBase;

@Repository
public interface FuncionarioRepository extends RepositoryBase<FuncionarioEntity>, FuncionarioRepositoryCustom {

    public FuncionarioEntity findByCpf(String cpf);

    @Modifying
    @Query("update br.com.app.funcionario.dao.FuncionarioEntity f set f.qtHorasTrabalhoDia = :qtHorasTrabalhoDia, f.qtHorasAlmoco = :qtHorasAlmoco, f.dataAtualizacao = CURRENT_TIMESTAMP where f.empresa = :empresa")
    public Integer updateHorariosByEmpresa(@Param("qtHorasTrabalhoDia") BigDecimal qtHorasTrabalhoDia, @Param("qtHorasAlmoco") BigDecimal qtHorasAlmoco, @Param("empresa") EmpresaEntity empresa);
}
