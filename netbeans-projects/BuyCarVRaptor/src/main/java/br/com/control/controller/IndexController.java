package br.com.control.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.control.action.PedidoAction;
import br.com.model.beans.ItemPedido;
import br.com.model.beans.Pedido;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
public class IndexController {

    @Inject
    private Result result;
    @Inject
    private PedidoAction pedidoAction;
    @Inject
    private Validator validator;

    @Post("/add")
    public void add(@NotNull @Valid Pedido pedido) {
        if (pedido.getCliente().getNrSequencia() == null) {
            validator.add(new SimpleMessage("pedido.cliente.nrSequencia", "O cliente deve ser informado!"));
        }
        if (pedido.getVeiculo().getNrSequencia() == null) {
            validator.add(new SimpleMessage("pedido.veiculo.nrSequencia", "O veículo deve ser informado!"));
        }
        validator.onErrorRedirectTo(this).index(pedido, null, null);
        Pedido p = null;
        try {
            p = pedidoAction.addPedido(pedido);
            result.include("msg", "Pedido cadastrado com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Falha ao cadastrar o pedido");
        } finally {
            result.redirectTo(this).index(p, null, null);
        }
    }

    @Get("/editar")
    public void editar(long nrSequencia) {
        try {
            result.redirectTo(this).index(pedidoAction.getPedido(nrSequencia), null, null);
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Erro ao editar o pedido");
        }
    }

    @Get("/")
    public Pedido index(Pedido pedido, ItemPedido itemPedido, Long nrSeqPedido) {
        try {
            result.include("clientes", pedidoAction.getClientes());
            result.include("veiculos", pedidoAction.getVeiculos());
            result.include("opcionais", pedidoAction.getOpcionais());
            if (nrSeqPedido != null) {
                pedido = pedidoAction.getPedido(nrSeqPedido);
            }
            result.include("itemPedido", itemPedido);
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Falha ao montar a página de pedidos");
        }
        return pedido;
    }

    @Get("/listarPedidos")
    public void listarPedidos() {
        try {
            result.include("pedidos", pedidoAction.getPedidos());
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Falha ao listar os pedidos");
        }
    }

    @Post("/addItem")
    public void addItem(@NotNull @Valid ItemPedido itemPedido, Long nrSeqPedido) {
        if (itemPedido.getOpcional().getNrSequencia() == null) {
            validator.add(new SimpleMessage("itemPedido.opcional.nrSequencia", "O opcional deve ser informado!"));
        }
        validator.onErrorRedirectTo(this).index(null, itemPedido, nrSeqPedido);
        try {
            pedidoAction.addItemPedido(itemPedido, nrSeqPedido);
            result.include("msg", "Item do pedido cadastrado com sucesso");
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Falha ao cadastrar o Item do pedido");
        } finally {
            result.redirectTo(this).index(null, null, nrSeqPedido);
        }
    }

    @Get("/editarItem")
    public void editarItem(long nrSequencia, Long nrSeqPedido) {
        try {
            result.redirectTo(this).index(null, pedidoAction.getItemPedido(nrSequencia), nrSeqPedido);
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Erro ao editar os itens do pedido");
        }
    }

    @Get("/removerItem")
    public void removerItem(long nrSequencia, Long nrSeqPedido) {
        try {
            pedidoAction.removeItemPedido(nrSequencia);
        } catch (Exception ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
            result.include("msg", "Erro ao excluir o item do pedido");
        } finally {
            result.redirectTo(this).index(null, null, nrSeqPedido);
        }
    }
}
