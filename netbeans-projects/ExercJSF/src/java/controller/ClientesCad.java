package controller;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import model.Cliente;

@ManagedBean(name = "clicad")
public class ClientesCad {

    private Cliente cliente = new Cliente();
    public static List<Cliente> clientes = new ArrayList<Cliente>();

    public Cliente getCliente() {
        return cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public String salvar() {
        clientes.add(cliente);
        System.out.println(cliente);
        return "sucesso";
    }
}