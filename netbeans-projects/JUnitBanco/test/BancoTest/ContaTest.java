/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BancoTest;

import Banco.Conta;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Caio
 */
public class ContaTest {

    public ContaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of depositar method, of class Conta.
     */
    @Test
    public void testDepositar() {
        /*System.out.println("depositar");
        double valor = 0.0;
        Conta instance = new Conta();
        instance.depositar(valor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of sacar method, of class Conta.
     */
    @Test
    public void testSacar() {
        /*System.out.println("sacar");
        double valor = 0.0;
        Conta instance = new Conta();
        double expResult = 0.0;
        double result = instance.sacar(valor);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of getProprietario method, of class Conta.
     */
    @Test
    public void testGetProprietario() {
        System.out.println("getProprietario");
        Conta instance = new Conta();
        String expResult = "";
        String result = instance.getProprietario();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumero method, of class Conta.
     */
    @Test
    public void testGetNumero() {
        System.out.println("getNumero");
        Conta instance = new Conta();
        String expResult = "";
        String result = instance.getNumero();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSaldo method, of class Conta.
     */
    @Test
    public void testGetSaldo() {
        System.out.println("getSaldo");
        Conta instance = new Conta();
        double expResult = 0.0;
        double result = instance.getSaldo();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extrato method, of class Conta.
     */
    @Test
    public void testExtrato() {
        System.out.println("extrato");
        Conta instance = new Conta();
        String expResult = "";
        String result = instance.extrato();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}