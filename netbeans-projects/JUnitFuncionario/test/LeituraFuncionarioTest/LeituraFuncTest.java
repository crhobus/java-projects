package LeituraFuncionarioTest;

import LeituraFuncionario.Funcionario;
import LeituraFuncionario.LeituraFunc;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LeituraFuncTest {

    private LeituraFunc leituraFunc;

    public LeituraFuncTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        leituraFunc = new LeituraFunc();
    }

    @After
    public void tearDown() {
        leituraFunc = null;
    }

    //testes diretorio
    @Test
    public void testDiretorio1() {
        leituraFunc.setDiretorio("C:\\Users\\crhobus\\MyDataFiles\\Projects\\java-projects\\netbeans-projects\\JUnitFuncionario\\ArqFuncionarios");
        assertNotNull(leituraFunc.getDiretorio());
    }

    @Test
    public void testDiretorio2() {
        leituraFunc.setDiretorio("C:\\Users\\crhobus\\MyDataFiles\\Projects\\java-projects\\netbeans-projects\\JUnitFuncionario");
        assertNotNull(leituraFunc.getDiretorio());
    }

    //testes qtdadeFunc
    @Test
    public void testQtdadeFunc1() {
        leituraFunc.setDiretorio("C:\\Users\\crhobus\\MyDataFiles\\Projects\\java-projects\\netbeans-projects\\JUnitFuncionario\\ArqFuncionarios");
        assertEquals(0, leituraFunc.getQtdadeFunc());
    }

    @Test
    public void testQtdadeFunc2() {
        leituraFunc.setDiretorio("C:\\Users\\crhobus\\MyDataFiles\\Projects\\java-projects\\netbeans-projects\\JUnitFuncionario\\ArqFuncionarios");
        try {
            leituraFunc.lerFuncs();
        } catch (IOException ex) {
            fail();
        } catch (NoSuchMethodException ex) {
            fail();
        } catch (IllegalAccessException ex) {
            fail();
        } catch (IllegalArgumentException ex) {
            fail();
        } catch (InvocationTargetException ex) {
            fail();
        } catch (NoSuchFieldException ex) {
            fail();
        } catch (ParseException ex) {
            fail();
        } catch (Exception ex) {
            fail();
        }
        assertEquals(6, leituraFunc.getQtdadeFunc());
    }

    //testes de leitura de funcionario
    @Test
    public void testLerFuncs1() {
        leituraFunc.setDiretorio("C:\\Users\\crhobus\\MyDataFiles\\Projects\\java-projects\\netbeans-projects\\JUnitFuncionario");
        try {
            leituraFunc.lerFuncs();
            fail();
        } catch (IOException ex) {
            assertEquals(ex.getMessage(), "Erro ao abrir o ArqFuncionarios para leitura");
        } catch (NoSuchMethodException ex) {
            fail();
        } catch (IllegalAccessException ex) {
            fail();
        } catch (IllegalArgumentException ex) {
            fail();
        } catch (InvocationTargetException ex) {
            fail();
        } catch (NoSuchFieldException ex) {
            fail();
        } catch (ParseException ex) {
            fail();
        } catch (Exception ex) {
            fail();
        }
    }

    @Test
    public void testLerFuncs2() {
        leituraFunc.setDiretorio("C:\\Users\\crhobus\\MyDataFiles\\Images");
        try {
            leituraFunc.lerFuncs();
            fail();
        } catch (IOException ex) {
            fail();
        } catch (NoSuchMethodException ex) {
            fail();
        } catch (IllegalAccessException ex) {
            fail();
        } catch (IllegalArgumentException ex) {
            fail();
        } catch (InvocationTargetException ex) {
            fail();
        } catch (NoSuchFieldException ex) {
            fail();
        } catch (ParseException ex) {
            fail();
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Erro na leitura do arquivo");
        }
    }

    @Test
    public void testLerFuncs3() {
        try {
            leituraFunc.lerFuncs();
            fail();
        } catch (IOException ex) {
            fail();
        } catch (NoSuchMethodException ex) {
            fail();
        } catch (IllegalAccessException ex) {
            fail();
        } catch (IllegalArgumentException ex) {
            fail();
        } catch (InvocationTargetException ex) {
            fail();
        } catch (NoSuchFieldException ex) {
            fail();
        } catch (ParseException ex) {
            fail();
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "informe um diretório válido");
        }
    }

    @Test
    public void testLerFuncs4() {
        leituraFunc.setDiretorio("C:/ua");
        try {
            leituraFunc.lerFuncs();
            fail();
        } catch (IOException ex) {
            fail();
        } catch (NoSuchMethodException ex) {
            fail();
        } catch (IllegalAccessException ex) {
            fail();
        } catch (IllegalArgumentException ex) {
            fail();
        } catch (InvocationTargetException ex) {
            fail();
        } catch (NoSuchFieldException ex) {
            fail();
        } catch (ParseException ex) {
            fail();
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "diretório não encontrado");
        }
    }

    @Test
    public void testLerFuncs5() {
        SimpleDateFormat forDate = new SimpleDateFormat("dd/MM/yyyy");
        leituraFunc.setDiretorio("C:\\Users\\crhobus\\MyDataFiles\\Projects\\java-projects\\netbeans-projects\\JUnitFuncionario\\ArqFuncionarios");
        try {
            leituraFunc.lerFuncs();
        } catch (IOException ex) {
            fail();
        } catch (NoSuchMethodException ex) {
            fail();
        } catch (IllegalAccessException ex) {
            fail();
        } catch (IllegalArgumentException ex) {
            fail();
        } catch (InvocationTargetException ex) {
            fail();
        } catch (NoSuchFieldException ex) {
            fail();
        } catch (ParseException ex) {
            fail();
        } catch (Exception ex) {
            fail();
        }
        assertNotNull(leituraFunc.getLista());
        assertEquals(6, leituraFunc.getQtdadeFunc());
        for (Funcionario func : leituraFunc.getLista()) {
            if (func.getNome().equals("str")) {
                assertEquals("str", func.getNome());
                assertEquals("Hobus", func.getSobrenome());
                assertEquals("Caco", func.getApelido());
                assertEquals("Bradesco", func.getBanco());
                assertEquals("123-098", func.getAgencia());
                assertEquals("67356", func.getConta());
                assertEquals("75458", func.getContaFGTS());
                assertEquals("987.978.835-90", func.getCpf());
                assertEquals("653.908.567", func.getRg());
                assertEquals("6745.438", func.getCarteiraHabilitacao());
                assertEquals("6739.8", func.getCarteiraTrabalho());
                assertEquals("758327", func.getCertificadoReservista());
                assertEquals("7658345942", func.getTituloEleitor());
                assertEquals("jj", func.getNomePai());
                assertEquals("mm", func.getNomeMae());
                assertEquals("Brasil", func.getPais());
                assertEquals("get", func.getRua());
                assertEquals("t", func.getBairro());
                assertEquals("89130-000", func.getCep());
                assertEquals("casa", func.getComplemento());
                assertEquals("3357-9160", func.getTelefoneResidencial());
                assertEquals("3388-9876", func.getTelefoneComercial());
                assertEquals("2290-1625", func.getTelefoneCelular());
                assertEquals("djbasjk", func.getChefe());
                assertEquals("Técnico em informática", func.getCargo());
                assertEquals('F', func.getSexo());
                assertEquals(4, func.getEstadoCivil());
                assertEquals(23, func.getDigitoConta());
                assertEquals(323, func.getCentroCusto());
                assertEquals(1247, func.getCracha());
                assertEquals(22, func.getEstabilidade());
                assertEquals(22, func.getDigitoCarteiraTrabalho());
                assertEquals(123, func.getDocumentoEstrangeiro());
                assertEquals(43232, func.getDescontoINSS());
                assertEquals(23444, func.getDependentesIR());
                assertEquals(7468, func.getClasseContribuicaoIR());
                assertEquals(2334, func.getHorasSemanais());
                assertEquals(2322, func.getInscricaoINSS());
                assertEquals(23, func.getPis());
                assertEquals(23, func.getNumeroContrato());
                assertEquals(23, func.getTipoContrato());
                assertEquals("05/10/1998", forDate.format(func.getDataNascimento()));
                assertEquals("09/08/2009", forDate.format(func.getDataContratacao()));
                assertEquals("09/09/2010", forDate.format(func.getDataPagamento()));
                assertEquals(1000, func.getSalario(), 0);
                assertEquals(1234.98, func.getSaldoFGTS(), 0);
                assertEquals(true, func.isInsentoIR());
                assertEquals(false, func.isContribuicaoSindical());
            }
        }
    }
}
