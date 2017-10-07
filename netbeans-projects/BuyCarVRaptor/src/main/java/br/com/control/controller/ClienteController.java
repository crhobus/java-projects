package br.com.control.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.control.action.ClienteAction;
import br.com.model.beans.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
public class ClienteController {

    @Inject
    private Result result;
    @Inject
    private ClienteAction clienteAction;
    @Inject
    private Validator validator;

    @Post("/cliente/add")
    public void add(@NotNull @Valid Cliente cliente) {
        if ("F".equalsIgnoreCase(cliente.getTipoPessoa())) {
            if (cliente.getCpf() == null) {
                validator.add(new SimpleMessage("cliente.cpf", "O CPF deve ser informado!"));
            }
            cliente.setCnpj(null);
        } else if ("J".equalsIgnoreCase(cliente.getTipoPessoa())) {
            if (cliente.getCnpj() == null) {
                validator.add(new SimpleMessage("cliente.cnpj", "O CNPJ deve ser informado!"));
            }
            cliente.setCpf(null);
        }
        validator.onErrorRedirectTo(this).formCliente(cliente);
        Cliente c = null;
        try {
            c = clienteAction.addCliente(cliente);
            result.include("msg", "Cliente cadastrado com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Falha ao cadastrar o cliente");
        } finally {
            result.redirectTo(this).formCliente(c);
        }
    }

    @Get("/cliente/editar")
    public void editar(long nrSequencia) {
        try {
            result.redirectTo(this).formCliente(clienteAction.getCliente(nrSequencia));
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Erro ao editar o cliente");
        }
    }

    @Get("/cliente/remover")
    public void remover(long nrSequencia) {
        try {
            clienteAction.removeCliente(nrSequencia);
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Erro ao excluir o cliente");
        } finally {
            result.redirectTo(this).listarClientes();
        }
    }

    @Get("/cliente/formCliente")
    public Cliente formCliente(Cliente cliente) {
        return cliente;
    }

    @Get("/cliente/listarClientes")
    public void listarClientes() {
        try {
            result.include("clientes", clienteAction.getClientes());
        } catch (Exception ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Falha ao listar os clientes");
        }
    }
}
