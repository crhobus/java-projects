package br.com.app.funcionario;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.app.CommonBaseTest;
import br.com.app.empresa.EmpresaService;
import br.com.app.funcionario.dao.FuncionarioRepository;
import br.com.app.lancamento.LancamentoService;
import br.com.app.usuario.UsuarioService;

public class FuncionarioServiceTest extends CommonBaseTest {

    @Mock
    private FuncionarioRepository repository;

    @Mock
    private FuncionarioMapper mapper;

    @Mock
    private EmpresaService empresaService;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private LancamentoService lancamentoService;

    @InjectMocks
    private FuncionarioService service;

    @Override
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
        //
    }
}
