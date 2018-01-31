package control.action.cliente;

import control.funcoes.Dados;
import control.funcoes.ExceptionError;
import control.funcoes.ExceptionInfo;
import java.util.Date;
import java.util.List;
import model.dao.ClienteDAO;
import model.dao.ServidorDAO;
import model.dao.UsuarioDAO;
import model.entidades.Cliente;
import model.entidades.Endereco;
import model.entidades.Usuario;

public class ClienteAction {

    private ClienteDAO clienteDAO;
    private ServidorDAO servidorDAO;

    public ClienteAction(ServidorDAO servidorDAO) {
        this.servidorDAO = servidorDAO;
        this.clienteDAO = new ClienteDAO(servidorDAO);
    }

    public List<Cliente> getClientes() throws ExceptionError {
        try {
            return clienteDAO.getClientes();
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível obter os clientes do sistema."
                    + "\nErro no Servidor");
        }
    }
    
    public List<Cliente> getClientes(String nmCliente, String nrTelefone, String nrCelular) throws ExceptionError {
        try {
            return clienteDAO.getClientes(nmCliente,nrTelefone,nrCelular);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível obter os clientes do sistema."
                    + "\nErro no Servidor");
        }
    }
    

    public Cliente salvar(Dados dados, Cliente cliente) throws ExceptionInfo, ExceptionError {
        Usuario u = null;
        try {
            u = clienteDAO.getUsuario(dados.getInfo("NM_USUARIO"), (cliente == null ? 0 : cliente.getCdCliente()));
        } catch (Exception ex) {
        }
        if (u != null) {
            throw new ExceptionInfo("NM_USUARIO", "Este usuário já existe, altere o nome do usuário");
        }
        if (!dados.getInfo("DS_CONFIRMA_SENHA").equals(dados.getInfo("DS_SENHA"))) {
            throw new ExceptionInfo("DS_CONFIRMA_SENHA", "Confirme sua senha");
        }
        Endereco endereco;
        Usuario usuario;
        if (cliente == null) {
            cliente = new Cliente();
            endereco = new Endereco();
            usuario = new Usuario();
        } else {
            endereco = cliente.getEndereco();
            usuario = cliente.getUsuario();
        }

        endereco.setDsEndereco(dados.getInfo("DS_ENDERECO"));
        endereco.setNmBairro(dados.getInfo("DS_BAIRRO"));
        endereco.setNrCep(dados.getInfo("NR_CEP"));
        endereco.setNrResidencia(Integer.parseInt(dados.getInfo("NR_RESIDENCIA")));
        endereco.setNmCidade(dados.getInfo("NM_CIDADE"));
        endereco.setDsReferencia(dados.getInfo("DS_REFERENCIA"));

        cliente.setEndereco(endereco);

        usuario.setNmUsuario(dados.getInfo("NM_USUARIO"));
        usuario.setDsSenha(dados.getInfo("DS_SENHA"));
        usuario.setDsEmail(dados.getInfo("DS_EMAIL"));
        usuario.setDtAtualizacao(new Date());
        try {
            UsuarioDAO usuarioDAO = new UsuarioDAO(servidorDAO);
            usuario.setTipoUsuario(usuarioDAO.getTipoUsuario(4));
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível salvar o cliente no sistema."
                    + "\nTipo de usuário não encontrado");
        }

        cliente.setUsuario(usuario);

        cliente.setNmCliente(dados.getInfo("NM_CLIENTE"));
        cliente.setNrTefefone(dados.getInfo("NR_TELEFONE"));
        cliente.setNrCelular(dados.getInfo("NR_CELULAR"));
        cliente.setDsObservacao(dados.getInfo("DS_OBSERVACAO"));
        try {
            servidorDAO.salvar(cliente, cliente.getCdCliente() == 0);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível salvar o cliente no sistema."
                    + "\nErro no Servidor");
        }
        return cliente;
    }

    public void excluirCliente(int cdCliente) throws ExceptionError {
        try {
            clienteDAO.excluirCliente(cdCliente);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível excluir o cliente do sistema."
                    + "\nErro no Servidor");
        }
    }

    public Cliente getCliente(String nmUsuario) throws ExceptionError {
        try {
            return clienteDAO.getCliente(nmUsuario);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível autenticar o usuário no sistema."
                    + "\nErro no Servidor");
        }
    }

    public Cliente getCliente(int cdUsuario) throws ExceptionError {
        try {
            return clienteDAO.getCliente(cdUsuario);
        } catch (Exception ex) {
            throw new ExceptionError("Não foi possível salvar o usuário no sistema."
                    + "\nErro no Servidor");
        }
    }
}
