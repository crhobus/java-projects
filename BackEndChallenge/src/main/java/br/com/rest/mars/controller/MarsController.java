package br.com.rest.mars.controller;

import br.com.rest.mars.model.Posicao;
import br.com.rest.mars.service.MarsService;
import java.net.HttpURLConnection;
import javax.ws.rs.core.Response;

/**
 *
 * @author crhobus
 */
public class MarsController {

    private final MarsService service;

    public MarsController() {
        this.service = new MarsService();
    }

    public Response movimentarRobo(String comandos) {
        try {
            Posicao pos = service.movimentarRobo(comandos);
            return Response.status(HttpURLConnection.HTTP_OK).entity(pos.toString()).build();
        } catch (Exception ex) {
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build();
        }
    }
}
