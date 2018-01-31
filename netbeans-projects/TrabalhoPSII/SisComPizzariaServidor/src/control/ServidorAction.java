package control;

import control.action.cliente.ClienteAction;
import control.action.funcionario.FuncionarioAction;
import control.action.pedido.PedidoAction;
import control.action.produto.ProdutoAction;
import control.action.produto.SaborAction;
import control.action.produto.TamanhoAction;
import control.action.usuario.UsuarioAction;
import control.funcoes.ExceptionError;
import model.dao.ServidorDAO;

public class ServidorAction implements Servidor {

    private ServidorDAO servidorDAO;

    @Override
    public void conectar() throws ExceptionError {
        servidorDAO = new ServidorDAO();
    }

    @Override
    public void desconectar() throws ExceptionError {
        servidorDAO.desconectar();
    }

    @Override
    public UsuarioAction getUsuarioAction() {
        return new UsuarioAction(servidorDAO);
    }

    @Override
    public ProdutoAction getProdutoAction() {
        return new ProdutoAction(servidorDAO);
    }

    @Override
    public ClienteAction getClienteAction() {
        return new ClienteAction(servidorDAO);
    }

    @Override
    public PedidoAction getPedidoAction() {
        return new PedidoAction(servidorDAO);
    }

    @Override
    public FuncionarioAction getFuncionarioAction() {
        return new FuncionarioAction(servidorDAO);
    }

    @Override
    public SaborAction getSaborAction() {
         return new SaborAction(servidorDAO);
    }

    @Override
    public TamanhoAction getTamanhoAction() {
        return new TamanhoAction(servidorDAO);
    }
}
