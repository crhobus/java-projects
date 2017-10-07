package br.com.control.action;

import br.com.model.beans.Cliente;
import br.com.model.beans.ItemPedido;
import br.com.model.beans.Opcional;
import br.com.model.beans.Pedido;
import br.com.model.beans.Veiculo;
import br.com.model.dao.ItemPedidoDAO;
import br.com.model.dao.PedidoDAO;
import br.com.model.dao.util.DAOAdapter;
import br.com.model.dao.util.Operacao;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class PedidoAction {

    @Inject
    private ClienteAction clienteAction;
    @Inject
    private VeiculoAction veiculoAction;
    @Inject
    private OpcionalAction opcionalAction;
    private final DAOAdapter<Pedido> pedidoDAO;
    private final DAOAdapter<ItemPedido> itemPedidoDAO;

    public PedidoAction() {
        pedidoDAO = new PedidoDAO();
        itemPedidoDAO = new ItemPedidoDAO();
    }

    public Pedido addPedido(Pedido pedido) throws Exception {
        try {
            pedidoDAO.init();
            if (pedido.getNrSequencia() == null) {
                return pedidoDAO.insert(pedido);
            } else {
                return pedidoDAO.update(pedido);
            }
        } finally {
            pedidoDAO.destroy();
        }
    }

    public void addItemPedido(ItemPedido itemPedido, Long nrSeqPedido) throws Exception {
        try {
            itemPedidoDAO.init();
            itemPedidoDAO.setNrSequencia(nrSeqPedido);
            if (itemPedido.getNrSequencia() == null) {
                itemPedidoDAO.insert(itemPedido);
            } else {
                itemPedidoDAO.update(itemPedido);
            }
        } finally {
            itemPedidoDAO.destroy();
        }
    }

    public Pedido getPedido(long nrSequencia) throws Exception {
        try {
            pedidoDAO.init();
            return pedidoDAO.get(nrSequencia);
        } finally {
            pedidoDAO.destroy();
        }
    }

    public List<Pedido> getPedidos() throws Exception {
        try {
            pedidoDAO.init();
            return pedidoDAO.list(Operacao.LISTAR);
        } finally {
            pedidoDAO.destroy();
        }
    }

    public void removeItemPedido(long nrSequencia) throws Exception {
        try {
            itemPedidoDAO.init();
            itemPedidoDAO.delete(nrSequencia);
        } finally {
            itemPedidoDAO.destroy();
        }
    }

    public ItemPedido getItemPedido(long nrSequencia) throws Exception {
        try {
            itemPedidoDAO.init();
            return itemPedidoDAO.get(nrSequencia);
        } finally {
            itemPedidoDAO.destroy();
        }
    }

    public List<Cliente> getClientes() throws Exception {
        return clienteAction.getClientes();
    }

    public List<Veiculo> getVeiculos() throws Exception {
        return veiculoAction.getVeiculos();
    }

    public List<Opcional> getOpcionais() throws Exception {
        return opcionalAction.getOpcionais();
    }
}
