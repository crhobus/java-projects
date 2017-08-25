package br.com.rest.mars.api;

import br.com.rest.mars.controller.MarsController;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author crhobus
 */
@Path("/mars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MarsAPI {

    @POST
    @Path("/{comandos}")
    public Response movimentarRobo(@PathParam("comandos") String comandos) {
        return new MarsController().movimentarRobo(comandos);
    }
}
