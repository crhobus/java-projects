/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BancoTest;

import Banco.Movimentacao;
import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Caio
 */
public class MovimentacaoTest {

    public MovimentacaoTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getData method, of class Movimentacao.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        Movimentacao instance = null;
        Date expResult = null;
        Date result = instance.getData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTipo method, of class Movimentacao.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        Movimentacao instance = null;
        String expResult = "";
        String result = instance.getTipo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValor method, of class Movimentacao.
     */
    @Test
    public void testGetValor() {
        System.out.println("getValor");
        Movimentacao instance = null;
        double expResult = 0.0;
        double result = instance.getValor();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}