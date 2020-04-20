package br.com.app.empresa;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.app.DatabaseTest;
import br.com.app.common.builder.EmpresaEntityBuilder;
import br.com.app.empresa.dao.EmpresaEntity;
import br.com.app.empresa.dao.EmpresaRepository;

public class EmpresaRepositoryTest extends DatabaseTest {

    @Autowired
    private EmpresaRepository repository;

    @Override
    public void setUp() {
        repository.deleteAllInBatch();
    }

    @Test
    public void findByCnpj() {
        EmpresaEntity empresa1 = EmpresaEntityBuilder.create().withNome("Empresa Teste X").withCnpj("12345678000011").build();
        repository.save(empresa1);

        EmpresaEntity empresa2 = EmpresaEntityBuilder.create().withNome("Empresa Teste Y").withCnpj("12345678000012").build();
        repository.save(empresa2);

        EmpresaEntity empresa3 = EmpresaEntityBuilder.create().withNome("Empresa Teste Z").withCnpj("12345678000013").build();
        repository.save(empresa3);

        EmpresaEntity result = repository.findByCnpj("12345678000012");

        assertThat(result).isSameAs(empresa2);
    }

}
