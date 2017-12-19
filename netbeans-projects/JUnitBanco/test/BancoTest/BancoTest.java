/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BancoTest;

import Banco.Poupanca;
import Banco.ContaCorrente;
import Banco.Banco;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Caio
 */
public class BancoTest {

    private Banco banco;

    public BancoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        banco = new Banco();
    }

    @After
    public void tearDown() {
        banco = null;
    }

    /**
     * Test of novaContaCorrente method, of class Banco.
     */
    @Test
    public void testNovaContaCorrente() {
        try {
            assertNotNull(banco.novaContaCorrente("Caio", "1"));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNull(banco.novaContaCorrente("KHG", "-54"));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Número deve ser maior que 1");
        }
        try {
            assertNull(banco.novaContaCorrente(null, "1"));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um nome válido");
        }
        try {
            assertNotNull(banco.novaContaCorrente("Ana", "6"));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.novaContaCorrente("Renan", "8"));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNull(banco.novaContaCorrente("Renan", null));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
        try {
            assertNull(banco.novaContaCorrente("MJ", "-90,8"));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
        try {
            assertNull(banco.novaContaCorrente("Renan", "8"));
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Conta com esse número ja esta cadastrado");
        }
        try {
            assertNotNull(banco.novaContaCorrente("Hobus", "9"));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.novaContaCorrente("Marta", "2"));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNull(banco.novaContaCorrente("CRH", "126'"));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
    }

    /**
     * Test of novaContaPoupanca method, of class Banco.
     */
    @Test
    public void testNovaContaPoupanca() {
        try {
            assertNotNull(banco.novaContaPoupanca("Caio", "1", 7));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNull(banco.novaContaPoupanca("KHG", "-5", 8));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Número deve ser maior que 1");
        }
        try {
            assertNull(banco.novaContaPoupanca("KHG", "-5.8", 8));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
        try {
            assertNull(banco.novaContaPoupanca(null, "1", 87));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um nome válido");
        }
        try {
            assertNotNull(banco.novaContaPoupanca("Ana", "6", 76));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.novaContaPoupanca("Renan", "8", 6));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNull(banco.novaContaPoupanca("Renan", null, 7));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
        try {
            assertNull(banco.novaContaPoupanca("Renan", "8", 6));
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Conta com esse número ja esta cadastrado");
        }
        try {
            assertNotNull(banco.novaContaPoupanca("Hobus", "9", 3));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.novaContaPoupanca("Marta", "2", 5));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNull(banco.novaContaPoupanca("CRH", "126'", 6));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
        try {
            assertNull(banco.novaContaPoupanca("KHG", "-54", 65));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Número deve ser maior que 1");
        }
        try {
            assertNull(banco.novaContaPoupanca("dc", "-54.98", 7));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
    }

    /**
     * Test of getValorTotalSaldo method, of class Banco.
     */
    @Test
    public void testGetValorTotalSaldo() {
        ContaCorrente conta1 = null;
        try {
            conta1 = banco.novaContaCorrente("Ana", "6");
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            conta1.depositar(89);
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            conta1.sacar(70);
        } catch (Exception ex) {
            fail("teste falhou");
        }
        ContaCorrente conta2 = null;
        try {
            conta2 = banco.novaContaCorrente("Hobus", "9");
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            conta2.depositar(8);
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            conta2.sacar(800);
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "saldo insuficiente");
        }
        try {
            conta2.sacar(1);
        } catch (Exception ex) {
            fail("teste falhou");
        }
        ContaCorrente conta3 = null;
        try {
            conta3 = banco.novaContaCorrente("Marta", "2");
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            conta3.depositar(80);
        } catch (Exception ex) {
            fail("teste falhou");
        }

        Poupanca conta4 = null;
        try {
            conta4 = banco.novaContaPoupanca("Marlos", "6", 8);
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            conta4.depositar(10);
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            conta4.depositar(-110.98);
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Número deve ser maior que 0");
        }
        try {
            conta4.sacar(-110.98);
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Número deve ser maior que 0");
        }
        try {
            conta4.sacar(0);
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Número deve ser maior que 0");
        }
        Poupanca conta5 = null;
        try {
            conta5 = banco.novaContaPoupanca("Maria", "65", 9);
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            conta5.depositar(700);
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            conta5.sacar(780);
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "saldo insuficiente");
        }
        Poupanca conta6 = null;
        try {
            conta6 = banco.novaContaPoupanca("Marcelo", "61", 70);
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            conta6.depositar(90);
        } catch (Exception ex) {
            fail("teste falhou");
        }

        assertEquals(905.4, banco.getValorTotalSaldo(), 0.0);
    }

    /**
     * Test of getContaCorrente method, of class Banco.
     */
    @Test
    public void testGetContaCorrente() {
        try {
            assertNotNull(banco.novaContaCorrente("Ana", "1"));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.novaContaCorrente("Renan", "8"));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.novaContaCorrente("Mara", "60"));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.novaContaCorrente("Bianca", "609"));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.novaContaCorrente("Tiago", "80"));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.getContaCorrente("1"));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNull(banco.getContaCorrente("10"));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Conta não encontrada");
        }
        try {
            assertNull(banco.getContaCorrente("910"));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Conta não encontrada");
        }
        try {
            assertNull(banco.getContaCorrente("i"));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
        try {
            assertNull(banco.getContaCorrente("-30"));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Número deve ser maior que 1");
        }
        try {
            assertNull(banco.getContaCorrente("-30.878"));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
        try {
            assertNull(banco.getContaCorrente(null));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
    }

    /**
     * Test of getPoupanca method, of class Banco.
     */
    @Test
    public void testGetPoupanca() {
        try {
            assertNotNull(banco.novaContaPoupanca("Morais", "20", 32));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.novaContaPoupanca("Ana", "7", 98));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.novaContaPoupanca("Elias", "87", 89));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNotNull(banco.getPoupanca("20", 8));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNull(banco.getPoupanca("22", 8));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Conta não encontrada");
        }
        try {
            assertNotNull(banco.getPoupanca("7", 8));
        } catch (Exception ex) {
            fail("teste falhou");
        }
        try {
            assertNull(banco.getPoupanca("827l", 8));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
        try {
            assertNull(banco.getPoupanca("2", 8));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Conta não encontrada");
        }
        try {
            assertNull(banco.getPoupanca("-30", 6));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Número deve ser maior que 1");
        }
        try {
            assertNull(banco.getPoupanca("-30.878", 7));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
        try {
            assertNull(banco.getPoupanca(null, 5));
            fail("teste falhou");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Entre com um número válido");
        }
    }
}
