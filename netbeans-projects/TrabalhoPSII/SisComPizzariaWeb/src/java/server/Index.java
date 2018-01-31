package server;

import control.Servidor;
import control.ServidorAction;
import control.funcoes.Dados;
import control.funcoes.ExceptionError;
import model.entidades.Cliente;

public class Index {

    private static Servidor servidor;
    private static Cliente clienteLogado;

    public static Servidor getInstanceServidor() throws ExceptionError {
        if (servidor == null) {
            servidor = new ServidorAction();
            servidor.conectar();
        }
        return servidor;
    }

    public static Cliente getClienteLogado() {
        return clienteLogado;
    }

    public static void setClienteLogado(Cliente clienteLogado) {
        Index.clienteLogado = clienteLogado;
    }

    public static Dados getDadosCliente() {
        if (clienteLogado != null) {
            Dados dados = new Dados();
            dados.addInfo("CD_CLIENTE", Integer.toString(clienteLogado.getCdCliente()));
            dados.addInfo("NM_CLIENTE", clienteLogado.getNmCliente());
            dados.addInfo("NM_USUARIO", clienteLogado.getUsuario().getNmUsuario());
            dados.addInfo("DS_ENDERECO", clienteLogado.getEndereco().getDsEndereco());
            dados.addInfo("DS_BAIRRO", clienteLogado.getEndereco().getNmBairro());
            dados.addInfo("NR_CEP", clienteLogado.getEndereco().getNrCep());
            dados.addInfo("NR_RESIDENCIA", Integer.toString(clienteLogado.getEndereco().getNrResidencia()));
            dados.addInfo("NM_CIDADE", clienteLogado.getEndereco().getNmCidade());
            dados.addInfo("DS_REFERENCIA", clienteLogado.getEndereco().getDsReferencia());
            dados.addInfo("NR_TELEFONE", clienteLogado.getNrTefefone());
            dados.addInfo("NR_CELULAR", clienteLogado.getNrCelular());
            dados.addInfo("DS_EMAIL", clienteLogado.getUsuario().getDsEmail());
            dados.addInfo("DS_OBSERVACAO", clienteLogado.getDsObservacao());
            return dados;
        }
        return null;
    }

    public static int getCdCliente() {
        if (clienteLogado != null) {
            return clienteLogado.getCdCliente();
        }
        return 0;
    }
}
