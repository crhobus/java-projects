package br.com.rest.mars.controller;

import javax.ws.rs.core.Response;
import org.hamcrest.CoreMatchers;
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
public class MarsControllerTest {

    public MarsControllerTest() {
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
    public void testMovimentarRoboOK() {
        String comandos = "MMRMMRMM";
        MarsController instance = new MarsController();
        Response result = instance.movimentarRobo(comandos);

        assertNotNull(result);
        assertThat(result.getStatus(), CoreMatchers.is(Response.Status.OK.getStatusCode()));
    }

    @Test
    public void testMovimentarRoboBadRequest() {
        String comandos = "AAA";
        MarsController instance = new MarsController();
        Response result = instance.movimentarRobo(comandos);

        assertNotNull(result);
        assertThat(result.getStatus(), CoreMatchers.is(Response.Status.BAD_REQUEST.getStatusCode()));
    }
}
