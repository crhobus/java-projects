package br.com.rest.mars.service;

import br.com.rest.mars.model.Posicao;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author crhobus
 */
public class MarsServiceTest {

    public MarsServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testMovimentarRoboOk1() throws Exception {
        String comandos = "MMRMMRMM";
        MarsService instance = new MarsService();
        Posicao expResult = new Posicao();
        expResult.setX(2);
        expResult.setY(0);
        expResult.setOrientacao("S");
        Posicao result = instance.movimentarRobo(comandos);

        assertNotNull(result);
        assertEquals(expResult, result);
    }

    @Test
    public void testMovimentarRoboOk2() throws Exception {
        String comandos = "MML";
        MarsService instance = new MarsService();
        Posicao expResult = new Posicao();
        expResult.setX(0);
        expResult.setY(2);
        expResult.setOrientacao("W");
        Posicao result = instance.movimentarRobo(comandos);

        assertNotNull(result);
        assertEquals(expResult, result);
    }

    @Test
    public void testMovimentarRoboEntradaInvalida() throws Exception {
        try {
            String comandos = "AAA";
            MarsService instance = new MarsService();
            instance.movimentarRobo(comandos);
            fail("Erro no caso de teste: testMovimentarRoboEntradaInvalida");
        } catch (Exception ex) {
            assertTrue(ex.getMessage().matches("Entrada inválida"));
        }
    }

    @Test
    public void testMovimentarRoboPosicaoInvalida() throws Exception {
        try {
            String comandos = "MMMMMMMMMMMMMMMMMMMMMMMM";
            MarsService instance = new MarsService();
            instance.movimentarRobo(comandos);
            fail("Erro no caso de teste: testMovimentarRoboPosicaoInvalida");
        } catch (Exception ex) {
            assertTrue(ex.getMessage().matches("Posição inválida"));
        }
    }
}
