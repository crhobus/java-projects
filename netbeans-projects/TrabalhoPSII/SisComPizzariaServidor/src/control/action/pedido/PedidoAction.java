package control.action.pedido;

import control.funcoes.Dados;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.util.List;
import model.dao.PedidoDAO;
import model.dao.ServidorDAO;
import model.entidades.Pedido;

public class PedidoAction {

    private PedidoDAO pedidoDAO;
    private ServidorDAO servidorDAO;

    public PedidoAction(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
        this.pedidoDAO = new PedidoDAO(servidorDAO);
    }

    public List<Pedido> getPedidos() throws ExceptionError {
        try {
            return pedidoDAO.getPedidos();
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível obter os pedidos do sistema."
                    + "\nErro no Servidor");
        }
    }

    public Pedido salvar(Dados dados, Pedido pedido) throws ExceptionInfo, ExceptionError, Exception {

        //    produto.setCdProduto(Integer.parseInt(dados.getInfo("CD_PRODUTO")));

        //  pedido.setDtCadastro(null);

        //   sabor.setNmSabor(dados.getInfo("NM_SABOR"));
        //   sabor.setDsSabor(dados.getInfo("DS_SABOR"));

        try {
            servidorDAO.salvar(pedido, pedido.getCdPedido() == 0);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível salvar o pedido no sistema."
                    + "\nErro no Servidor");
        }
        return pedido;
    }

    public Pedido salvar(Pedido pedido) throws ExceptionInfo, ExceptionError, Exception {

        try {
            servidorDAO.salvar(pedido, pedido.getCdPedido() == 0);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível salvar o pedido no sistema."
                    + "\nErro no Servidor");
        }
        return pedido;
    }

    public void excluirPedido(int cdPedido) throws ExceptionError {
        try {
            pedidoDAO.excluirPedido(cdPedido);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível excluir o pedido do sistema."
                    + "\nErro no Servidor");
        }
    }
}
