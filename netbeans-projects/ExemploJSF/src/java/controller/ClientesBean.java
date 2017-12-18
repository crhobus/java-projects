package controller;

import data.Cliente;
import data.ClienteDao;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "clicad")
public class ClientesBean {

    private Cliente cliente = new Cliente();
    private ClienteDao dao = new ClienteDao();

    public Cliente getCliente() {
        return cliente;
    }

    public ClienteDao getDao() {
        return dao;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String novo() {
        this.cliente = new Cliente();
        return "formcli";
    }

    public String salvar() {
        if (dao.getClientes().indexOf(cliente) == -1) {
            dao.getClientes().add(cliente);
        }
        return "listcli";
    }
}
