package br.com.control.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.control.action.OpcionalAction;
import br.com.model.beans.Opcional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
public class OpcionalController {

    @Inject
    private Result result;
    @Inject
    private OpcionalAction opcionalAction;
    @Inject
    private Validator validator;

    @Post("/opcional/add")
    public void add(@NotNull @Valid Opcional opcional) {
        validator.onErrorRedirectTo(this).formOpcional(opcional);
        Opcional o = null;
        try {
            o = opcionalAction.addOpcional(opcional);
            result.include("msg", "Opcional cadastrado com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(OpcionalController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Falha ao cadastrar o opcional");
        } finally {
            result.redirectTo(this).formOpcional(o);
        }
    }

    @Get("/opcional/editar")
    public void editar(long nrSequencia) {
        try {
            result.redirectTo(this).formOpcional(opcionalAction.getOpcional(nrSequencia));
        } catch (Exception ex) {
            Logger.getLogger(OpcionalController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Erro ao editar o opcionl");
        }
    }

    @Get("/opcional/remover")
    public void remover(long nrSequencia) {
        try {
            opcionalAction.removeOpcional(nrSequencia);
        } catch (Exception ex) {
            Logger.getLogger(OpcionalController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Erro ao excluir o opcional");
        } finally {
            result.redirectTo(this).listarOpcionais();
        }
    }

    @Get("/opcional/formOpcional")
    public Opcional formOpcional(Opcional opcional) {
        return opcional;
    }

    @Get("/opcional/listarOpcionais")
    public void listarOpcionais() {
        try {
            result.include("opcionais", opcionalAction.getOpcionais());
        } catch (Exception ex) {
            Logger.getLogger(OpcionalController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Falha ao listar os opcionais");
        }
    }
}
