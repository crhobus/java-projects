package control;

import control.action.cliente.ClienteAction;
import control.action.funcionario.FuncionarioAction;
import control.action.pedido.PedidoAction;
import control.action.produto.ProdutoAction;
import control.action.produto.SaborAction;
import control.action.produto.TamanhoAction;
import control.action.usuario.UsuarioAction;
import control.funcoes.ExceptionError;

public interface Servidor {

    public void conectar() throws ExceptionError;

    public void desconectar() throws ExceptionError;

    public UsuarioAction getUsuarioAction();

    public ProdutoAction getProdutoAction();
    
    public SaborAction getSaborAction();
    
    public TamanhoAction getTamanhoAction();

    public ClienteAction getClienteAction();

    public PedidoAction getPedidoAction();

    public FuncionarioAction getFuncionarioAction();
    
}
