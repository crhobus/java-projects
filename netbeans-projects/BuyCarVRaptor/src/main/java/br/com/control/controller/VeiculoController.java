package br.com.control.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.control.action.VeiculoAction;
import br.com.model.beans.Veiculo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
public class VeiculoController {

    @Inject
    private Result result;
    @Inject
    private VeiculoAction veiculoAction;
    @Inject
    private Validator validator;

    @Post("/veiculo/add")
    public void add(@NotNull @Valid Veiculo veiculo) {
        validator.onErrorRedirectTo(this).formVeiculo(veiculo);
        Veiculo v = null;
        try {
            v = veiculoAction.addVeiculo(veiculo);
            result.include("msg", "Veículo cadastrado com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(VeiculoController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Falha ao cadastrar o veículo");
        } finally {
            result.redirectTo(this).formVeiculo(v);
        }
    }

    @Get("/veiculo/editar")
    public void editar(long nrSequencia) {
        try {
            result.redirectTo(this).formVeiculo(veiculoAction.getVeiculo(nrSequencia));
        } catch (Exception ex) {
            Logger.getLogger(VeiculoController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Erro ao editar o veículo");
        }
    }

    @Get("/veiculo/remover")
    public void remover(long nrSequencia) {
        try {
            veiculoAction.removeVeiculo(nrSequencia);
        } catch (Exception ex) {
            Logger.getLogger(VeiculoController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Erro ao excluir o veículo");
        } finally {
            result.redirectTo(this).listarVeiculos();
        }
    }

    @Get("/veiculo/formVeiculo")
    public Veiculo formVeiculo(Veiculo veiculo) {
        return veiculo;
    }

    @Get("/veiculo/listarVeiculos")
    public void listarVeiculos() {
        try {
            result.include("veiculos", veiculoAction.getVeiculos());
        } catch (Exception ex) {
            Logger.getLogger(VeiculoController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Falha ao listar os veículos");
        }
    }
}
