package br.com.control.action;

import br.com.model.beans.Cliente;
import br.com.model.dao.ClienteDAO;
import br.com.model.dao.util.DAOAdapter;
import br.com.model.dao.util.Operacao;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class ClienteAction {

    private final DAOAdapter<Cliente> clienteDAO;

    public ClienteAction() {
        clienteDAO = new ClienteDAO();
    }

    public Cliente addCliente(Cliente cliente) throws Exception {
        try {
            clienteDAO.init();
            if (cliente.getNrSequencia() == null) {
                return clienteDAO.insert(cliente);
            } else {
                return clienteDAO.update(cliente);
            }
        } finally {
            clienteDAO.destroy();
        }
    }

    public void removeCliente(long nrSequencia) throws Exception {
        try {
            clienteDAO.init();
            clienteDAO.delete(nrSequencia);
        } finally {
            clienteDAO.destroy();
        }
    }

    public Cliente getCliente(long nrSequencia) throws Exception {
        try {
            clienteDAO.init();
            return clienteDAO.get(nrSequencia);
        } finally {
            clienteDAO.destroy();
        }
    }

    public List<Cliente> getClientes() throws Exception {
        try {
            clienteDAO.init();
            return clienteDAO.list(Operacao.LISTAR);
        } finally {
            clienteDAO.destroy();
        }
    }
}
